package com.geekerstar.monitor.controller;

import com.geekerstar.common.annotation.ControllerEndPoint;
import com.geekerstar.common.annotation.Weblog;
import com.geekerstar.common.entity.GeekResponse;
import com.geekerstar.common.util.DateUtil;
import com.geekerstar.monitor.endpoint.GeekHttpTraceEndpoint;
import com.geekerstar.monitor.entity.GeekHttpTrace;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.geekerstar.monitor.endpoint.GeekHttpTraceEndpoint.GeekHttpTraceDescriptor;

/**
 * @author geekerstar
 * @date 2020/2/1 11:51
 * @description
 */
@Api("actuator监控")
@Slf4j
@RestController
@RequestMapping("febs/actuator")
public class GeekActuatorController {
    @Autowired
    private GeekHttpTraceEndpoint httpTraceEndpoint;

    @GetMapping("httptrace")
    @Weblog(description = "请求追踪")
    @RequiresPermissions("httptrace:view")
    @ControllerEndPoint(exceptionMessage = "请求追踪失败")
    @ApiOperation(value = "请求追踪", notes = "请求追踪")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "method", value = "方法", paramType = "query", required = true, defaultValue = ""),
            @ApiImplicitParam(name = "url", value = "url", paramType = "query", required = true, defaultValue = "")
    })
    public GeekResponse httpTraces(String method, String url) {
        GeekHttpTraceDescriptor traces = httpTraceEndpoint.traces();
        List<HttpTrace> httpTraceList = traces.getTraces();
        List<GeekHttpTrace> geekHttpTraces = Lists.newArrayList();
        httpTraceList.forEach(t -> {
            GeekHttpTrace geekHttpTrace = new GeekHttpTrace();
            geekHttpTrace.setRequestTime(DateUtil.formatInstant(t.getTimestamp(), DateUtil.FULL_TIME_SPLIT_PATTERN));
            geekHttpTrace.setMethod(t.getRequest().getMethod());
            geekHttpTrace.setUrl(t.getRequest().getUri());
            geekHttpTrace.setStatus(t.getResponse().getStatus());
            geekHttpTrace.setTimeTaken(t.getTimeTaken());
            if (StringUtils.isNotBlank(method) && StringUtils.isNotBlank(url)) {
                if (StringUtils.equalsIgnoreCase(method, geekHttpTrace.getMethod())
                        && StringUtils.containsIgnoreCase(geekHttpTrace.getUrl().toString(), url))
                    geekHttpTraces.add(geekHttpTrace);
            } else if (StringUtils.isNotBlank(method)) {
                if (StringUtils.equalsIgnoreCase(method, geekHttpTrace.getMethod()))
                    geekHttpTraces.add(geekHttpTrace);
            } else if (StringUtils.isNotBlank(url)) {
                if (StringUtils.containsIgnoreCase(geekHttpTrace.getUrl().toString(), url))
                    geekHttpTraces.add(geekHttpTrace);
            } else {
                geekHttpTraces.add(geekHttpTrace);
            }
        });
        Map<String, Object> data = new HashMap<>();
        data.put("rows", geekHttpTraces);
        data.put("total", geekHttpTraces.size());
        return new GeekResponse().success().data(data);
    }
}

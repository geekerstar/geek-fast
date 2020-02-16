package com.geekerstar.monitor.controller;

import com.geekerstar.common.entity.GeekConstant;
import com.geekerstar.common.util.GeekUtil;
import com.geekerstar.monitor.entity.JvmInfo;
import com.geekerstar.monitor.entity.ServerInfo;
import com.geekerstar.monitor.entity.TomcatInfo;
import com.geekerstar.monitor.helper.GeekActuatorHelper;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static com.geekerstar.monitor.endpoint.GeekMetricsEndpoint.GeekMetricResponse;


/**
 * @author geekerstar
 * @date 2020/2/1 12:28
 * @description
 */
@Api(tags = "监控视图")
@Controller("monitorView")
@RequestMapping(GeekConstant.VIEW_PREFIX + "monitor")
public class ViewController {
    @Autowired
    private GeekActuatorHelper actuatorHelper;

    @GetMapping("online")
    @RequiresPermissions("online:view")
    public String online() {
        return GeekUtil.view("monitor/online");
    }

    @GetMapping("log")
    @RequiresPermissions("log:view")
    public String log() {
        return GeekUtil.view("monitor/log");
    }

    @GetMapping("loginlog")
    @RequiresPermissions("loginlog:view")
    public String loginLog() {
        return GeekUtil.view("monitor/loginLog");
    }

    @GetMapping("httptrace")
    @RequiresPermissions("httptrace:view")
    public String httptrace() {
        return GeekUtil.view("monitor/httpTrace");
    }

    @GetMapping("jvm")
    @RequiresPermissions("jvm:view")
    public String jvmInfo(Model model) {
        List<GeekMetricResponse> jvm = actuatorHelper.getMetricResponseByType("jvm");
        JvmInfo jvmInfo = actuatorHelper.getJvmInfoFromMetricData(jvm);
        model.addAttribute("jvm", jvmInfo);
        return GeekUtil.view("monitor/jvmInfo");
    }

    @GetMapping("tomcat")
    @RequiresPermissions("tomcat:view")
    public String tomcatInfo(Model model) {
        List<GeekMetricResponse> tomcat = actuatorHelper.getMetricResponseByType("tomcat");
        TomcatInfo tomcatInfo = actuatorHelper.getTomcatInfoFromMetricData(tomcat);
        model.addAttribute("tomcat", tomcatInfo);
        return GeekUtil.view("monitor/tomcatInfo");
    }

    @GetMapping("server")
    @RequiresPermissions("server:view")
    public String serverInfo(Model model) {
        List<GeekMetricResponse> jdbcInfo = actuatorHelper.getMetricResponseByType("jdbc");
        List<GeekMetricResponse> systemInfo = actuatorHelper.getMetricResponseByType("system");
        List<GeekMetricResponse> processInfo = actuatorHelper.getMetricResponseByType("process");

        ServerInfo serverInfo = actuatorHelper.getServerInfoFromMetricData(jdbcInfo, systemInfo, processInfo);
        model.addAttribute("server", serverInfo);
        return GeekUtil.view("monitor/serverInfo");
    }

    @GetMapping("swagger")
    public String swagger() {
        return GeekUtil.view("monitor/swagger");
    }

    @GetMapping("druid")
    public String druid() {
        return GeekUtil.view("monitor/druid");
    }

    @GetMapping("redisInfo")
    public String redisInfo() {
        return GeekUtil.view("monitor/redisInfo");
    }

    @GetMapping("redisTerminal")
    public String redisTerminal() {
        return GeekUtil.view("monitor/redisTerminal");
    }
}

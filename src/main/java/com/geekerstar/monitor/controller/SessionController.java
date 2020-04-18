package com.geekerstar.monitor.controller;

import com.geekerstar.common.annotation.Weblog;
import com.geekerstar.common.entity.GeekResponse;
import com.geekerstar.monitor.entity.ActiveUser;
import com.geekerstar.monitor.service.ISessionService;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author geekerstar
 * @date 2020/2/1 12:22
 * @description
 */
@Api(tags = "Session日志监控")
@RestController
@RequestMapping("session")
@RequiredArgsConstructor
public class SessionController {

    private final ISessionService sessionService;

    @GetMapping("list")
    @Weblog(description = "在线用户列表")
    @RequiresPermissions("online:view")
    @ApiOperation(value = "在线用户列表", notes = "在线用户列表")
    public GeekResponse list(String username) {
        List<ActiveUser> list = sessionService.list(username);
        Map<String, Object> data = Maps.newHashMap();
        data.put("rows", list);
        data.put("total", CollectionUtils.size(list));
        return new GeekResponse().success().data(data);
    }

    @GetMapping("delete/{id}")
    @Weblog(description = "踢出用户")
    @RequiresPermissions("user:kickout")
    @ApiOperation(value = "踢出用户", notes = "踢出用户")
    public GeekResponse forceLogout(@PathVariable String id) {
        sessionService.forceLogout(id);
        return new GeekResponse().success();
    }
}

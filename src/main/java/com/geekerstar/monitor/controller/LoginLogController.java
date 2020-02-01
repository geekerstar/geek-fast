package com.geekerstar.monitor.controller;


import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.geekerstar.common.annotation.ControllerEndPoint;
import com.geekerstar.common.annotation.Weblog;
import com.geekerstar.common.controller.BaseController;
import com.geekerstar.common.entity.GeekResponse;
import com.geekerstar.common.entity.QueryRequest;
import com.geekerstar.monitor.entity.LoginLog;
import com.geekerstar.monitor.service.ILoginLogService;
import com.wuwenze.poi.ExcelKit;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 登录日志表 前端控制器
 * </p>
 *
 * @author Geekerstar
 * @since 2020-02-01
 */
@Api("登录日志监控")
@RestController
@RequestMapping("/loginLog")
public class LoginLogController extends BaseController {

    @Autowired
    private ILoginLogService loginLogService;

    @GetMapping("list")
    @Weblog(description = "登录日志列表")
    @RequiresPermissions("loginlog:view")
    @ApiOperation(value = "登录日志列表", notes = "登录日志列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "loginLog", value = "登录日志", paramType = "body", required = true, defaultValue = ""),
            @ApiImplicitParam(name = "request", value = "查询请求", paramType = "body", required = true, defaultValue = "")
    })
    public GeekResponse loginLogList(LoginLog loginLog, QueryRequest request) {
        Map<String, Object> dataTable = getDataTable(this.loginLogService.findLoginLogs(loginLog, request));
        return new GeekResponse().success().data(dataTable);
    }

    @GetMapping("delete/{ids}")
    @Weblog(description = "删除登录日志")
    @RequiresPermissions("loginlog:delete")
    @ControllerEndPoint(exceptionMessage = "删除登录日志失败")
    @ApiOperation(value = "删除登录日志", notes = "删除登录日志")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "登录日志id", paramType = "query", required = true, defaultValue = "")
    })
    public GeekResponse deleteLogs(@NotBlank(message = "{required}") @PathVariable String ids) {
        String[] loginLogIds = ids.split(StringPool.COMMA);
        this.loginLogService.deleteLoginLogs(loginLogIds);
        return new GeekResponse().success();
    }

    @GetMapping("excel")
    @Weblog(description = "导出Excel")
    @RequiresPermissions("loginlog:export")
    @ControllerEndPoint(exceptionMessage = "导出Excel失败")
    @ApiOperation(value = "导出Excel", notes = "导出Excel")
    public void export(QueryRequest request, LoginLog loginLog, HttpServletResponse response) {
        List<LoginLog> loginLogs = this.loginLogService.findLoginLogs(loginLog, request).getRecords();
        ExcelKit.$Export(LoginLog.class, response).downXlsx(loginLogs, false);
    }

}

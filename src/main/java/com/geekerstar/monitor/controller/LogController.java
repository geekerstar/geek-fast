package com.geekerstar.monitor.controller;


import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.geekerstar.common.annotation.ControllerEndPoint;
import com.geekerstar.common.annotation.Weblog;
import com.geekerstar.common.controller.BaseController;
import com.geekerstar.common.entity.GeekResponse;
import com.geekerstar.common.entity.QueryRequest;
import com.geekerstar.monitor.entity.Log;
import com.geekerstar.monitor.service.ILogService;
import com.wuwenze.poi.ExcelKit;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

/**
 * 操作日志表 LogController 前端控制器
 *
 * @author Geekerstar
 * @since 2020-02-01
 */
@Api(tags = "日志监控")
@Slf4j
@RestController
@RequestMapping("/log")
@RequiredArgsConstructor
public class LogController extends BaseController {

    private final ILogService logService;

    @GetMapping("list")
    @Weblog(description = "日志列表")
    @RequiresPermissions("log:view")
    @ApiOperation(value = "日志列表", notes = "日志列表")
    public GeekResponse logList(Log log, QueryRequest request) {
        Map<String, Object> dataTable = getDataTable(this.logService.findLogs(log, request));
        return new GeekResponse().success().data(dataTable);
    }

    @GetMapping("delete/{ids}")
    @Weblog(description = "删除日志")
    @RequiresPermissions("log:delete")
    @ControllerEndPoint(exceptionMessage = "删除日志失败")
    @ApiOperation(value = "删除日志", notes = "删除日志")
    public GeekResponse deleteLogss(@NotBlank(message = "{required}") @PathVariable String ids) {
        String[] logIds = ids.split(StringPool.COMMA);
        this.logService.deleteLogs(logIds);
        return new GeekResponse().success();
    }

    @GetMapping("excel")
    @Weblog(description = "导出Excel")
    @RequiresPermissions("log:export")
    @ControllerEndPoint(exceptionMessage = "导出Excel失败")
    @ApiOperation(value = "导出Excel", notes = "导出Excel")
    public void export(QueryRequest request, Log lg, HttpServletResponse response) {
        List<Log> logs = this.logService.findLogs(lg, request).getRecords();
        ExcelKit.$Export(Log.class, response).downXlsx(logs, false);
    }

}

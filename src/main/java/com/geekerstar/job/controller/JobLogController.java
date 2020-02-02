package com.geekerstar.job.controller;


import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.geekerstar.common.annotation.ControllerEndPoint;
import com.geekerstar.common.controller.BaseController;
import com.geekerstar.common.entity.GeekResponse;
import com.geekerstar.common.entity.QueryRequest;
import com.geekerstar.job.entity.JobLog;
import com.geekerstar.job.service.IJobLogService;
import com.wuwenze.poi.ExcelKit;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
 * 调度日志表 前端控制器
 * </p>
 *
 * @author Geekerstar
 * @since 2020-02-02
 */
@Slf4j
@Validated
@RestController
@RequestMapping("jobLog")
public class JobLogController extends BaseController {

    @Autowired
    private IJobLogService jobLogService;

    @GetMapping
    @RequiresPermissions("job:log:view")
    public GeekResponse jobLogList(QueryRequest request, JobLog log) {
        Map<String, Object> dataTable = getDataTable(this.jobLogService.findJobLogs(request, log));
        return new GeekResponse().success().data(dataTable);
    }

    @GetMapping("delete/{jobIds}")
    @RequiresPermissions("job:log:delete")
    @ControllerEndPoint(exceptionMessage = "删除调度日志失败")
    public GeekResponse deleteJobLog(@NotBlank(message = "{required}") @PathVariable String jobIds) {
        String[] ids = jobIds.split(StringPool.COMMA);
        this.jobLogService.deleteJobLogs(ids);
        return new GeekResponse().success();
    }

    @GetMapping("excel")
    @RequiresPermissions("job:log:export")
    @ControllerEndPoint(exceptionMessage = "导出Excel失败")
    public void export(QueryRequest request, JobLog jobLog, HttpServletResponse response) {
        List<JobLog> jobLogs = this.jobLogService.findJobLogs(request, jobLog).getRecords();
        ExcelKit.$Export(JobLog.class, response).downXlsx(jobLogs, false);
    }

}

package com.geekerstar.job.controller;


import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.geekerstar.common.annotation.ControllerEndPoint;
import com.geekerstar.common.controller.BaseController;
import com.geekerstar.common.entity.GeekResponse;
import com.geekerstar.common.entity.QueryRequest;
import com.geekerstar.job.entity.Job;
import com.geekerstar.job.service.IJobService;
import com.wuwenze.poi.ExcelKit;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.quartz.CronExpression;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 定时任务表 前端控制器
 * </p>
 *
 * @author Geekerstar
 * @since 2020-02-02
 */
@Api(tags = "任务调度")
@Validated
@RestController
@RequestMapping("/job")
@RequiredArgsConstructor
public class JobController extends BaseController {

    private final IJobService jobService;

    @GetMapping
    @RequiresPermissions("job:view")
    @ApiOperation(value = "任务列表", notes = "任务列表")
    public GeekResponse jobList(QueryRequest request, Job job) {
        Map<String, Object> dataTable = getDataTable(this.jobService.findJobs(request, job));
        return new GeekResponse().success().data(dataTable);
    }

    @GetMapping("cron/check")
    @ApiOperation(value = "检查Cron表达式", notes = "检查Cron表达式")
    public boolean checkCron(String cron) {
        try {
            return CronExpression.isValidExpression(cron);
        } catch (Exception e) {
            return false;
        }
    }

    @PostMapping
    @RequiresPermissions("job:add")
    @ControllerEndPoint(operation = "新增定时任务", exceptionMessage = "新增定时任务失败")
    @ApiOperation(value = "新增定时任务", notes = "新增定时任务")
    public GeekResponse addJob(@Valid Job job) {
        this.jobService.createJob(job);
        return new GeekResponse().success();
    }

    @GetMapping("delete/{jobIds}")
    @RequiresPermissions("job:delete")
    @ControllerEndPoint(operation = "删除定时任务", exceptionMessage = "删除定时任务失败")
    @ApiOperation(value = "删除定时任务", notes = "删除定时任务")
    public GeekResponse deleteJob(@NotBlank(message = "{required}") @PathVariable String jobIds) {
        String[] ids = jobIds.split(StringPool.COMMA);
        this.jobService.deleteJobs(ids);
        return new GeekResponse().success();
    }

    @PostMapping("update")
    @ControllerEndPoint(operation = "修改定时任务", exceptionMessage = "修改定时任务失败")
    @ApiOperation(value = "修改定时任务", notes = "修改定时任务")
    public GeekResponse updateJob(@Valid Job job) {
        this.jobService.updateJob(job);
        return new GeekResponse().success();
    }

    @GetMapping("run/{jobIds}")
    @RequiresPermissions("job:run")
    @ControllerEndPoint(operation = "执行定时任务", exceptionMessage = "执行定时任务失败")
    @ApiOperation(value = "执行定时任务", notes = "执行定时任务")
    public GeekResponse runJob(@NotBlank(message = "{required}") @PathVariable String jobIds) {
        this.jobService.run(jobIds);
        return new GeekResponse().success();
    }

    @GetMapping("pause/{jobIds}")
    @RequiresPermissions("job:pause")
    @ControllerEndPoint(operation = "暂停定时任务", exceptionMessage = "暂停定时任务失败")
    @ApiOperation(value = "暂停定时任务", notes = "暂停定时任务")
    public GeekResponse pauseJob(@NotBlank(message = "{required}") @PathVariable String jobIds) {
        this.jobService.pause(jobIds);
        return new GeekResponse().success();
    }

    @GetMapping("resume/{jobIds}")
    @RequiresPermissions("job:resume")
    @ControllerEndPoint(operation = "恢复定时任务", exceptionMessage = "恢复定时任务失败")
    @ApiOperation(value = "恢复定时任务", notes = "恢复定时任务")
    public GeekResponse resumeJob(@NotBlank(message = "{required}") @PathVariable String jobIds) {
        this.jobService.resume(jobIds);
        return new GeekResponse().success();
    }

    @GetMapping("excel")
    @RequiresPermissions("job:export")
    @ControllerEndPoint(exceptionMessage = "导出Excel失败")
    @ApiOperation(value = "导出Excel失败", notes = "导出Excel失败")
    public void export(QueryRequest request, Job job, HttpServletResponse response) {
        List<Job> jobs = this.jobService.findJobs(request, job).getRecords();
        ExcelKit.$Export(Job.class, response).downXlsx(jobs, false);
    }
}

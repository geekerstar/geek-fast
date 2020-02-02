package com.geekerstar.job.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.geekerstar.common.entity.QueryRequest;
import com.geekerstar.job.entity.JobLog;

/**
 * <p>
 * 调度日志表 服务类
 * </p>
 *
 * @author Geekerstar
 * @since 2020-02-02
 */
public interface IJobLogService extends IService<JobLog> {

    IPage<JobLog> findJobLogs(QueryRequest request, JobLog jobLog);

    void saveJobLog(JobLog log);

    void deleteJobLogs(String[] jobLogIds);

}

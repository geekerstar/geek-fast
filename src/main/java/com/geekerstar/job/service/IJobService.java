package com.geekerstar.job.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.geekerstar.common.entity.QueryRequest;
import com.geekerstar.job.entity.Job;

/**
 * <p>
 * 定时任务表 服务类
 * </p>
 *
 * @author Geekerstar
 * @since 2020-02-02
 */
public interface IJobService extends IService<Job> {

    Job findJob(Long jobId);

    IPage<Job> findJobs(QueryRequest request, Job job);

    void createJob(Job job);

    void updateJob(Job job);

    void deleteJobs(String[] jobIds);

    int updateBatch(String jobIds, String status);

    void run(String jobIds);

    void pause(String jobIds);

    void resume(String jobIds);
}

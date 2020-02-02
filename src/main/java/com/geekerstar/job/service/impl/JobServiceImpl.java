package com.geekerstar.job.service.impl;

import com.geekerstar.job.entity.Job;
import com.geekerstar.job.mapper.JobMapper;
import com.geekerstar.job.service.IJobService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 定时任务表 服务实现类
 * </p>
 *
 * @author Geekerstar
 * @since 2020-02-02
 */
@Service
public class JobServiceImpl extends ServiceImpl<JobMapper, Job> implements IJobService {

}

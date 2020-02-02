package com.geekerstar.job.service.impl;

import com.geekerstar.job.entity.JobLog;
import com.geekerstar.job.mapper.JobLogMapper;
import com.geekerstar.job.service.IJobLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 调度日志表 服务实现类
 * </p>
 *
 * @author Geekerstar
 * @since 2020-02-02
 */
@Service
public class JobLogServiceImpl extends ServiceImpl<JobLogMapper, JobLog> implements IJobLogService {

}

package com.geekerstar.job.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.geekerstar.job.entity.Job;

import java.util.List;

/**
 * <p>
 * 定时任务表 Mapper 接口
 * </p>
 *
 * @author Geekerstar
 * @since 2020-02-02
 */
public interface JobMapper extends BaseMapper<Job> {

    /**
     * 获取定时任务列表
     *
     * @return 定时任务列表
     */
    List<Job> queryList();
}

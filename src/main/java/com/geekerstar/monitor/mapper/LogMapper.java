package com.geekerstar.monitor.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.geekerstar.monitor.entity.Log;
import org.springframework.stereotype.Repository;

/**
 * 操作日志表 LogMapper Mapper 接口
 *
 * @author Geekerstar
 * @since 2020-02-01
 */
@Repository
public interface LogMapper extends BaseMapper<Log> {

}

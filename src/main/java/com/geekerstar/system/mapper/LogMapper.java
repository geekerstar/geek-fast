package com.geekerstar.system.mapper;

import com.geekerstar.system.entity.Log;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * 操作日志表 LogMapper Mapper 接口
 *
 * @author Geekerstar
 * @since 2020-01-31
 */
@Repository
public interface LogMapper extends BaseMapper<Log> {

}

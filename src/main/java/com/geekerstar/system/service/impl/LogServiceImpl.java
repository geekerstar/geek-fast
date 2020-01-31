package com.geekerstar.system.service.impl;

import com.geekerstar.system.entity.Log;
import com.geekerstar.system.mapper.LogMapper;
import com.geekerstar.system.service.ILogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 操作日志表 LogServiceImpl 服务实现类
 *
 * @author Geekerstar
 * @since 2020-01-31
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements ILogService {

}

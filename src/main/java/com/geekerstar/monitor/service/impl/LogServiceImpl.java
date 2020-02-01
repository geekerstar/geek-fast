package com.geekerstar.monitor.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geekerstar.monitor.entity.Log;
import com.geekerstar.monitor.mapper.LogMapper;
import com.geekerstar.monitor.service.ILogService;
import org.springframework.stereotype.Service;

/**
 * 操作日志表 LogServiceImpl 服务实现类
 *
 * @author Geekerstar
 * @since 2020-02-01
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements ILogService {

}

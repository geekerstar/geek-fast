package com.geekerstar.monitor.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geekerstar.monitor.entity.LoginLog;
import com.geekerstar.monitor.mapper.LoginLogMapper;
import com.geekerstar.monitor.service.ILoginLogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 登录日志表 服务实现类
 * </p>
 *
 * @author Geekerstar
 * @since 2020-02-01
 */
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements ILoginLogService {

}

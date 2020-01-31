package com.geekerstar.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geekerstar.system.entity.User;
import com.geekerstar.system.mapper.UserMapper;
import com.geekerstar.system.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * 用户表 UserServiceImpl 服务实现类
 *
 * @author Geekerstar
 * @since 2020-01-31
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}

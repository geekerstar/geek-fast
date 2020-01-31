package com.geekerstar.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geekerstar.system.entity.Role;
import com.geekerstar.system.mapper.RoleMapper;
import com.geekerstar.system.service.IRoleService;
import org.springframework.stereotype.Service;

/**
 * 角色表 RoleServiceImpl 服务实现类
 *
 * @author Geekerstar
 * @since 2020-01-31
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}

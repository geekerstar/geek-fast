package com.geekerstar.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geekerstar.common.authentication.ShiroRealm;
import com.geekerstar.common.entity.GeekConstant;
import com.geekerstar.common.entity.QueryRequest;
import com.geekerstar.common.util.SortUtil;
import com.geekerstar.system.entity.Role;
import com.geekerstar.system.entity.RoleMenu;
import com.geekerstar.system.mapper.RoleMapper;
import com.geekerstar.system.service.IRoleMenuService;
import com.geekerstar.system.service.IRoleService;
import com.geekerstar.system.service.IUserRoleService;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * 角色表 RoleServiceImpl 服务实现类
 *
 * @author Geekerstar
 * @since 2020-01-31
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
@RequiredArgsConstructor
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    private final IRoleMenuService roleMenuService;

    private final IUserRoleService userRoleService;

    private final ShiroRealm shiroRealm;

    @Override
//    @Cacheable(value = {"Cache:findUserRole"}, keyGenerator = "simpleKeyGenerator")
    public List<Role> findUserRole(String username) {
        return this.baseMapper.findUserRole(username);
    }

    @Override
//    @Cacheable(value = {"Cache:findRoles"}, keyGenerator = "simpleKeyGenerator")
    public List<Role> findRoles(Role role) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(role.getRoleName())) {
            queryWrapper.lambda().like(Role::getRoleName, role.getRoleName());
        }
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
//    @Cacheable(value = {"Cache:findRoles1"}, keyGenerator = "simpleKeyGenerator")
    public IPage<Role> findRoles(Role role, QueryRequest request) {
        Page<Role> page = new Page<>(request.getPageNum(), request.getPageSize());
        SortUtil.handlePageSort(request, page, "createTime", GeekConstant.ORDER_DESC, false);
        return this.baseMapper.findRolePage(page, role);
    }

    @Override
//    @Cacheable(value = {"Cache:findByName"}, keyGenerator = "simpleKeyGenerator")
    public Role findByName(String roleName) {
        return this.baseMapper.selectOne(new QueryWrapper<Role>().lambda().eq(Role::getRoleName, roleName));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createRole(Role role) {
        role.setCreateTime(LocalDateTime.now());
        this.baseMapper.insert(role);
        this.saveRoleMenus(role);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRole(Role role) {
        role.setModifyTime(LocalDateTime.now());
        this.updateById(role);
        List<String> roleIdList = Lists.newArrayList();
        roleIdList.add(String.valueOf(role.getRoleId()));
        this.roleMenuService.deleteRoleMenusByRoleId(roleIdList);
        saveRoleMenus(role);
        shiroRealm.clearCache();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteRoles(String roleIds) {
        List<String> list = Arrays.asList(roleIds.split(StringPool.COMMA));
        this.baseMapper.delete(new QueryWrapper<Role>().lambda().in(Role::getRoleId, list));
        this.roleMenuService.deleteRoleMenusByRoleId(list);
        this.userRoleService.deleteUserRolesByRoleId(list);
    }

    private void saveRoleMenus(Role role) {
        if (StringUtils.isNotBlank(role.getMenuIds())) {
            String[] menuIds = role.getMenuIds().split(StringPool.COMMA);
            List<RoleMenu> roleMenus = Lists.newArrayList();
            Arrays.stream(menuIds).forEach(menuId -> {
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setMenuId(Long.valueOf(menuId));
                roleMenu.setRoleId(role.getRoleId());
                roleMenus.add(roleMenu);
            });
            roleMenuService.saveBatch(roleMenus);
        }
    }
}

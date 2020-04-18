package com.geekerstar.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.geekerstar.system.entity.RoleMenu;

import java.util.List;

/**
 * 角色菜单关联表 IRoleMenuService 服务类
 *
 * @author Geekerstar
 * @since 2020-01-31
 */
public interface IRoleMenuService extends IService<RoleMenu> {

    /**
     * 通过角色 id 删除
     *
     * @param roleIds 角色 id
     */
    void deleteRoleMenusByRoleId(List<String> roleIds);

    /**
     * 通过菜单（按钮）id 删除
     *
     * @param menuIds 菜单（按钮）id
     */
    void deleteRoleMenusByMenuId(List<String> menuIds);

}

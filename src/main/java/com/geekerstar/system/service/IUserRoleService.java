package com.geekerstar.system.service;

import com.geekerstar.system.entity.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 用户角色关联表 IUserRoleService 服务类
*
* @author Geekerstar
* @since 2020-01-31
*/
@Service
public interface IUserRoleService extends IService<UserRole> {
    /**
     * 通过角色 id 删除
     *
     * @param roleIds 角色 id
     */
    void deleteUserRolesByRoleId(List<String> roleIds);

    /**
     * 通过用户 id 删除
     *
     * @param userIds 用户 id
     */
    void deleteUserRolesByUserId(List<String> userIds);
}

package com.geekerstar.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.geekerstar.common.entity.QueryRequest;
import com.geekerstar.system.entity.User;
import org.springframework.stereotype.Service;

/**
 * 用户表 IUserService 服务类
 *
 * @author Geekerstar
 * @since 2020-01-31
 */
@Service
public interface IUserService extends IService<User> {

    /**
     * 通过用户名查找用户详细信息
     *
     * @param username 用户名
     * @return 用户信息
     */
    User findUserDetailList(String username);

    /**
     * 查找用户详细信息
     *
     * @param request request
     * @param user    用户对象，用于传递查询条件
     * @return IPage
     */
    IPage<User> findUserDetailList(User user, QueryRequest request);

    /**
     * 通过用户名查找用户
     *
     * @param username 用户名
     * @return 用户
     */
    User findByName(String username);

    /**
     * 新增用户
     *
     * @param user user
     */
    void createUser(User user);

    /**
     * 删除用户
     *
     * @param ids 用户 id数组
     */
    void deleteUsers(String[] ids);

    /**
     * 修改用户
     *
     * @param user user
     */
    void updateUser(User user);

    /**
     * 重置密码
     *
     * @param usernames 用户名数组
     */
    void resetPassword(String[] usernames);

    /**
     * 修改密码
     *
     * @param username 用户名
     * @param password 新密码
     */
    void updatePassword(String username, String password);

    /**
     * 更新用户头像
     *
     * @param username 用户名
     * @param avatar   用户头像
     */
    void updateAvatar(String username, String avatar);

    /**
     * 修改用户系统配置（个性化配置）
     *
     * @param username 用户名称
     * @param theme    主题风格
     * @param isTab    是否开启 TAB
     */
    void updateTheme(String username, String theme, String isTab);

    void updateProfile(User user);
}
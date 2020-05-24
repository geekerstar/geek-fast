package com.geekerstar.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geekerstar.common.authentication.ShiroRealm;
import com.geekerstar.common.entity.GeekConstant;
import com.geekerstar.common.entity.QueryRequest;
import com.geekerstar.common.exception.GeekException;
import com.geekerstar.common.util.GeekUtil;
import com.geekerstar.common.util.Md5Util;
import com.geekerstar.common.util.SortUtil;
import com.geekerstar.system.entity.User;
import com.geekerstar.system.entity.UserRole;
import com.geekerstar.system.mapper.UserMapper;
import com.geekerstar.system.service.IUserRoleService;
import com.geekerstar.system.service.IUserService;
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
 * 用户表 UserServiceImpl 服务实现类
 *
 * @author Geekerstar
 * @since 2020-01-31
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private final IUserRoleService userRoleService;

    private final ShiroRealm shiroRealm;

    @Override
//    @Cacheable(value = {"Cache:findUserDetailList"}, keyGenerator = "simpleKeyGenerator")
    public User findUserDetailList(String username) {
        User user = new User();
        user.setUsername(username);
        List<User> userList = this.baseMapper.findUserDetail(user);
        return CollectionUtils.isNotEmpty(userList) ? userList.get(0) : null;
    }

    @Override
//    @Cacheable(value = {"Cache:findUserDetailList1"}, keyGenerator = "simpleKeyGenerator")
    public IPage<User> findUserDetailList(User user, QueryRequest request) {
        Page<User> page = new Page<>(request.getPageNum(), request.getPageSize());
        SortUtil.handlePageSort(request, page, "userId", GeekConstant.ORDER_ASC, false);
        return this.baseMapper.findUserDetailPage(page, user);
    }

    @Override
//    @Cacheable(value = {"Cache:findByName"}, keyGenerator = "simpleKeyGenerator")
    public User findByName(String username) {
        return this.baseMapper.findByName(username);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createUser(User user) {
        user.setCreateTime(LocalDateTime.now());
        user.setStatus(User.STATUS_VALID);
        user.setAvatar(User.DEFAULT_AVATAR);
        user.setTheme(User.THEME_BLACK);
        user.setIsTab(User.TAB_OPEN);
        user.setPassword(Md5Util.encrypt(user.getUsername(), User.DEFAULT_PASSWORD));
        save(user);
        // 保存用户角色
        String[] roles = user.getRoleId().split(StringPool.COMMA);
        setUserRoles(user, roles);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteUsers(String[] ids) {
        List<String> list = Arrays.asList(ids);
        // 删除用户
        this.removeByIds(list);
        // 删除关联角色
        this.userRoleService.deleteUserRolesByUserId(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUser(User user) {
        String username = user.getUsername();
        // 更新用户
        user.setPassword(null);
        user.setUsername(null);
        user.setModifyTime(LocalDateTime.now());
        updateById(user);
        // 更新关联角色
        this.userRoleService.remove(new LambdaQueryWrapper<UserRole>().eq(UserRole::getUserId, user.getUserId()));
        String[] roles = user.getRoleId().split(StringPool.COMMA);
        setUserRoles(user, roles);
        User currentUser = GeekUtil.getCurrentUser();
        if (StringUtils.equalsIgnoreCase(currentUser.getUsername(), username)) {
            shiroRealm.clearCache();
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void resetPassword(String[] usernames) {
        Arrays.stream(usernames).forEach(username -> {
            User user = new User();
            user.setPassword(Md5Util.encrypt(username, User.DEFAULT_PASSWORD));
            this.baseMapper.update(user, new LambdaQueryWrapper<User>().eq(User::getUsername, username));
        });
    }

    @Override
    public void updatePassword(String username, String password) {
        User user = new User();
        user.setPassword(Md5Util.encrypt(username, password));
        user.setModifyTime(LocalDateTime.now());
        this.baseMapper.update(user, new LambdaQueryWrapper<User>().eq(User::getUsername, username));
    }

    @Override
    public void updateAvatar(String username, String avatar) {
        User user = new User();
        user.setAvatar(avatar);
        user.setModifyTime(LocalDateTime.now());
        this.baseMapper.update(user, new LambdaQueryWrapper<User>().eq(User::getUsername, username));
    }

    @Override
    public void updateTheme(String username, String theme, String isTab) {
        User user = new User();
        user.setTheme(theme);
        user.setIsTab(isTab);
        user.setModifyTime(LocalDateTime.now());
        this.baseMapper.update(user, new LambdaQueryWrapper<User>().eq(User::getUsername, username));
    }

    @Override
    public void updateProfile(User user) {
        user.setUsername(null);
        user.setRoleId(null);
        user.setPassword(null);
        if (isCurrentUser(user.getId())) {
            updateById(user);
        } else {
            throw new GeekException("您无权修改别人的账号信息！");
        }
    }

    @Override
    public void regist(String username, String password) {
        User user = new User();
        user.setPassword(Md5Util.encrypt(username, password));
        user.setUsername(username);
        user.setCreateTime(LocalDateTime.now());
        user.setStatus(User.STATUS_VALID);
        user.setSex(User.SEX_UNKNOW);
        user.setAvatar(User.DEFAULT_AVATAR);
        user.setTheme(User.THEME_WHITE);
        user.setIsTab(User.TAB_OPEN);
        user.setDescription("注册用户");
        this.save(user);
        UserRole ur = new UserRole();
        ur.setUserId(user.getUserId());
        ur.setRoleId(GeekConstant.REGISTER_ROLE_ID);
        this.userRoleService.save(ur);
    }

    @Override
    public void updateLoginTime(String username) {
        User user = new User();
        user.setLastLoginTime(LocalDateTime.now());
        this.baseMapper.update(user, new LambdaQueryWrapper<User>().eq(User::getUsername, username));
    }

    private void setUserRoles(User user, String[] roles) {
        List<UserRole> userRoles = Lists.newArrayList();
        Arrays.stream(roles).forEach(roleId -> {
            UserRole userRole = new UserRole();
            userRole.setUserId(user.getUserId());
            userRole.setRoleId(Long.valueOf(roleId));
            userRoles.add(userRole);
        });
        userRoleService.saveBatch(userRoles);
    }

    private boolean isCurrentUser(Long id) {
        User currentUser = GeekUtil.getCurrentUser();
        return currentUser.getUserId().equals(id);
    }
}

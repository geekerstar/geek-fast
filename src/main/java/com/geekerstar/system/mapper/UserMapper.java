package com.geekerstar.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.geekerstar.system.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户表 UserMapper Mapper 接口
 *
 * @author Geekerstar
 * @since 2020-01-31
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    /**
     * 查找用户详细信息
     *
     * @param user 用户对象，用于传递查询条件
     * @return List<User>
     */
    List<User> findUserDetail(User user);

    /**
     * 通过用户名查找用户
     *
     * @param username 用户名
     * @return 用户
     */
    User findByName(String username);

    /**
     * 查找用户详细信息
     *
     * @param page 分页对象
     * @param user 用户对象，用于传递查询条件
     * @return Ipage
     */
    IPage<User> findUserDetailPage(Page<User> page, User user);
}

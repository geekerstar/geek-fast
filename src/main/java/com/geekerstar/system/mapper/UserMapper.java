package com.geekerstar.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.geekerstar.system.entity.User;
import org.springframework.stereotype.Repository;

/**
 * 用户表 UserMapper Mapper 接口
 *
 * @author Geekerstar
 * @since 2020-01-31
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

}

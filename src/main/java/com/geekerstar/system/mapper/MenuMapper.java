package com.geekerstar.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.geekerstar.system.entity.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 菜单表 MenuMapper Mapper 接口
 *
 * @author Geekerstar
 * @since 2020-01-31
 */
@Repository
public interface MenuMapper extends BaseMapper<Menu> {
    /**
     * 查找用户权限集
     *
     * @param username 用户名
     * @return 用户权限集合
     */
    List<Menu> findUserPermissions(String username);

    /**
     * 查找用户菜单集合
     *
     * @param username 用户名
     * @return 用户菜单集合
     */
    List<Menu> findUserMenus(String username);
}

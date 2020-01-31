package com.geekerstar.system.service.impl;

import com.geekerstar.system.entity.Menu;
import com.geekerstar.system.mapper.MenuMapper;
import com.geekerstar.system.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 菜单表 MenuServiceImpl 服务实现类
 *
 * @author Geekerstar
 * @since 2020-01-31
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

}

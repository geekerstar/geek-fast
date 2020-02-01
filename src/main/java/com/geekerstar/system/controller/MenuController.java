package com.geekerstar.system.controller;


import com.geekerstar.common.annotation.ControllerEndPoint;
import com.geekerstar.common.controller.BaseController;
import com.geekerstar.common.entity.GeekResponse;
import com.geekerstar.common.entity.MenuTree;
import com.geekerstar.common.exception.GeekException;
import com.geekerstar.system.entity.Menu;
import com.geekerstar.system.entity.User;
import com.geekerstar.system.service.IMenuService;
import com.wuwenze.poi.ExcelKit;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * 菜单表 MenuController 前端控制器
 *
 * @author Geekerstar
 * @since 2020-01-31
 */
@Slf4j
@RestController
@RequestMapping("menu")
public class MenuController extends BaseController {

    @Autowired
    private IMenuService menuService;

    @GetMapping("{username}")
    public GeekResponse getUserMenus(@NotBlank(message = "{required}") @PathVariable String username) throws GeekException {
        User currentUser = getCurrentUser();
        if (!StringUtils.equalsIgnoreCase(username, currentUser.getUsername()))
            throw new GeekException("您无权获取别人的菜单");
        MenuTree<Menu> userMenus = this.menuService.findUserMenus(username);
        return new GeekResponse().data(userMenus);
    }

    @GetMapping("tree")
    @ControllerEndPoint(exceptionMessage = "获取菜单树失败")
    public GeekResponse getMenuTree(Menu menu) {
        MenuTree<Menu> menus = this.menuService.findMenus(menu);
        return new GeekResponse().success().data(menus.getChilds());
    }

    @PostMapping
    @RequiresPermissions("menu:add")
    @ControllerEndPoint(operation = "新增菜单/按钮", exceptionMessage = "新增菜单/按钮失败")
    public GeekResponse addMenu(@Valid Menu menu) {
        this.menuService.createMenu(menu);
        return new GeekResponse().success();
    }

    @GetMapping("delete/{menuIds}")
    @RequiresPermissions("menu:delete")
    @ControllerEndPoint(operation = "删除菜单/按钮", exceptionMessage = "删除菜单/按钮失败")
    public GeekResponse deleteMenus(@NotBlank(message = "{required}") @PathVariable String menuIds) {
        this.menuService.deleteMeuns(menuIds);
        return new GeekResponse().success();
    }

    @PostMapping("update")
    @RequiresPermissions("menu:update")
    @ControllerEndPoint(operation = "修改菜单/按钮", exceptionMessage = "修改菜单/按钮失败")
    public GeekResponse updateMenu(@Valid Menu menu) {
        this.menuService.updateMenu(menu);
        return new GeekResponse().success();
    }

    @GetMapping("excel")
    @RequiresPermissions("menu:export")
    @ControllerEndPoint(exceptionMessage = "导出Excel失败")
    public void export(Menu menu, HttpServletResponse response) {
        List<Menu> menus = this.menuService.findMenuList(menu);
        ExcelKit.$Export(Menu.class, response).downXlsx(menus, false);
    }

}

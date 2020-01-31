package com.geekerstar.system.controller;


import com.geekerstar.common.annotation.ControllerEndPoint;
import com.geekerstar.common.entity.GeekResponse;
import com.geekerstar.common.exception.GeekException;
import com.geekerstar.system.entity.User;
import com.geekerstar.system.service.IMenuService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

/**
 * 菜单表 MenuController 前端控制器
 *
 * @author Geekerstar
 * @since 2020-01-31
 */
@Slf4j
@RestController
@RequestMapping("menu")
public class MenuController {

    @Autowired
    private IMenuService menuService;


    @GetMapping("{username}")
    @ControllerEndPoint(exceptionMessage = "获取用户菜单失败")
    @ApiOperation(value = "获取用户菜单", notes = "获取用户菜单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", paramType = "query", required = true, defaultValue = "")
    })
    public GeekResponse getUserMenus(@NotBlank(message = "{required}") @PathVariable String username) throws GeekException {
        return null;
    }

}

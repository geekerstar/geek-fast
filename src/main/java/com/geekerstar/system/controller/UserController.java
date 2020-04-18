package com.geekerstar.system.controller;


import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.geekerstar.common.annotation.ControllerEndPoint;
import com.geekerstar.common.annotation.Weblog;
import com.geekerstar.common.controller.BaseController;
import com.geekerstar.common.entity.GeekResponse;
import com.geekerstar.common.entity.QueryRequest;
import com.geekerstar.common.exception.GeekException;
import com.geekerstar.common.util.Md5Util;
import com.geekerstar.system.entity.User;
import com.geekerstar.system.service.IUserService;
import com.wuwenze.poi.ExcelKit;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

/**
 * 用户表 UserController 前端控制器
 *
 * @author Geekerstar
 * @since 2020-01-31
 */
@Api(tags = "用户管理")
@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController extends BaseController {

    private final IUserService userService;

    @GetMapping("{usernmae}")
    @Weblog(description = "获取用户信息")
    @ControllerEndPoint(exceptionMessage = "获取用户信息失败")
    @ApiOperation(value = "获取用户信息", notes = "获取用户信息")
    public User getUser(@NotBlank(message = "{required}") @PathVariable String username) {
        return this.userService.findUserDetailList(username);
    }

    @GetMapping("check/{username}")
    @Weblog(description = "检查用户名")
    @ApiOperation(value = "检查用户名", notes = "检查用户名")
    public Boolean checkUserName(@NotBlank(message = "{required}") @PathVariable String username, String userId) {
        return this.userService.findByName(username) == null || StringUtils.isNotBlank(userId);
    }

    @GetMapping("list")
    @Weblog(description = "用户列表")
    @RequiresPermissions("user:view")
    @ApiOperation(value = "用户列表", notes = "用户列表")
    public GeekResponse userList(User user, QueryRequest request) {
        Map<String, Object> dataTable = getDataTable(this.userService.findUserDetailList(user, request));
        return new GeekResponse().success().data(dataTable);
    }

    @PostMapping
    @Weblog(description = "新增用户")
    @RequiresPermissions("user:add")
    @ControllerEndPoint(operation = "新增用户", exceptionMessage = "新增用户失败")
    @ApiOperation(value = "新增用户", notes = "新增用户")
    public GeekResponse addUser(@Valid User user) {
        this.userService.createUser(user);
        return new GeekResponse().success();
    }

    @GetMapping("delete/{userIds}")
    @Weblog(description = "删除用户")
    @RequiresPermissions("user:delete")
    @ControllerEndPoint(operation = "删除用户", exceptionMessage = "删除用户失败")
    @ApiOperation(value = "删除用户", notes = "删除用户")
    public GeekResponse deleteUsers(@NotBlank(message = "{required}") @PathVariable String userIds) {
        String[] ids = userIds.split(StringPool.COMMA);
        this.userService.deleteUsers(ids);
        return new GeekResponse().success();
    }

    @PostMapping("update")
    @Weblog(description = "修改用户")
    @RequiresPermissions("user:update")
    @ControllerEndPoint(operation = "修改用户", exceptionMessage = "修改用户失败")
    @ApiOperation(value = "修改用户", notes = "修改用户")
    public GeekResponse updateUser(@Valid User user) {
        if (user.getUserId() == null) {
            throw new GeekException("用户ID为空");
        }
        this.userService.updateUser(user);
        return new GeekResponse().success();
    }

    @PostMapping("password/reset/{usernames}")
    @Weblog(description = "重置密码")
    @RequiresPermissions("user:password:reset")
    @ControllerEndPoint(exceptionMessage = "重置用户密码失败")
    @ApiOperation(value = "重置密码", notes = "重置密码")
    public GeekResponse resetPassword(@NotBlank(message = "{required}") @PathVariable String usernames) {
        String[] usernameArr = usernames.split(StringPool.COMMA);
        this.userService.resetPassword(usernameArr);
        return new GeekResponse().success();
    }

    @PostMapping("password/update")
    @Weblog(description = "修改密码")
    @ControllerEndPoint(exceptionMessage = "修改密码失败")
    @ApiOperation(value = "修改密码", notes = "修改密码")
    public GeekResponse updatePassword(
            @NotBlank(message = "{required}") String oldPassword,
            @NotBlank(message = "{required}") String newPassword) {
        User user = getCurrentUser();
        if (!StringUtils.equals(user.getPassword(), Md5Util.encrypt(user.getUsername(), oldPassword))) {
            throw new GeekException("原密码不正确");
        }
        userService.updatePassword(user.getUsername(), newPassword);
        return new GeekResponse().success();
    }

    @GetMapping("avatar/{image}")
    @Weblog(description = "修改头像")
    @ControllerEndPoint(exceptionMessage = "修改头像失败")
    @ApiOperation(value = "修改头像", notes = "修改头像")
    public GeekResponse updateAvatar(@NotBlank(message = "{required}") @PathVariable String image) {
        User user = getCurrentUser();
        this.userService.updateAvatar(user.getUsername(), image);
        return new GeekResponse().success();
    }

    @PostMapping("theme/update")
    @Weblog(description = "修改系统配置")
    @ControllerEndPoint(exceptionMessage = "修改系统配置失败")
    @ApiOperation(value = "修改系统配置", notes = "修改系统配置")
    public GeekResponse updateTheme(String theme, String isTab) {
        User user = getCurrentUser();
        this.userService.updateTheme(user.getUsername(), theme, isTab);
        return new GeekResponse().success();
    }

    @PostMapping("profile/update")
    @Weblog(description = "修改个人信息")
    @ControllerEndPoint(exceptionMessage = "修改个人信息失败")
    @ApiOperation(value = "修改个人信息", notes = "修改个人信息")
    public GeekResponse updateProfile(User user) throws GeekException {
        User currentUser = getCurrentUser();
        user.setUserId(currentUser.getUserId());
        this.userService.updateProfile(user);
        return new GeekResponse().success();
    }

    @GetMapping("excel")
    @Weblog(description = "导出Excel")
    @RequiresPermissions("user:export")
    @ControllerEndPoint(exceptionMessage = "导出Excel失败")
    @ApiOperation(value = "导出Excel", notes = "导出Excel")
    public void export(QueryRequest queryRequest, User user, HttpServletResponse response) {
        List<User> users = this.userService.findUserDetailList(user, queryRequest).getRecords();
        ExcelKit.$Export(User.class, response).downXlsx(users, false);
    }
}

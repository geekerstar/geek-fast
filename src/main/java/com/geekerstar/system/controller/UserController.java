package com.geekerstar.system.controller;


import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.geekerstar.common.annotation.ControllerEndPoint;
import com.geekerstar.common.controller.BaseController;
import com.geekerstar.common.entity.GeekResponse;
import com.geekerstar.common.entity.QueryRequest;
import com.geekerstar.common.exception.GeekException;
import com.geekerstar.common.util.MD5Util;
import com.geekerstar.system.entity.User;
import com.geekerstar.system.service.IUserService;
import com.wuwenze.poi.ExcelKit;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

import static com.geekerstar.common.util.GeekUtil.getCurrentUser;

/**
 * 用户表 UserController 前端控制器
 *
 * @author Geekerstar
 * @since 2020-01-31
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    @GetMapping("{usernmae}")
    @ControllerEndPoint(exceptionMessage = "获取用户信息失败")
    @ApiOperation(value = "获取用户信息", notes = "获取用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", paramType = "query", required = true, defaultValue = "")
    })
    public User getUser(@NotBlank(message = "{required}") @PathVariable String username){
        return this.userService.findUserDetailList(username);
    }

    @GetMapping("check/{username}")
    public Boolean checkUserName(@NotBlank(message = "{required}") @PathVariable String username, String userId){
        return this.userService.findByName(username) == null || StringUtils.isNotBlank(userId);
    }

    @GetMapping("list")
    @RequiresPermissions("user:view")
    public GeekResponse userList(User user, QueryRequest request){
        Map<String, Object> dataTable = getDataTable(this.userService.findUserDetailList(user, request));
        return new GeekResponse().success().data(dataTable);
    }

    @PostMapping
    @RequiresPermissions("user:add")
    @ControllerEndPoint(operation = "新增用户", exceptionMessage = "新增用户失败")
    public GeekResponse addUser(@Valid User user) {
        this.userService.createUser(user);
        return new GeekResponse().success();
    }

    @GetMapping("delete/{userIds}")
    @RequiresPermissions("user:delete")
    @ControllerEndPoint(operation = "删除用户", exceptionMessage = "删除用户失败")
    public GeekResponse deleteUsers(@NotBlank(message = "{required}") @PathVariable String userIds) {
        String[] ids = userIds.split(StringPool.COMMA);
        this.userService.deleteUsers(ids);
        return new GeekResponse().success();
    }

    @PostMapping("update")
    @RequiresPermissions("user:update")
    @ControllerEndPoint(operation = "修改用户", exceptionMessage = "修改用户失败")
    public GeekResponse updateUser(@Valid User user) {
        if (user.getUserId() == null) {
            throw new GeekException("用户ID为空");
        }
        this.userService.updateUser(user);
        return new GeekResponse().success();
    }

    @PostMapping("password/reset/{usernames}")
    @RequiresPermissions("user:password:reset")
    @ControllerEndPoint(exceptionMessage = "重置用户密码失败")
    public GeekResponse resetPassword(@NotBlank(message = "{required}") @PathVariable String usernames) {
        String[] usernameArr = usernames.split(StringPool.COMMA);
        this.userService.resetPassword(usernameArr);
        return new GeekResponse().success();
    }

    @PostMapping("password/update")
    @ControllerEndPoint(exceptionMessage = "修改密码失败")
    public GeekResponse updatePassword(
            @NotBlank(message = "{required}") String oldPassword,
            @NotBlank(message = "{required}") String newPassword) {
        User user = getCurrentUser();
        if (!StringUtils.equals(user.getPassword(), MD5Util.encrypt(user.getUsername(), oldPassword))) {
            throw new GeekException("原密码不正确");
        }
        userService.updatePassword(user.getUsername(), newPassword);
        return new GeekResponse().success();
    }

    @GetMapping("avatar/{image}")
    @ControllerEndPoint(exceptionMessage = "修改头像失败")
    public GeekResponse updateAvatar(@NotBlank(message = "{required}") @PathVariable String image) {
        User user = getCurrentUser();
        this.userService.updateAvatar(user.getUsername(), image);
        return new GeekResponse().success();
    }

    @PostMapping("theme/update")
    @ControllerEndPoint(exceptionMessage = "修改系统配置失败")
    public GeekResponse updateTheme(String theme, String isTab) {
        User user = getCurrentUser();
        this.userService.updateTheme(user.getUsername(), theme, isTab);
        return new GeekResponse().success();
    }

    @PostMapping("profile/update")
    @ControllerEndPoint(exceptionMessage = "修改个人信息失败")
    public GeekResponse updateProfile(User user) throws GeekException {
        User currentUser = getCurrentUser();
        user.setUserId(currentUser.getUserId());
        this.userService.updateProfile(user);
        return new GeekResponse().success();
    }

    @GetMapping("excel")
    @RequiresPermissions("user:export")
    @ControllerEndPoint(exceptionMessage = "导出Excel失败")
    public void export(QueryRequest queryRequest, User user, HttpServletResponse response) {
        List<User> users = this.userService.findUserDetailList(user, queryRequest).getRecords();
        ExcelKit.$Export(User.class, response).downXlsx(users, false);
    }
}

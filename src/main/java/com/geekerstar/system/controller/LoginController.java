package com.geekerstar.system.controller;

import com.geekerstar.common.controller.BaseController;
import com.geekerstar.common.entity.GeekResponse;
import com.geekerstar.common.exception.GeekException;
import com.geekerstar.common.service.ValidateCodeService;
import com.geekerstar.common.util.Md5Util;
import com.geekerstar.monitor.entity.LoginLog;
import com.geekerstar.monitor.service.ILoginLogService;
import com.geekerstar.system.entity.User;
import com.geekerstar.system.service.IUserService;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author geekerstar
 * @date 2020/1/31 19:54
 * @description
 */
@Api(tags = "登录模块")
@Validated
@RestController
@RequiredArgsConstructor
public class LoginController extends BaseController {

    private final IUserService userService;

    private final ValidateCodeService validateCodeService;

    private final ILoginLogService loginLogService;

    @PostMapping("login")
//    @Weblog(description = "登录")
    @ApiOperation(value = "登录", notes = "登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", paramType = "query", required = true, defaultValue = ""),
            @ApiImplicitParam(name = "password", value = "密码", paramType = "query", required = true, defaultValue = ""),
            @ApiImplicitParam(name = "verifyCode", value = "验证码", paramType = "query", required = true, defaultValue = "")
    })
    public GeekResponse login(
            @NotBlank(message = "{required}") String username,
            @NotBlank(message = "{required}") String password,
            @NotBlank(message = "{required}") String verifyCode,
            boolean rememberMe, HttpServletRequest request) throws GeekException {
        HttpSession session = request.getSession();
        // 检查验证码
        validateCodeService.check(session.getId(), verifyCode);
        password = Md5Util.encrypt(username.toLowerCase(), password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
        super.login(token);
        // 保存登录日志
        LoginLog loginLog = new LoginLog();
        loginLog.setUsername(username);
        loginLog.setSystemBrowserInfo();
        this.loginLogService.saveLoginLog(loginLog);

        return new GeekResponse().success();
    }

    @PostMapping("regist")
//    @Weblog(description = "注册")
    @ApiOperation(value = "注册", notes = "注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", paramType = "query", required = true, defaultValue = ""),
            @ApiImplicitParam(name = "password", value = "密码", paramType = "query", required = true, defaultValue = "")
    })
    public GeekResponse regist(
            @NotBlank(message = "{required}") String username,
            @NotBlank(message = "{required}") String password) throws GeekException {
        User user = userService.findByName(username);
        if (user != null) {
            throw new GeekException("该用户名已存在");
        }
        this.userService.regist(username, password);
        return new GeekResponse().success();
    }

    @GetMapping("index/{username}")
    @ApiOperation(value = "获取首页信息", notes = "获取首页信息")
    public GeekResponse index(@NotBlank(message = "{required}") @PathVariable String username) {
        // 更新登录时间
        this.userService.updateLoginTime(username);
        Map<String, Object> data = Maps.newHashMapWithExpectedSize(5);
        // 获取系统访问记录
        Long totalVisitCount = this.loginLogService.findTotalVisitCount();
        data.put("totalVisitCount", totalVisitCount);
        Long todayVisitCount = this.loginLogService.findTodayVisitCount();
        data.put("todayVisitCount", todayVisitCount);
        Long todayIp = this.loginLogService.findTodayIp();
        data.put("todayIp", todayIp);
        // 获取近期系统访问记录
        List<Map<String, Object>> lastSevenVisitCount = this.loginLogService.findLastSevenDaysVisitCount(null);
        data.put("lastSevenVisitCount", lastSevenVisitCount);
        // 获取用户近期访问记录
        User param = new User();
        param.setUsername(username);
        List<Map<String, Object>> lastSevenUserVisitCount = this.loginLogService.findLastSevenDaysVisitCount(param);
        data.put("lastSevenUserVisitCount", lastSevenUserVisitCount);
        return new GeekResponse().success().data(data);
    }


    @GetMapping("images/captcha")
//    @Weblog(description = "获取验证码")
    @ApiOperation(value = "获取验证码", notes = "获取验证码")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException, GeekException {
        validateCodeService.create(request, response);
    }

}

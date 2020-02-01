package com.geekerstar.system.controller;

import com.geekerstar.common.controller.BaseController;
import com.geekerstar.common.entity.GeekResponse;
import com.geekerstar.common.exception.GeekException;
import com.geekerstar.common.service.ValidateCodeService;
import com.geekerstar.common.util.MD5Util;
import com.geekerstar.system.entity.User;
import com.geekerstar.system.service.IUserService;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotBlank;
import java.io.IOException;

/**
 * @author geekerstar
 * @date 2020/1/31 19:54
 * @description
 */
@Validated
@RestController
public class LoginController extends BaseController {

    @Autowired
    private IUserService userService;
    @Autowired
    private ValidateCodeService validateCodeService;

    @PostMapping("login")
    public GeekResponse login(
            @NotBlank(message = "{required}") String username,
            @NotBlank(message = "{required}") String password,
            @NotBlank(message = "{required}") String verifyCode,
            boolean rememberMe, HttpServletRequest request) throws GeekException {
        HttpSession session = request.getSession();
        validateCodeService.check(session.getId(), verifyCode);
        password = MD5Util.encrypt(username.toLowerCase(), password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
        super.login(token);

        return new GeekResponse().success();
    }

    @PostMapping("regist")
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


    @GetMapping("images/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException, GeekException {
        validateCodeService.create(request, response);
    }

}

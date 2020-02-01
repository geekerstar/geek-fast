package com.geekerstar.system.controller;

import com.geekerstar.common.authentication.ShiroHelper;
import com.geekerstar.common.controller.BaseController;
import com.geekerstar.common.entity.GeekConstant;
import com.geekerstar.common.util.GeekUtil;
import com.geekerstar.system.entity.User;
import com.geekerstar.system.service.IUserService;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.ExpiredSessionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author geekerstar
 * @date 2020/1/31 20:24
 * @description
 */
@Api("系统管理视图")
@Controller("systemView")
public class ViewController extends BaseController {
    @Autowired
    private IUserService userService;

    @Autowired
    private ShiroHelper shiroHelper;

    @GetMapping("login")
    @ResponseBody
    public Object login(HttpServletRequest request) {
        if (GeekUtil.isAjaxRequest(request)) {
            throw new ExpiredSessionException();
        } else {
            ModelAndView mav = new ModelAndView();
            mav.setViewName(GeekUtil.view("login"));
            return mav;
        }
    }

    @GetMapping("unauthorized")
    public String unauthorized() {
        return GeekUtil.view("error/403");
    }


    @GetMapping("/")
    public String redirectIndex() {
        return "redirect:/index";
    }

    @GetMapping("index")
    public String index(Model model) {
        AuthorizationInfo authorizationInfo = shiroHelper.getCurrentuserAuthorizationInfo();
        User user = super.getCurrentUser();
        User currentUserDetail = userService.findByName(user.getUsername());
        currentUserDetail.setPassword("It's a secret");
        model.addAttribute("user", currentUserDetail);
        model.addAttribute("permissions", authorizationInfo.getStringPermissions());
        model.addAttribute("roles", authorizationInfo.getRoles());
        return "index";
    }

    @GetMapping(GeekConstant.VIEW_PREFIX + "layout")
    public String layout() {
        return GeekUtil.view("layout");
    }

    @GetMapping(GeekConstant.VIEW_PREFIX + "password/update")
    public String passwordUpdate() {
        return GeekUtil.view("system/user/passwordUpdate");
    }

    @GetMapping(GeekConstant.VIEW_PREFIX + "user/profile")
    public String userProfile() {
        return GeekUtil.view("system/user/userProfile");
    }

    @GetMapping(GeekConstant.VIEW_PREFIX + "user/avatar")
    public String userAvatar() {
        return GeekUtil.view("system/user/avatar");
    }

    @GetMapping(GeekConstant.VIEW_PREFIX + "user/profile/update")
    public String profileUpdate() {
        return GeekUtil.view("system/user/profileUpdate");
    }

    @GetMapping(GeekConstant.VIEW_PREFIX + "system/user")
    @RequiresPermissions("user:view")
    public String systemUser() {
        return GeekUtil.view("system/user/user");
    }

    @GetMapping(GeekConstant.VIEW_PREFIX + "system/user/add")
    @RequiresPermissions("user:add")
    public String systemUserAdd() {
        return GeekUtil.view("system/user/userAdd");
    }

    @GetMapping(GeekConstant.VIEW_PREFIX + "system/user/detail/{username}")
    @RequiresPermissions("user:view")
    public String systemUserDetail(@PathVariable String username, Model model) {
        resolveUserModel(username, model, true);
        return GeekUtil.view("system/user/userDetail");
    }

    @GetMapping(GeekConstant.VIEW_PREFIX + "system/user/update/{username}")
    @RequiresPermissions("user:update")
    public String systemUserUpdate(@PathVariable String username, Model model) {
        resolveUserModel(username, model, false);
        return GeekUtil.view("system/user/userUpdate");
    }

    @GetMapping(GeekConstant.VIEW_PREFIX + "system/role")
    @RequiresPermissions("role:view")
    public String systemRole() {
        return GeekUtil.view("system/role/role");
    }

    @GetMapping(GeekConstant.VIEW_PREFIX + "system/menu")
    @RequiresPermissions("menu:view")
    public String systemMenu() {
        return GeekUtil.view("system/menu/menu");
    }

    @GetMapping(GeekConstant.VIEW_PREFIX + "system/dept")
    @RequiresPermissions("dept:view")
    public String systemDept() {
        return GeekUtil.view("system/dept/dept");
    }

    @RequestMapping(GeekConstant.VIEW_PREFIX + "index")
    public String pageIndex() {
        return GeekUtil.view("index");
    }

    @GetMapping(GeekConstant.VIEW_PREFIX + "404")
    public String error404() {
        return GeekUtil.view("error/404");
    }

    @GetMapping(GeekConstant.VIEW_PREFIX + "403")
    public String error403() {
        return GeekUtil.view("error/403");
    }

    @GetMapping(GeekConstant.VIEW_PREFIX + "500")
    public String error500() {
        return GeekUtil.view("error/500");
    }

    private void resolveUserModel(String username, Model model, Boolean transform) {
        User user = userService.findByName(username);
        model.addAttribute("user", user);
        if (transform) {
            String ssex = user.getSex();
            if (User.SEX_MALE.equals(ssex)) user.setSex("男");
            else if (User.SEX_FEMALE.equals(ssex)) user.setSex("女");
            else user.setSex("保密");
        }
        if (user.getLastLoginTime() != null)
            model.addAttribute("lastLoginTime", user.getLastLoginTime());
    }
}

package com.geekerstar.development.controller;

import com.geekerstar.common.entity.GeekConstant;
import com.geekerstar.common.util.GeekUtil;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author geekerstar
 * @date 2020/2/2 12:42
 * @description
 */
@Api(tags = "开发工具")
@Controller("developmentView")
@RequestMapping(GeekConstant.VIEW_PREFIX + "development")
public class ViewController {

    @GetMapping("cron")
    public String cron() {
        return GeekUtil.view("development/cron");
    }
}

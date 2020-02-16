package com.geekerstar.chat.controller;

import com.geekerstar.common.entity.GeekConstant;
import com.geekerstar.common.util.GeekUtil;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author geekerstar
 * @date 2020/2/9 21:31
 * @description
 */
@Api(tags = "即时通讯")
@Controller("chatView")
@RequestMapping(GeekConstant.VIEW_PREFIX + "chat")
@CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
public class ViewController {
    @GetMapping("chat")
    public String chat() {
        return GeekUtil.view("chat/chat");
    }

    @GetMapping("pc")
    public String pc() {
        return GeekUtil.view("chat/pc");
    }


    @GetMapping("mobile")
    public String mobile() {
        return GeekUtil.view("chat/mobile");
    }

    @GetMapping("index")
    public String index() {
        return GeekUtil.view("chat/index");
    }

}


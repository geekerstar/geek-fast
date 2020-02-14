package com.geekerstar.message.controller;

import com.geekerstar.common.entity.GeekConstant;
import com.geekerstar.common.util.GeekUtil;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author geekerstar
 * @date 2020/2/9 21:31
 * @description
 */
@Api(tags = "消息中心")
@Controller("messageView")
@RequestMapping(GeekConstant.VIEW_PREFIX + "message")
public class ViewController {
    @GetMapping("message")
    public String online() {
        return GeekUtil.view("message/message");
    }
}

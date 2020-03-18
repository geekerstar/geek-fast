package com.geekerstar.monitor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author geekerstar
 * @date 2020/3/17 16:49
 * @description
 */
@Controller
public class WebSSHController {
    @RequestMapping("/webssh")
    public String webssh(){
        return "webssh";
    }
}

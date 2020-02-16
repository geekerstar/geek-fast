package com.geekerstar.middleware.controller;

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
@Api(tags = "中间件管理")
@Controller("middleware")
@RequestMapping(GeekConstant.VIEW_PREFIX + "middleware")
public class ViewController {
    @GetMapping("rabbitmq")
    public String rabbitmq() {
        return GeekUtil.view("middleware/rabbitmq");
    }

    @GetMapping("elasticsearch")
    public String elasticsearch() {
        return GeekUtil.view("middleware/elasticsearch");
    }

    @GetMapping("kibana")
    public String kibana() {
        return GeekUtil.view("middleware/kibana");
    }
}

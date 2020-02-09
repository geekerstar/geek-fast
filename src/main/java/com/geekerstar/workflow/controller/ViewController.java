package com.geekerstar.workflow.controller;

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
@Api(tags = "工作流")
@Controller("workflowView")
@RequestMapping(GeekConstant.VIEW_PREFIX + "workflow")
public class ViewController {
    @GetMapping("workflow")
    public String online() {
        return GeekUtil.view("workflow/workflow");
    }
}

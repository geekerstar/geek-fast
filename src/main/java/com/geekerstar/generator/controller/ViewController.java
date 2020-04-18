package com.geekerstar.generator.controller;

import com.geekerstar.common.entity.GeekConstant;
import com.geekerstar.common.util.GeekUtil;
import com.geekerstar.generator.entity.GeneratorConfig;
import com.geekerstar.generator.service.IGeneratorConfigService;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author geekerstar
 * @date 2020/2/2 13:03
 * @description
 */
@Controller("generatorViews")
@RequestMapping(GeekConstant.VIEW_PREFIX + "generator")
@RequiredArgsConstructor
public class ViewController {

    private final IGeneratorConfigService generatorConfigService;

    @GetMapping("generator")
    @RequiresPermissions("generator:view")
    public String generator() {
        return GeekUtil.view("generator/generator");
    }

    @GetMapping("configure")
    @RequiresPermissions("generator:configure:view")
    public String generatorConfigure(Model model) {
        GeneratorConfig generatorConfig = generatorConfigService.findGeneratorConfig();
        model.addAttribute("config", generatorConfig);
        return GeekUtil.view("generator/configure");
    }
}

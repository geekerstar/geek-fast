package com.geekerstar.other.controller;

import com.geekerstar.common.entity.GeekConstant;
import com.geekerstar.common.util.GeekUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author geekerstar
 * @date 2020/2/1 18:39
 * @description
 */
@Controller("othersView")
@RequestMapping(GeekConstant.VIEW_PREFIX + "others")
public class ViewController {
    @GetMapping("geek/form")
    @RequiresPermissions("geek:form:view")
    public String geekForm() {
        return GeekUtil.view("others/geek/form");
    }

    @GetMapping("geek/form/group")
    @RequiresPermissions("geek:formgroup:view")
    public String geekFormGroup() {
        return GeekUtil.view("others/geek/formGroup");
    }

    @GetMapping("geek/tools")
    @RequiresPermissions("geek:tools:view")
    public String geekTools() {
        return GeekUtil.view("others/geek/tools");
    }

    @GetMapping("geek/icon")
    @RequiresPermissions("geek:icons:view")
    public String geekIcon() {
        return GeekUtil.view("others/geek/icon");
    }

    @GetMapping("geek/others")
    @RequiresPermissions("others:geek:others")
    public String geekOthers() {
        return GeekUtil.view("others/geek/others");
    }

    @GetMapping("apex/line")
    @RequiresPermissions("apex:line:view")
    public String apexLine() {
        return GeekUtil.view("others/apex/line");
    }

    @GetMapping("apex/area")
    @RequiresPermissions("apex:area:view")
    public String apexArea() {
        return GeekUtil.view("others/apex/area");
    }

    @GetMapping("apex/column")
    @RequiresPermissions("apex:column:view")
    public String apexColumn() {
        return GeekUtil.view("others/apex/column");
    }

    @GetMapping("apex/radar")
    @RequiresPermissions("apex:radar:view")
    public String apexRadar() {
        return GeekUtil.view("others/apex/radar");
    }

    @GetMapping("apex/bar")
    @RequiresPermissions("apex:bar:view")
    public String apexBar() {
        return GeekUtil.view("others/apex/bar");
    }

    @GetMapping("apex/mix")
    @RequiresPermissions("apex:mix:view")
    public String apexMix() {
        return GeekUtil.view("others/apex/mix");
    }

    @GetMapping("map")
    @RequiresPermissions("map:view")
    public String map() {
        return GeekUtil.view("others/map/gaodeMap");
    }

    @GetMapping("eximport")
    @RequiresPermissions("others:eximport:view")
    public String eximport() {
        return GeekUtil.view("others/eximport/eximport");
    }

    @GetMapping("eximport/result")
    public String eximportResult() {
        return GeekUtil.view("others/eximport/eximportResult");
    }
}

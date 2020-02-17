package com.geekerstar.development.controller;

import com.geekerstar.common.entity.GeekConstant;
import com.geekerstar.common.util.GeekUtil;
import com.geekerstar.job.entity.Job;
import com.geekerstar.job.service.IJobService;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.constraints.NotBlank;

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

package com.geekerstar.generator.controller;

import com.geekerstar.common.annotation.ControllerEndPoint;
import com.geekerstar.common.controller.BaseController;
import com.geekerstar.common.entity.GeekResponse;
import com.geekerstar.common.exception.GeekException;
import com.geekerstar.generator.entity.GeneratorConfig;
import com.geekerstar.generator.service.IGeneratorConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author geekerstar
 * @date 2020/2/2 13:01
 * @description
 */
@Api(tags = "代码生成器配置")
@Slf4j
@RestController
@RequestMapping("generatorConfig")
@RequiredArgsConstructor
public class GeneratorConfigController extends BaseController {

    private final IGeneratorConfigService generatorConfigService;

    @GetMapping
    @RequiresPermissions("generator:configure:view")
    @ApiOperation(value = "获取代码生成器配置信息", notes = "获取代码生成器配置信息")
    public GeekResponse getGeneratorConfig() {
        return new GeekResponse().success().data(generatorConfigService.findGeneratorConfig());
    }

    @PostMapping("update")
    @RequiresPermissions("generator:configure:update")
    @ControllerEndPoint(operation = "修改GeneratorConfig", exceptionMessage = "修改GeneratorConfig失败")
    @ApiOperation(value = "更新配置信息", notes = "更新配置信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "generatorConfig", value = "配置信息", paramType = "body", required = true, defaultValue = "")
    })
    public GeekResponse updateGeneratorConfig(@Valid GeneratorConfig generatorConfig) {
        if (StringUtils.isBlank(generatorConfig.getId())) {
            throw new GeekException("配置id不能为空");
        }
        this.generatorConfigService.updateGeneratorConfig(generatorConfig);
        return new GeekResponse().success();
    }
}

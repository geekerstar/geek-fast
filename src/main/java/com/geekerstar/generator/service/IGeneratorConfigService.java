package com.geekerstar.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.geekerstar.generator.entity.GeneratorConfig;

/**
 * <p>
 * 代码生成配置表 服务类
 * </p>
 *
 * @author Geekerstar
 * @since 2020-02-02
 */
public interface IGeneratorConfigService extends IService<GeneratorConfig> {

    /**
     * 查询
     *
     * @return GeneratorConfig
     */
    GeneratorConfig findGeneratorConfig();

    /**
     * 修改
     *
     * @param generatorConfig generatorConfig
     */
    void updateGeneratorConfig(GeneratorConfig generatorConfig);

}

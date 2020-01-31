package com.geekerstar.common.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * @author geekerstar
 * date: 2019/12/16 08:50
 * description:
 */
@Data
@SpringBootConfiguration
@PropertySource(value = {"classpath:geek.properties"})
@ConfigurationProperties(prefix = "geek")
public class GeekProperties {

    private ShiroProperties shiro = new ShiroProperties();

    private boolean autoOpenBrowser = true;

    private String[] autoOpenBrowserEnv = {};

    private SwaggerProperties swaggerProperties = new SwaggerProperties();

    private int maxBatchInsertNum = 1000;

    private ValidateCodeProperties code = new ValidateCodeProperties();
}

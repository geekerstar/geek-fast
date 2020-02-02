package com.geekerstar.common.configure;

import com.github.xiaoymin.knife4j.spring.annotations.EnableSwaggerBootstrapUi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author geekerstar
 * date: 2019-12-21 14:13
 * description:
 */
@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUi
@Profile({"dev", "prod"})
public class SwaggerConfigure {

    @Bean
    public Docket systemApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("1000.系统管理").select()
                .apis(RequestHandlerSelectors.basePackage("com.geekerstar.system.controller")).paths(PathSelectors.any()).build()
                .apiInfo(common("系统管理"));
    }

    @Bean
    public Docket monitorApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("1001.系统监控").select()
                .apis(RequestHandlerSelectors.basePackage("com.geekerstar.monitor.controller")).paths(PathSelectors.any()).build()
                .apiInfo(common("系统监控"));
    }

    @Bean
    public Docket jobApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("1002.任务调度").select()
                .apis(RequestHandlerSelectors.basePackage("com.geekerstar.job.controller")).paths(PathSelectors.any()).build()
                .apiInfo(common("任务调度"));
    }

    @Bean
    public Docket generatorApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("1003.代码生成").select()
                .apis(RequestHandlerSelectors.basePackage("com.geekerstar.generator.controller")).paths(PathSelectors.any()).build()
                .apiInfo(common("代码生成"));
    }

    @Bean
    public Docket otherApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("1004.其他模块").select()
                .apis(RequestHandlerSelectors.basePackage("com.geekerstar.other.controller")).paths(PathSelectors.any()).build()
                .apiInfo(common("其他模块"));
    }


    /**
     * 公共信息
     *
     * @param desc
     * @return
     */
    private ApiInfo common(String desc) {
        return new ApiInfoBuilder()
                // 大标题
                .title("Geek-Fast快速开发平台")
                // 详细描述
                .description(desc)
                // 版本
                .version("1.0")
                .termsOfServiceUrl("")
                .contact(new Contact("Geekerstar", "https://www.geekerstar.com", "247507792@qq.com"))
                .license("")
                .licenseUrl("")
                .build();
    }
}

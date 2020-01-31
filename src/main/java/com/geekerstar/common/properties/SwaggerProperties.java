package com.geekerstar.common.properties;

import lombok.Data;

/**
 * @author geekerstar
 * date: 2019/12/16 21:43
 * description:
 */
@Data
public class SwaggerProperties {

    private String basePackage;

    private String title;

    private String description;

    private String version;

    private String author;

    private String url;

    private String email;

    private String license;

    private String licenseUrl;

}

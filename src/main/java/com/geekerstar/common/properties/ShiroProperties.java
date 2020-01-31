package com.geekerstar.common.properties;

import lombok.Data;

/**
 * @author geekerstar
 * date: 2019/12/16 08:56
 * description:
 */
@Data
public class ShiroProperties {

    private long sessionTimeout;

    private int cookieTimeout;

    private String anonUrl;

    private String loginUrl;

    private String successUrl;

    private String logoutUrl;

    private String unauthorizedUrl;
}

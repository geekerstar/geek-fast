package com.geekerstar.common.properties;

import com.geekerstar.common.entity.ImageType;
import lombok.Data;

/**
 * @author geekerstar
 * date: 2019/12/16 21:46
 * description:
 */
@Data
public class ValidateCodeProperties {

    /**
     * 验证码有效时间，单位秒
     */
    private long time = 120L;

    /**
     * 验证码类型，可选值 png和 gif
     */
    private String type = ImageType.GIF;

    /**
     * 图片宽度，px
     */
    private Integer width = 130;

    /**
     * 图片高度，px
     */
    private Integer height = 48;

    /**
     * 验证码位数
     */
    private Integer length = 4;

    /**
     * 验证码值的类型
     * 1. 数字加字母
     * 2. 纯数字
     * 3. 纯字母
     */
    private Integer charType = 2;
}

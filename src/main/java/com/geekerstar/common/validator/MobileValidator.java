package com.geekerstar.common.validator;

import com.geekerstar.common.annotation.IsMobile;
import com.geekerstar.common.entity.Regexp;
import com.geekerstar.common.util.GeekUtil;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author geekerstar
 * date: 2019/12/15 19:46
 * description: 校验是否为合法的手机号码
 */
public class MobileValidator implements ConstraintValidator<IsMobile,String> {

    @Override
    public void initialize(IsMobile isMobile) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        try {
            if (StringUtils.isBlank(s)){
                return true;
            } else {
                String regex = Regexp.MOBILE_SIMPLE_REG;
                return GeekUtil.match(regex,s);
            }
        } catch (Exception e) {
            return false;
        }
    }
}

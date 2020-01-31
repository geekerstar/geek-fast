package com.geekerstar.common.validator;

import com.geekerstar.common.annotation.IsCron;
import org.quartz.CronExpression;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author geekerstar
 * date: 2019/12/15 19:39
 * description: 校验是否为合法的 Cron表达式
 */
public class CronValidator implements ConstraintValidator<IsCron, String> {

    @Override
    public void initialize(IsCron isCron) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        try {
            return CronExpression.isValidExpression(s);
        } catch (Exception e) {
            return false;
        }
    }
}

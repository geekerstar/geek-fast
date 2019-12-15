package com.geekerstar.common.aspect;

import com.geekerstar.common.annotation.ControllerEndPoint;
import com.geekerstar.common.exception.GeekException;
import com.geekerstar.common.util.GeekUtil;
import com.geekerstar.common.util.HttpContextUtil;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author geekerstar
 * date: 2019/12/15 20:18
 * description:
 */
@Aspect
@Component
public class ControllerEndpointAspect extends AspectSupport{

    @Pointcut("@annotation(com.geekerstar.common.annotation.ControllerEndPoint)")
    public void pointcut(){}

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point){
        Object result;
        Method targetMethod = resolveMethod(point);
        ControllerEndPoint annotation = targetMethod.getAnnotation(ControllerEndPoint.class);
        String operation = annotation.operation();
        long start = System.currentTimeMillis();
        try {
            result = point.proceed();
            if (StringUtils.isNotBlank(operation)){
                HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
                // todo 记录日志
            }
            return result;
        } catch (Throwable throwable) {
            String exceptionMessage = annotation.exceptionMessage();
            String message = throwable.getMessage();
            String error = GeekUtil.containChinese(message) ? exceptionMessage + "，" + message : exceptionMessage;
            throw new GeekException(error);
        }
    }

}

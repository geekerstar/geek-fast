package com.geekerstar.common.aspect;

import com.geekerstar.common.annotation.ControllerEndPoint;
import com.geekerstar.common.exception.GeekException;
import com.geekerstar.common.util.GeekUtil;
import com.geekerstar.common.util.HttpContextUtil;
import com.geekerstar.monitor.service.ILogService;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author geekerstar
 * date: 2019/12/15 20:18
 * description:
 */
@Aspect
@Component
public class ControllerEndpointAspect extends AspectSupport {

    @Autowired
    private ILogService logService;

    @Pointcut("@annotation(com.geekerstar.common.annotation.ControllerEndPoint)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws GeekException {
        Object result;
        Method targetMethod = resolveMethod(point);
        ControllerEndPoint annotation = targetMethod.getAnnotation(ControllerEndPoint.class);
        String operation = annotation.operation();
        long start = System.currentTimeMillis();
        try {
            result = point.proceed();
            if (StringUtils.isNotBlank(operation)) {
                RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
                ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) attributes;
                String ip = StringUtils.EMPTY;
                if (servletRequestAttributes != null) {
                    ip = servletRequestAttributes.getRequest().getRemoteAddr();
                }
                logService.saveLog(point, targetMethod, ip, operation, start);
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

package com.spring.itjunior.aop;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@Component
@Aspect
public class BindingAdvice {

    @Around("execution(* com.spring.itjunior..*Controller.*(..))")
    public Object logging(ProceedingJoinPoint pjp) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
        log.info("=======================================");
        log.info("[[요청된 URI 주소]] >>> [ {} ]",request.getRequestURI());
        log.info("[[start Controller]] >>> [{}] // [[method]] >>> [{}]",pjp.getSignature().getDeclaringType(),pjp.getSignature().getName());
        Object result = pjp.proceed();
        log.info("[[end Controller]] >>> [{}] // [[method]] >>> [{}]",pjp.getSignature().getDeclaringType(),pjp.getSignature().getName());
        log.info("");
        return result;
    }

    //aop joinpoint를 사용하려면 restCotroller방식을 사용해야 에러 타입과 벨류를 효율적으로 사용할 수 있다.
    @Around("execution(* com.spring.itjunior..*Controller.*(..))")
    public Object validCheck(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        for (Object arg : args) {
            if (arg instanceof BindingResult) {
                log.info("BindingAdvice aop로 들어옴.");
                BindingResult bindingResult = (BindingResult) arg;
                if (bindingResult.hasErrors()) {
                    Map<String, String> errorMap = new HashMap<>();

                    for (FieldError error : bindingResult.getFieldErrors()) {
                        errorMap.put(error.getField(), error.getDefaultMessage());
                    }
                    log.info(errorMap);

//                    model.addAttribute("errorMap",errorMap);
                    return "error/validExcetion";
                }
            }
        }

        return pjp.proceed();
    }
}

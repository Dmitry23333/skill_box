package com.example.springbootnewsportal.aop;

import com.example.springbootnewsportal.service.AccessCheckerService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.nio.file.AccessDeniedException;


@Component
@Aspect
@RequiredArgsConstructor
public class AccessCheckAspect {
    private final AccessCheckerService accessCheckerService;

    @Before("@annotation(accessible)")
    public void check(Accessible accessible) throws AccessDeniedException {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            throw new IllegalArgumentException("RequestAttributes not present!");
        }
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        accessCheckerService.check(request, accessible);

        if (!accessCheckerService.check(request, accessible)) {
            throw new AccessDeniedException("Access denied for this action!");
        }
    }
}

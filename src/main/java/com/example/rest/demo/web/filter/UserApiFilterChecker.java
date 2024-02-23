//package com.example.rest.demo.web.filter;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@Component("com/example/rest/demo/web/filter")
//@ConditionalOnExpression()
//public class UserApiFilterChecker extends OncePerRequestFilter {
//
//    private static final String HTTP_CLIENT_HEADER = "X-User-Api-Key";
//
//
//    private String userApiKey = "12345";
//
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String headerValue = response.getHeader(HTTP_CLIENT_HEADER);
//
//        if (headerValue == null || !headerValue.equals(userApiKey)) {
//            response.setHeader(HTTP_CLIENT_HEADER, "Invalid");
//            response.sendError(HttpStatus.BAD_REQUEST.value(), "Заголовок X-User-Api-Key отсутствует или указан неверно!");
//            return;
//        }
//        filterChain.doFilter(request, response);
//    }
//}

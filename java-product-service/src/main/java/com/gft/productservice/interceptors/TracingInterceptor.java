package com.gft.productservice.interceptors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import com.gft.productservice.utils.Constants;
import com.gft.productservice.utils.RequestContext;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TracingInterceptor implements HandlerInterceptor {
   @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String customerId = request.getHeader(Constants.CUSTOMER_ID_MDC_KEY);
        if (StringUtils.isNotEmpty(customerId)) {
            RequestContext.setCustomerId(customerId);
            MDC.put(Constants.CUSTOMER_ID_MDC_KEY, customerId);
        }
        log.info("Action=[{}], Method=[{}] <- ENTER", request.getRequestURI(), request.getMethod());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,Exception exception) throws Exception {
        log.info("Action=[{}], Method=[{}] -> LEAVE", request.getRequestURI(), request.getMethod());
        String customerId = request.getHeader(Constants.CUSTOMER_ID_MDC_KEY);
        if (StringUtils.isNotEmpty(customerId)) {
            RequestContext.setCustomerId(null);
            MDC.remove(Constants.CUSTOMER_ID_MDC_KEY);
        }
    } 
}

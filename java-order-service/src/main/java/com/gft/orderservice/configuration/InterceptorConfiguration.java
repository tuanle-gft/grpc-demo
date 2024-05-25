package com.gft.orderservice.configuration;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.gft.orderservice.interceptors.TracingInterceptor;

@Component
public class InterceptorConfiguration implements WebMvcConfigurer {
    private TracingInterceptor tracingInterceptor;

    public InterceptorConfiguration(TracingInterceptor tracingInterceptor) {
        this.tracingInterceptor = tracingInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tracingInterceptor);
    }
}

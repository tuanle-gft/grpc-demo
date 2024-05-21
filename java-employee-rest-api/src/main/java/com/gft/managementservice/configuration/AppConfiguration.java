package com.gft.managementservice.configuration;

import com.gft.openapi.client.certificates.ApiClient;
import com.gft.openapi.client.certificates.api.DefaultApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {
    @Bean
    public DefaultApi certificateApi() {
        return new DefaultApi(new ApiClient().setBasePath("http://localhost:8001/v1"));
    }
}

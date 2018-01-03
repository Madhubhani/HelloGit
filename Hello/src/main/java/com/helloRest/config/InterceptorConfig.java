package com.helloRest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.helloRest.interceptor.AuthenticationInterceptor;

@Configuration
public class InterceptorConfig  extends WebMvcConfigurerAdapter{

	@Autowired
    public AuthenticationInterceptor authenticationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(authenticationInterceptor).addPathPatterns("/hello/**");
			/*.excludePathPatterns("/status", "/health", "/v2/api-docs", "/swagger-ui")*/

    }
    
}

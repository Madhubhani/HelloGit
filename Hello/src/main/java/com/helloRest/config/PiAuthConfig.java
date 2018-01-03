package com.helloRest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.helloRest.domain.Student;
import com.pearson.ed.pi.authentication.authenticator.TokenAuthenticator;
import com.pearson.ed.pi.token.JWKSStoreProxy;

@Configuration
public class PiAuthConfig {

    @Bean
    public JWKSStoreProxy setJWKSStoreProxy() {
    	return new JWKSStoreProxy("https://int-piapi-internal.stg-openclass.com/tokens", 3000, 3000);
    }

    @Bean
    public TokenAuthenticator tokenAuthenticator() {
	return new TokenAuthenticator(setJWKSStoreProxy());
    }
    
    @Bean
    public Student st() {
	return new Student();
    }
    
}

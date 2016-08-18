package com.aw.rest.security.oauth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true)
@Order(5)
public class OAuth2MethodSecurityConfig extends GlobalMethodSecurityConfiguration {

	static final Logger log = LoggerFactory.getLogger(OAuth2MethodSecurityConfig.class);
	
	@Autowired
    private OAuth2SecurityConfiguration securityConfig;
 
    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
    	log.info("MethodSecurityExpressionHandler ");
        return new OAuth2MethodSecurityExpressionHandler();
    }
}
package com.aw.rest.security.oauth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.aw.rest.security.AwaAuthenticationFilter;
import com.aw.rest.security.AwaAuthorizationFilter;



/**
 * @author Rahul Vishwakarma
 *
 */
@Configuration
@EnableResourceServer
@Order(6)
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	static final Logger log = LoggerFactory.getLogger(ResourceServerConfiguration.class);

	@Autowired
	private AwaAuthenticationFilter awaAuthenticationFilter;
	
	@Autowired
	private AwaAuthorizationFilter awaAuthorizationFilter;
	
	private static final String RESOURCE_ID = "my_rest_api";

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.resourceId(RESOURCE_ID).stateless(false);
	}
 
	@Override
	public void configure(HttpSecurity http) throws Exception {
		log.info("Configuring HTTP Security ");
		http.
		anonymous().disable()
		.authorizeRequests()
		.antMatchers("/admin/*").hasRole("ADMIN")
		.antMatchers("/countries/*").hasRole("ADMIN")
		//.antMatchers("/countries/**").access("hasRole('ADMIN')")
		.and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler())
		.and().cors().configurationSource(configurationSource())
		.and()
			.addFilterBefore(awaAuthenticationFilter, AbstractPreAuthenticatedProcessingFilter.class)
			.addFilterAfter(awaAuthorizationFilter, AbstractPreAuthenticatedProcessingFilter.class);
	}

	
	private static UrlBasedCorsConfigurationSource configurationSource() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.setMaxAge(3600L);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("Origin, Content-Type, Authorization, X-CSRF-Token, X-Requested-With, Accept, Accept-Version, Content-Length, Content-MD5,  Date, X-Api-Version, X-File-Name");
		config.addAllowedMethod("GET, POST, PUT, DELETE, OPTIONS");
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		return source;
	}

}

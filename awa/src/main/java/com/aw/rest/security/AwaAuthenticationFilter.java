package com.aw.rest.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

/**
 * @author Rahul Vishwakarma
 *
 */
@Configuration
public class AwaAuthenticationFilter implements Filter {

	static final Logger log = LoggerFactory.getLogger(AwaAuthenticationFilter.class);

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		if (req.getHeader("Access-Control-Request-Method") != null && "OPTIONS".equalsIgnoreCase(req.getMethod().trim())) {
			log.info("Sending Header: CORS pre-flight request");
			res.sendError(HttpStatus.OK.value());
			return;
		}else{
			log.info("------------------------------ Pre-Authenticate ------------------------------");
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if (authentication instanceof Authentication) {
				log.info("Attempt authentication: " + authentication.getPrincipal());
			}
		}
		filterChain.doFilter(request, response);
	}

	
	
	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}

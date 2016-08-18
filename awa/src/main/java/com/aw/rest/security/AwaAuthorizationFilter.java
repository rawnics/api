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
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author Rahul Vishwakarma
 *
 */
@Configuration
public class AwaAuthorizationFilter implements Filter {

	static final Logger log = LoggerFactory.getLogger(AwaAuthorizationFilter.class);

	@Autowired
	private AwaAuthorizationValidator awaAuthorizationValidator;


	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		log.info("------------------------------ Pre-Authorization ------------------------------");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		try{
			SecurityContextHolder.setContext(loadSecurityContext((HttpServletRequest)req));
		} catch (IllegalAccessException e) {
			log.error(e.getMessage());
			res.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
			return;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
			return;
		}
		filterChain.doFilter(request, response);
	}


	/**
	 * Get the proper security context.
	 * 
	 * @param request HttpServletRequest
	 * @return A SecurityContext
	 * @throws IllegalAccessException 
	 * @throws WmsException 
	 */
	private SecurityContext loadSecurityContext(HttpServletRequest request) throws IllegalAccessException, Exception {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = null;
		try {
			authentication = SecurityContextHolder.getContext().getAuthentication();
			log.info("Security Context: "+authentication);
			if(authentication == null || authentication.getPrincipal() == null)
				return securityContext;	
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return securityContext;
		}
		/**	Call the authority validation flow */
		if (authentication instanceof Authentication && authentication.isAuthenticated() 
				&& awaAuthorizationValidator.validateAuthenticatedAccessRequest(authentication, request)) {
			log.info("---------------------- Authorized "+authentication.getPrincipal()+"-------------------------");
		} else {
			String message = "Authorization failed for user";
			throw new IllegalAccessException(message);
		}
		return securityContext;
	}


	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}

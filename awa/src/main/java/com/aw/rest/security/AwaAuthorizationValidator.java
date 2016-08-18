package com.aw.rest.security;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.aw.rest.dal.entity.UserInfo;
import com.aw.rest.security.AuthRequest.RoleType;
import com.aw.rest.service.utility.Utility;

/**
 * @author Rahul Vishwakarma
 *
 */
@Service
public class AwaAuthorizationValidator {
	
	private static final Logger log = LoggerFactory.getLogger(AwaAuthorizationValidator.class);

	@Autowired
	private SecurityService securityService;


	/**
	 * Authority validation service
	 * 
	 * @param authentication
	 * @param request
	 * @return
	 * @throws IllegalAccessException
	 * @throws Exception 
	 */
	public boolean validateAuthenticatedAccessRequest(Authentication authentication, 
								HttpServletRequest request) throws IllegalAccessException, Exception {
		String message = "";
		String url = request.getRequestURI();
		String httpMethod = request.getMethod().toUpperCase();
		log.debug("Authenticated user in context:"+authentication.getDetails().toString()); 
		UserInfo userInfo =  securityService.getAuthenticatedUserInfo(authentication);
		log.info("Validating "+httpMethod+" on url:"+url+" for user: "+ userInfo.toString());
		
		/** 1. All CRUD operations permitted to ROLE_ADMINISTRATOR */
		if(isAuthorityAvailable(authentication, RoleType.ROLE_ADMINISTRATOR))
			return true;
		
		/**
		 * Authorization based on the HTTP request type
		 * 2. READ (GET)
		 * 	  An authenticated user should be able to view a project and the various other data within. if authorized returns true
		 * 3. Find If its create or update based on URL
		 * 	  CREATE (POST) - An authenticated user can create 
		 *    UPDATE (POST) - Only an owner or admin  can update 
		 *    UPDATE (PUT)
		 * 4. DELETE
		 * 	  An authenticated user(user_name) should not be able to delete(by id) other users data. 
		 *    Only an owner or admin can delete data. 
		 */
		switch (httpMethod) {

			case "GET": return validateGetOperation(userInfo, request);

			case "POST": return validatePostOperation(userInfo, request);

			case "PUT": return validatePutOperation(userInfo, request);

			case "DELETE": return validateDeleteOperation(userInfo, request);

			default: message = "Authenticated user "+userInfo.getUserName()+
					 " made an unsupported "+httpMethod+" request on "+ url;  
				log.warn(message);
				break;
		}
		message = "Unauthorized: Access denied to authenticated user "+userInfo.getUserName()+
				  " for a "+httpMethod+" request on "+ url;  
		throw new IllegalAccessException(message);
	}
	
	/**
	 * Checks for a valid authority with the authenticated user
	 * 
	 * @param authentication
	 * @param authorityName
	 * @return
	 */
	private boolean isAuthorityAvailable(Authentication authentication, RoleType authorityType ){
		//List<SimpleGrantedAuthority> authorities = (List<SimpleGrantedAuthority>) authentication.getAuthorities();
		for(GrantedAuthority grant : authentication.getAuthorities()){
			if((!grant.getAuthority().isEmpty()) && grant.getAuthority().endsWith(authorityType.getName())){
				log.warn("Authorized access to "+grant.getAuthority());
				return true;
			}
		}
		return false;
	}

	/**
	 * Validate GET method
	 * 
	 * @param userInfo
	 * @param request
	 * @return
	 */
	private boolean validateGetOperation(UserInfo userInfo, HttpServletRequest request){
		String url = request.getRequestURI();
		String httpMethod = request.getMethod().toUpperCase();
		String message = httpMethod+" data from "+url+" for user "+userInfo.getUserName(); 
		log.info(message);	
		return true;
	}
	
	/**
	 * Validate PUT method
	 * 
	 * @param userInfo
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	private boolean validatePutOperation(UserInfo userInfo, HttpServletRequest request) throws Exception{
		String url = request.getRequestURI();
		String httpMethod = request.getMethod().toUpperCase();
		int id = getIdFromUrl(url);
		String message = httpMethod+" data, "+url+" with operation on id "+id+" for user "+userInfo.getUserName();
		log.info(message);	
		if(id > 0){
			log.info("UPDATE");
			//User should not be able to update other users data.
			return verifyUserAccess(id, userInfo);
		}else{
			//Allow put operations on other non id centric access
			return true;
		}
	}
	
	/**
	 * Validate POST method
	 * 
	 * @param userInfo
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	private boolean validatePostOperation(UserInfo userInfo, HttpServletRequest request) throws Exception{
		String url = request.getRequestURI();
		String httpMethod = request.getMethod().toUpperCase();
		int id = getIdFromUrl(url);
		String message = httpMethod+" data, "+url+" with operation on id "+id+" for user "+userInfo.getUserName();
		log.info(message);	
		if(id > 0){
			log.info("CREATE/UPDATE");
			//User should not be able to update other users data.
			return verifyUserAccess(id, userInfo);
		}else{
			//Allow post operations on other non id centric access
			return true;
		}
	}
	
	/**
	 * Validate DELETE method
	 * 
	 * @param userInfo
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	private boolean validateDeleteOperation(UserInfo userInfo, HttpServletRequest request) throws Exception{
		String url = request.getRequestURI();
		String httpMethod = request.getMethod().toUpperCase();
		int id = getIdFromUrl(url);
		String message = httpMethod+" data, "+url+" with operation on id "+id+" for user "+userInfo.getUserName();
		log.info(message);	
		//User should not be able to update other users data.
		if(id > 0){
			log.info("DELETE");
			//User should not be able to delete other users data.
			return verifyUserAccess(id, userInfo);
		}else{
			//Block delete operations on other non project centric access
			return false;
		}
	}

	/**
	 * Validate user access based on role in a project
	 * 
	 * @param id
	 * @param userInfo
	 * @return
	 * @throws Exception
	 */
	private boolean verifyUserAccess(int id, UserInfo userInfo) throws Exception{
		//If systemProjectId=0 and user is a DATA-OWNER then user is allowed to create projects in the system
		if(id == 0){
			if(userInfo.getAuthoritiesStringCollection().contains(RoleType.ROLE_SME.getName())){
				return true;
			}
		}
		//If not a OWNER then Map the id level roles for access

		return false;
	}
	
	/**
	 * Extract the id from the access urls
	 * 
	 * @param url
	 * @return
	 */
	private int getIdFromUrl(String url){
		String regexId = "(/\\d+)";
		String regexNumber = "\\d+";
		int projectId = 0;
		try{
			String r ="";
			r = Utility.findTextBasedOnRegex(regexId, url);
			r = Utility.findTextBasedOnRegex(regexNumber, r);
			projectId = Integer.parseInt(r);
		}catch(Exception e){
			log.error("Unable to parse id from url: "+url);
		}
		return projectId;
	}
}

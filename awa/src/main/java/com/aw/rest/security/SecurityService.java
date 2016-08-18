package com.aw.rest.security;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.aw.rest.dal.ISecurityDao;
import com.aw.rest.dal.entity.UserInfo;
import com.aw.rest.service.utility.Utility;

@Service
public class SecurityService {

	private static final Logger log = LoggerFactory.getLogger(SecurityService.class);
	
	@Autowired
	private ISecurityDao securityDao;

	@Autowired
	private Utility utility;


	/**
	 * Get the User details object from the authentication database
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public UserInfo getUserInfoByName(String userName) throws Exception {
		//User data responded from the Authentication mysql db
		UserInfo u = securityDao.getUserByName(userName);
		//Add authorities as per WMS user data
		u.setAuthorities(securityDao.getAuthoritiesForUser(userName));
		log.debug("---------------------------------------------------------");
		log.debug(u.toString());
		log.debug("---------------------------------------------------------");
		return u;
	}


	/**
	 * Get the Granted Authority( Permissions )
	 * For the username
	 * 
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public List<GrantedAuthority> getAuthoritiesForUser(UserInfo user) throws Exception {
		try {
			//User data responded from the Authentication mysql db
			UserInfo u = securityDao.getUserByName(user.getUserName());
			if(u != null){
				user.setUserId(u.getUserId());
			}
			log.debug("Authorization for user: "+user.toString());
			return securityDao.getAuthoritiesForUser(user.getUserName());
		} catch (Exception e) {
			log.error("No user/authorities data found for user: "+user.getUserName());
			return null;
		}
	}


	/**
     * Get user info from the Security Context
     * @return
     */
    public UserInfo getContextUserInfo() throws Exception{
		UserInfo userInfo = new UserInfo();
		try{
		    Authentication a = SecurityContextHolder.getContext().getAuthentication();	
		    if(a != null && a.isAuthenticated()){
		    	//Get the user name and id
		    	userInfo = getAuthenticatedUserInfo(a);
		    } else{
				userInfo.setUserName("Anonymous");
				userInfo.setUserId(0);
		    }
		    ServletRequestAttributes sra = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		    if(sra!=null){
				HttpServletRequest req = sra.getRequest();
				if(req!=null){
				    userInfo.setClientIp(getClientIpAddr(req));
				    userInfo.setSession(req.getSession().getId());
				}            
		    }	
		    log.debug("Security Context user: "+userInfo);
		}catch (Exception e){
		  throw new Exception(e.getMessage() + ": Failed to get user info for: "+userInfo.getUserName());
		}
		return userInfo;
    }


    /**
     * Get user info from the Request parameters
     * @param Authentication
     * @param HttpServletRequest
     * @return UserInfo
     */
    public UserInfo getContextUserInfo(Authentication a, HttpServletRequest req){
  		UserInfo userInfo = new UserInfo();
  		try{
  		    if(a != null && a.isAuthenticated()){
  		    	//Get the user name, id and authorities
  		    	userInfo = getAuthenticatedUserInfo(a);
  		    } else {
  				userInfo.setUserName("Anonymous");
  				userInfo.setUserId(0);
  		    }
			if(req!=null){
			    userInfo.setClientIp(getClientIpAddr(req));
			    userInfo.setSession(req.getSession().getId());
			}            
  		}catch (Exception e){
  			log.error(e.getMessage() + ": Failed to get user info for: "+a.getName());
  		}
  		return userInfo;
    }


    /**
     * Get the authenticated user details 
     * @param authentication
     * @return
     */
    public UserInfo getAuthenticatedUserInfo(Authentication authentication){
    	if(authentication == null || authentication.getPrincipal() == null)
			return null;
    	//Details has the UserInfo Object added during authentication
    	UserInfo ui = new UserInfo(0, (String)authentication.getName(), (String)authentication.getCredentials());
    	ui.setAuthorities(new ArrayList<>(authentication.getAuthorities()));

    	/*
    	
    	Object user = authentication.getDetails();
		if (user instanceof AuthRequest){
			AuthRequest ar = (AuthRequest) user;
			log.debug(ar.toString());
			UserInfo ui = new UserInfo((ar.getId()==null?0:Integer.valueOf(ar.getId())), ar.getUser(), ar.getToken());
			ui.setAuthorities(new ArrayList<GrantedAuthority>(authentication.getAuthorities()) );
			ui.setSession(ar.getSession());
			ui.setClientIp(ar.getRemotehost());
			ui.setToken(ar.getToken());
			if(ui.getAuthoritiesStringCollection().contains(WmsConstants.RoleNames.ADMIN))
				ui.setStatus(WmsConstants.RoleNames.ADMIN);
			return ui;
		}
		*/
		return ui; 
    }


    /**
     * Get Client IP Address based on the request
     * @param request
     * @return String IPAddress
     */
    public String getClientIpAddr(HttpServletRequest request) {
    	//log.info(getHeaders(request));
    	//log.info("*************"+request.getHeader("REMOTE_HOST"));
    	String ip="";
		try{
			ip = request.getHeader("X-FORWARDED-FOR");  
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
			    ip = request.getHeader("Proxy-Client-IP");  
			}  
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
			    ip = request.getHeader("WL-Proxy-Client-IP");  
			}  
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
			    ip = request.getHeader("HTTP_CLIENT_IP");  
			}  
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
			    ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
			}  
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
			    ip = request.getRemoteAddr();  
			}  
		}catch (Exception e){
			 log.error(e.getMessage());
		}
		return ip;
    }


    /**
     * Create a hash of the input string
     * Using MD5 digest algorithm
     * @param input
     * @return
     */
    public String hashMD5(String input) {
        String md5String = null;
        if(null == input) return null;
        try {
        	log.info("Creating Hash: "+input);
	        //Create MessageDigest object for MD5
	        MessageDigest digest = MessageDigest.getInstance("MD5");
	        //Update input string in message digest
	        digest.update(input.getBytes(), 0, input.length());
	        //Converts message digest value in base 16 (hex) 
	        md5String = new BigInteger(1, digest.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
        	log.error(e.getMessage(),e);
        }catch (Exception e) {
        	log.error(e.getMessage(),e);
		}
        return md5String;
    }
    public String hashMD5(String input, String salt) {
    	return hashMD5(input+salt);
    }
}


package com.aw.rest.dal;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import com.aw.rest.dal.entity.UserInfo;

/**
 * @author Rahul Vishwakarma
 *
 */
public interface ISecurityDao {

	
	/**
	 * Get the roles or granted authorities associated with user
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<GrantedAuthority> getAuthoritiesForUser(String userName) throws Exception;
	
	/**
	 * Get user name from drupal
	 * @param userId
	 * @return
	 */
	String getWmsUsername(String userId) throws Exception;
	
	/**
	 * Get the Email id of the user from drupal database
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	String getEmailIdForUserId(int userId) throws Exception;

	/**
	 * Get user by name
	 * 
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	UserInfo getUserByName(String userName) throws Exception;

	
}

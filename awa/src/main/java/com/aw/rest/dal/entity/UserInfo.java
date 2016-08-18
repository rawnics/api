/**
 * 
 */
package com.aw.rest.dal.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author Rahul Vishwakarma
 *
 */
public class UserInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int userId;
	private String userName;
	private String userPass;
	private String clientIp;
	private String session;
	private String device;
	private String token;
	private String organizationId;
	private String organizationName;
	private List<GrantedAuthority> authorities;
	
	private String firstName;
	private String lastName;
	
	public UserInfo(int id, String userName, String userPass) {
		super();
		this.userId = id;
		this.userName = userName;
		this.userPass = userPass;
	}
	
	public UserInfo(int id, String userName, String userPass,String organizationId) {
		super();
		this.userId = id;
		this.userName = userName;
		this.userPass = userPass;
		this.organizationId = organizationId;
	}
	
	public UserInfo() {
		super();
	}
	

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * @return the clientIp
	 */
	public String getClientIp() {
		return clientIp;
	}
	/**
	 * @param clientIp the clientIp to set
	 */
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the userPass
	 */
	public String getUserPass() {
		return userPass;
	}
	/**
	 * @param userPass the userPass to set
	 */
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	
	/**
	 * @return the authorities
	 */
	public List<GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	public List<String> getAuthoritiesStringCollection() {
		List<String> au = new ArrayList<String>();
		for(GrantedAuthority g : authorities){
			au.add(g.getAuthority());
		}
		return au;
	}

	/**
	 * @param authorities the authorities to set
	 */
	public void setAuthorities(List<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	/**
	 * @return the session
	 */
	public String getSession() {
		return session;
	}

	/**
	 * @param session the session to set
	 */
	public void setSession(String session) {
		this.session = session;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @return the device
	 */
	public String getDevice() {
		return device;
	}

	/**
	 * @param device the device to set
	 */
	public void setDevice(String device) {
		this.device = device;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}	
	
}

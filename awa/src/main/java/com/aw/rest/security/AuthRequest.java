package com.aw.rest.security;

import java.io.Serializable;
import java.util.List;

public class AuthRequest implements Serializable{
	
	private static final long serialVersionUID = -327696741165620742L;

	public enum RequestParamKey {		
		Authorization("Authorization"),
		UserAgent("User-Agent"),
		Origin("Origin"),
		Basic("Basic");
		
		private RequestParamKey(String name) {
			this.name = name;
		}
		private String name;
		public String get() {return name;}
	}
	
	public enum AuthTokenType{
		user,
		token,
		apikey,
		uploadkey
	}
	
	/**
	 * Enumeration of authorities for validation
	 * 
	 * @author Rahul Vishwakarma
	 */
	public enum RoleType {
		
		ROLE_ADMINISTRATOR(1, "ADMIN"),
		ROLE_SME(2, "SME"),
		ROLE_USER(3, "USER"),
		ROLE_ANONYMOUS(4,"ANONYMOUS"),
		ROLE_AUTHENTICATED(5,"AUTHENTICATED");
		
		private final int value;
		private final String name;
		
		private RoleType(int value, String name){
			this.value = value;
			this.name = name;
		}
		
		public int getValue(){
			return value;
		}
		public String getName(){
			return name;
		}
	}

	
	/**
	 * All requests shall have the following 
	 * id 	: unique serial number
	 * user : user name
	 * token: token hash as identifier
	 */
	private String id;
	private String user;
	private String type; //token, apikey, uploadkey
	private String token;
	private String session;
	private String device;
	private String remotehost;
	private List<String> auths;
	

	public AuthRequest() {
		super();
	}

	/**
	 * @param id
	 * @param user
	 * @param token
	 * @param device
	 * @param remotehost
	 * @param auth
	 */
	public AuthRequest(String id, String user, String token, String type,
			String remotehost, List<String> auth) {
		super();
		this.id = id;
		this.user = user;
		this.token = token;
		this.type = type;
		this.remotehost = remotehost;
		this.auths = auth;
	}
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
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
	public String getRemotehost() {
		return remotehost;
	}
	public void setRemotehost(String remotehost) {
		this.remotehost = remotehost;
	}
	
	/**
	 * @return the auths
	 */
	public List<String> getAuths() {
		return auths;
	}

	/**
	 * @param auths the auths to set
	 */
	public void setAuths(List<String> auths) {
		this.auths = auths;
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
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AuthRequest [id=" + id + ", user=" + user + ", type=" + type
				+ ", token=" + token + ", session=" + session + ", device="
				+ device + ", remotehost=" + remotehost + ", auths=" + auths
				+ "]";
	}	
	
}

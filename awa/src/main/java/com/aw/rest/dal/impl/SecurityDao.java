
package com.aw.rest.dal.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

import com.aw.rest.dal.ISecurityDao;
import com.aw.rest.dal.entity.UserInfo;

/**
 * @author Rahul Vishwakarma
 *
 */
@Repository
public class SecurityDao implements ISecurityDao {
	
	private static final Logger log = LoggerFactory.getLogger(SecurityDao.class);

	@Autowired
	private JdbcTemplate awrServiceJdbcTemplate;
	
	/* (non-Javadoc)
	 * @see com.hp.wms.dal.ISecurityDao#getAuthoritiesForUser(java.lang.String)
	 */
	@Override
	public List<GrantedAuthority> getAuthoritiesForUser(String userName) throws Exception {
		// Get user role names from WMS Database
		String sql = "SELECT a.* FROM "
				+ "(	SELECT DISTINCT r.role_name FROM user u, project_user_role ur, "
				+		"role r "
				+ "		WHERE  u.user_id=ur.user_id AND r.role_id=ur.role_id AND u.user_name=? "
				+ " UNION ALL"
				+ "		SELECT DISTINCT p.permission FROM user u, project_user_role ur, "
				+ 		"role r, role_permission rp, permission p "
				+ "		WHERE u.user_id=ur.user_id AND r.role_id=ur.role_id AND r.role_id=rp.role_id AND rp.permission_id = p.permission_id"
				+ "		AND u.user_name=?"
				+ ") a ";
		log.debug(sql);
		List<String> roles = awrServiceJdbcTemplate.queryForList(sql, new Object[]{userName,userName}, String.class);
		log.debug("WMS User:"+userName+" has the following authorities: "+roles);
		List<GrantedAuthority> authorities = null;
		if(roles != null && !roles.isEmpty()){
			authorities = new ArrayList<GrantedAuthority>();
			for(String grant: roles)
				authorities.add(new SimpleGrantedAuthority(grant.trim()));
		}
		return authorities;
	}


	/* (non-Javadoc)
	 * @see com.hp.wms.dal.ISecurityDao#getUserByName(java.lang.String)
	 */
	@Override
	public UserInfo getUserByName(String userName) throws Exception {
		String query = "SELECT user_id, user_name, first_name, "
				+ " last_name, email, organization_id, system_create_time,"
				+ " last_update_time, last_update_by "
				+ " FROM user "
				+ " WHERE user_name = ? ORDER BY last_update_time DESC LIMIT 1";
		log.debug(query);	
		UserInfo user = awrServiceJdbcTemplate.queryForObject(query,new Object[] {userName}, 
									new BeanPropertyRowMapper<UserInfo>(UserInfo.class));
		return user;
	}
	


	/* (non-Javadoc)
	 * @see com.hp.wms.dal.ISecurityDao#getWmsUsername(java.lang.String)
	 */
	@Override
	public String getWmsUsername(String userId) throws Exception{
		String sql ="SELECT first_name FROM user where user_id = ?";
		log.debug("UserId: " + userId);
		log.debug(sql);			
		String userName = awrServiceJdbcTemplate.queryForObject(sql, new Object[] { Integer.parseInt(userId) },
				String.class);
		log.debug(userName+", UserId: " + userId);
		return userName;
	}

	/* (non-Javadoc)
	 * @see com.hp.teamci.dal.ISecurityDAO#getDrupalEmailIdForUserId(int)
	 */
	@Override
	public String getEmailIdForUserId(int userId) throws Exception {
			String sql ="SELECT email FROM user WHERE user_id = ?";
			log.debug("UserId: " + userId);
			log.debug(sql);			
			String mailId = awrServiceJdbcTemplate.queryForObject(sql, new Object[] { userId },
					String.class);
			log.debug(mailId+", UserId: " + userId);
			return mailId;
	}


}

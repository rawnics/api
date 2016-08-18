package com.aw.rest.dal.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.aw.rest.dal.IConfigurationDao;
import com.aw.rest.dal.entity.ConfigSetting;
import com.aw.rest.exception.AwrException;

/**
 * @author Rahul
 *
 */
@Repository
public class ConfigurationDao implements IConfigurationDao {

	private static final Logger log = LoggerFactory.getLogger(ConfigurationDao.class);

	@Autowired
	private JdbcTemplate awrServiceJdbcTemplate;
	
	
	/* (non-Javadoc)
	 * @see com.aw.rest.dal.IConfigurationDao#getConfigurations(java.lang.String)
	 */
	@Override
	public List<ConfigSetting> getConfigurations(String type) throws AwrException {
		String sql = "SELECT * FROM configuration ";
		if(type != null && type.length() > 0){
			String where =" WHERE config_tag LIKE '"+type+"'";
			sql+=where;
		}
		log.debug(sql);
		RowMapper<ConfigSetting> rowMapper = new RowMapper<ConfigSetting>() {
			@Override
			public ConfigSetting mapRow(ResultSet rs, int arg1)
					throws SQLException {
				ConfigSetting setting = new ConfigSetting();
				setting.setConfigKey(rs.getString("config_key"));
				setting.setConfigValue(rs.getString("config_value"));
				return setting;
			}
		};
		List<ConfigSetting> config = awrServiceJdbcTemplate.query(sql,rowMapper);
		return config;
	}
	
	
	/* (non-Javadoc)
	 * @see com.aw.rest.dal.IConfigurationDao#getConfiguration(java.lang.String)
	 */
	@Override
	public ConfigSetting getConfiguration(String key) throws AwrException {
		String sql = "SELECT * FROM configuration ";
		if(key != null && key.length() > 0){
			String where =" WHERE config_key LIKE '"+key+"'";
			sql+=where;
		}
		log.debug(sql);
		RowMapper<ConfigSetting> rowMapper = new RowMapper<ConfigSetting>() {
			@Override
			public ConfigSetting mapRow(ResultSet rs, int arg1)
					throws SQLException {
				ConfigSetting setting = new ConfigSetting();
				setting.setConfigKey(rs.getString("config_key"));
				setting.setConfigValue(rs.getString("config_value"));
				return setting;
			}
		};
		List<ConfigSetting> config = awrServiceJdbcTemplate.query(sql,rowMapper);
		return config.get(0);
	}

}

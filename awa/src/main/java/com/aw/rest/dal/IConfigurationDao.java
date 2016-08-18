/**
 * 
 */
package com.aw.rest.dal;

import java.util.List;

import com.aw.rest.dal.entity.ConfigSetting;
import com.aw.rest.exception.AwrException;

/**
 * @author Rahul
 *
 */
public interface IConfigurationDao {

	/**
	 * Get a list of configurations by type
	 * 
	 * @param type
	 * @return
	 * @throws AwrException
	 */
	public List<ConfigSetting> getConfigurations(String type) throws AwrException;

	/**
	 * Get a particular configuration by key
	 * 
	 * @param key
	 * @return
	 * @throws AwrException
	 */
	public ConfigSetting getConfiguration(String key) throws AwrException;
}

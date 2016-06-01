/**
 * 
 */
package com.aw.rest.dal;

import java.util.List;

import com.aw.rest.dal.common.ConfigSetting;
import com.aw.rest.exception.AwrException;

/**
 * @author Rahul
 *
 */
public interface IConfigurationDao {

	public List<ConfigSetting> getConfigurations(String type) throws AwrException;

	public ConfigSetting getConfiguration(String key) throws AwrException;
}

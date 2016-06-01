/*
 * Title 			: WPIComputeSetting.java  
 * Company 			: Hewlett Packard
 * Project Name 	: Wildlife Picture Index
 * @author 			: Rahul
 * @version 		: 1.0 
 * Date 			: Dec, 2013
 * <p>
 * � Copyright 2013 Hewlett-Packard Development Company, L.P. All rights reserved.
 * @see : HP SOFTWARE LICENSE AND DISTRIBUTION AGREEMENT
 */

package com.aw.rest.dal.common;

import java.io.Serializable;


/**
 * Settings or Configuration Entity 
 * 
 * @author Rahul Vishwakarma
 * 
 */
public class ConfigSetting implements Serializable{

	private static final long serialVersionUID = -6990529125124198656L;
	
	private String configKey;
	private String configValue;
	
	public ConfigSetting(String configKey, String configValue) {
		super();
		this.configKey = configKey;
		this.configValue = configValue;
	}

	public ConfigSetting() {
		super();
	}

	/**
	 * @return the configKey
	 */
	public String getConfigKey() {
		return configKey;
	}

	/**
	 * @param configKey the configKey to set
	 */
	public void setConfigKey(String configKey) {
		this.configKey = configKey;
	}

	/**
	 * @return the configValue
	 */
	public String getConfigValue() {
		return configValue;
	}

	/**
	 * @param configValue the configValue to set
	 */
	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EmailSetting [configKey=" + configKey + ", configValue="
				+ configValue + "]";
	}
	
	
}

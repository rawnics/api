/**
 * 
 */
package com.aw.rest.service.impl;

import java.util.Properties;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Service;

import com.aw.rest.dal.IConfigurationDao;
import com.aw.rest.dal.common.ConfigSetting;
import com.aw.rest.exception.AwrError;
import com.aw.rest.exception.AwrException;
import com.aw.rest.service.IConfigurationService;

/**
 * @author Rahul Vishwakarma
 *
 */
@Service
public class ConfigurationService implements IConfigurationService {
	
	static final Logger log = LoggerFactory.getLogger(ConfigurationService.class);
	
	@Autowired
	private IConfigurationDao configurationDao;

	/**
	 * Initialize all configurations
	 * @throws Exception 
	 */
	@PostConstruct
	private void loadResource() {
		try {			
			propSpring.putAll(PropertiesLoaderUtils.loadAllProperties("spring-security.properties"));
			propSpring.putAll(PropertiesLoaderUtils.loadAllProperties("quartz.properties"));
			log.info("Read classpath properties : "+propSpring.toString());
			propsAll.putAll(propSpring);
			//Initialize, if system proxy is ON
			if("YES".equalsIgnoreCase((String) propSpring.get(AwrConfig.HTTP_IS_PROXY_REQUIRED.get()))){
				initializeSystemSettings(propSpring);
			}		
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
	}
	
	/**
	 * Initialize system wide http proxy
	 * @param propSpring
	 */
	private void initializeSystemSettings(Properties propSpring){
		log.warn("Initializing system wide proxy ");
		System.setProperty("http.proxyHost", (String) propSpring.get(AwrConfig.HTTP_PROXY_HOST.get()));  
		System.setProperty("http.proxyPort", (String) propSpring.get(AwrConfig.HTTP_PROXY_PORT.get()));	
	}
	
	

	/* (non-Javadoc)
	 * @see com.hp.wms.service.IConfigurationService#getConfiguration(java.lang.String)
	 */
	@Override
	public String getConfiguration(String key) throws AwrException {
		if(propsAll != null){
			if(propsAll.containsKey(key))
				return propsAll.getProperty(key);
		}else{
			//Another attempt
			loadResource();
			if(propsAll != null){
				if(propsAll.containsKey(key))
					return propsAll.getProperty(key);
			}else{
				throw new AwrException(AwrError.CONFIGURATION_NOT_FOUND, "No config available for this key in any properties");
			}
		}
		return "";
	}
	
	/* (non-Javadoc)
	 * @see com.hp.wms.service.IConfigurationService#getDbConfiguration(java.lang.String)
	 */
	@Override
	public String getDbConfiguration(String key) throws AwrException {
		ConfigSetting config = null;
		try {
			config = configurationDao.getConfiguration(key);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		if(config != null){
			return config.getConfigValue();
		}else{
			throw new AwrException(AwrError.CONFIGURATION_NOT_FOUND, "No config available for this key in db configuration table");
		}
	}
}

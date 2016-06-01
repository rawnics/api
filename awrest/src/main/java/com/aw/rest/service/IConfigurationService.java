package com.aw.rest.service;

import java.util.Properties;

import com.aw.rest.exception.AwrException;

/**
 * @author Rahul Vishwakarma
 *
 */
public interface IConfigurationService {

	/** All properties store **/
	public Properties propsAll = new Properties();
	/** Properties store */
	public Properties propSpring = new Properties();

	/**
	 * Configuration key for properties
	 * @author vishwaka
	 *
	 */
	public enum AwrConfig{
		
		HTTP_IS_PROXY_REQUIRED("app.proxy.isrequired"),
		HTTP_PROXY_HOST("app.proxy.host"),
		HTTP_PROXY_PORT("app.proxy.port"),
		
		APP_JOB_SCHEDULE_SWITCH("job.app.schedule.service"),
		APP_JOB_SCHEDULE_INTERVAL("job.app.schedule.service.interval"),
		APP_JOB_SCHEDULE_STARTDELAY("job.app.schedule.service.startdelay")
		;

		String name;
		private AwrConfig(String name) {
			this.name = name;
		}
		public String get(){
			return this.name;
		}
	}

	/**
	 * Get Any configuration
	 * @param key
	 * @return
	 * @throws Exception
	 */
	String getConfiguration(String key) throws AwrException;

	String getDbConfiguration(String key) throws AwrException;
}

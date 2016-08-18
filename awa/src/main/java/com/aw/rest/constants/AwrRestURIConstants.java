
package com.aw.rest.constants;

/**
 * @author Rahul
 *
 */
public interface AwrRestURIConstants {

	//Country Codes
	String GET_ALL_COUNTRY_CODES = "/countries";
	String GET_COUNTRY_BY_CODE = "/countries/{countryCode}";
	String GET_ALL_CONTINENTS = "/continents";
	
	
	//Logging level
	String GET_CONFIG_LOGGING_LEVEL = "/admin/logging/level"; //?level=?level can be (INFO, WARN, ERROR, DEBUG, TRACE, OFF, ALL). Current level: "
	String GET_CONFIG_LOGGING_PACKAGE = "/admin/logging/package"; //?classes=?&level=? 	classes: space seperated class expressions, level: can be (INFO, WARN, ERROR, DEBUG, TRACE, OFF, ALL). For others logging is set to WARN "
	String GET_APPLICATION_VERSION = "/admin/version";

	//Other Constants
	String EXCEPTION_ERROR_CODE_DELIMITER = "$";	

	
}

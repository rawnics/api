/*
 * Title 			: RestURIConstants.java  
 * Company 			: Hewlett Packard
 * Project Name 	: WMS
 * @author 			: Rahul
 * @version 		: 1.0 
 * Date 			: May, 2015
 * <p>
 * � Copyright 2013 Hewlett-Packard Development Company, L.P. All rights reserved.
 * @see : HP SOFTWARE LICENSE AND DISTRIBUTION AGREEMENT
 */
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

	//Other Constants
	String EXCEPTION_ERROR_CODE_DELIMITER = "$";	

	
}

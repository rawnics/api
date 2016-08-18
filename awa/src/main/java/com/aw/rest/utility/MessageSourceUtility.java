package com.aw.rest.utility;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.aw.rest.exception.AwrError;


/**
 * @author vishwaka
 *
 */
@Component
public class MessageSourceUtility {

	@Autowired
	private MessageSource messageSource;
	
	/**
	 * Get localized message value from the messages_*.properties bundle
	 * 
	 * @param errorMessageKey
	 * @param moduleName
	 * @param value
	 * @param locale
	 * @return
	 */
	public String getMessageValue(String errorMessageKey,String moduleName,String value,Locale locale){
    	return messageSource.getMessage(errorMessageKey, 
				new Object[]{moduleName,value}, locale);
    }
	
	/**
	 * Get localized message value from the messages_*.properties bundle
	 * 
	 * @param errorMessageKey
	 * @param moduleName
	 * @param value
	 * @param locale
	 * @return
	 */
	public String getMessageValue(String errorMessageKey,Object[] values,Locale locale){
    	return messageSource.getMessage(errorMessageKey,values, locale);
    }
	/**
	 * Get localized message value from the messages_*.properties bundle
	 * 
	 * @param errorMessageKey
	 * @param moduleName
	 * @param value
	 * @return
	 */
	public String getMessageValue(String errorMessageKey,String moduleName,String value){
		return getMessageValue(errorMessageKey, moduleName, value,null);
	}
	
	/**
	 * Get localized message value from the messages_*.properties bundle
	 * 
	 * @param errorMessageKey
	 * @return
	 */
	public String getMessageValue(String errorMessageKey){
		return getMessageValue(errorMessageKey,null,null,null);
	}
	
	/**
	 * Get localized message value from the messages_*.properties bundle
	 * 
	 * @param errorMessageKey
	 * @return
	 */
	public String getMessageValue(String errorMessageKey,Locale locale){
		return getMessageValue(errorMessageKey,null,null,locale);
	}
	
	/**
	 * Get localized message value from the messages_*.properties bundle
	 * 
	 * @param error
	 * @param values
	 * @param locale
	 * @return
	 */
	public String getMessageValue(AwrError error,Object[] values,Locale locale){
		return getMessageValue(error.getErrorMessageId(),values,locale);
	}
	
}

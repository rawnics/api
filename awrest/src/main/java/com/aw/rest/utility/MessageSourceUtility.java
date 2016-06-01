/**
 * 
 */
package com.aw.rest.utility;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.aw.rest.exception.AwrError;


@Component
public class MessageSourceUtility {

	@Autowired
	private MessageSource messageSource;
	
	/**
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
	 * 
	 * @param errorMessageKey
	 * @return
	 */
	public String getMessageValue(String errorMessageKey){
		return getMessageValue(errorMessageKey,null,null,null);
	}
	
	/**
	 * 
	 * @param errorMessageKey
	 * @return
	 */
	public String getMessageValue(String errorMessageKey,Locale locale){
		return getMessageValue(errorMessageKey,null,null,locale);
	}
	
	public String getMessageValue(AwrError ctfsError,Object[] values,Locale locale){
		return getMessageValue(ctfsError.getErrorMessageId(),values,locale);
	}
	
}

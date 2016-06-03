
package com.aw.rest.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.aw.rest.constants.AwrRestURIConstants;

/**
 * @author Rahul Vishwakarma
 * 
 * Exception handling component that has APIs shared and used by controllers.
 */
@Component
public class AwrExceptionHandler {

    public AwrExceptionHandler() {
    }

    @Autowired
    private MessageSource messageSource;

    private static final Logger log = LoggerFactory.getLogger(AwrExceptionHandler.class);

    /**
     * Return Error message corresponding to exception
     * @param ex exception if any errors
     * @return string error message
     */
    public String getError(Exception ex) {
		StringWriter sw = new StringWriter();
		sw.append(ex.getClass().getSimpleName()).append("\n");
		sw.append(ex.getMessage()).append("\n");
		ex.printStackTrace(new PrintWriter(sw));
		return sw.toString();
    }

    /**
     * Get Error code
     * 
     * @param ex exception
     * @return string error message
     */
    public String getErrorCodeId(Exception ex) {
		/*
		 *Get the error message
		 * tokenize on $ and get the errorId and the message
		 * handle each errorId as below
		 * Note: The exception shall be thrown with error codes and handled in the controller with the message and the error id
		 * This service will be called specifically form the catch block to handle the exception thereupon
		 */
		String errMsg = ex.getMessage();
		String errCode = "0";
	
	
	
		int errCodeValue = 0;
		try{
		    if(errMsg!=null && errMsg.indexOf(AwrRestURIConstants.EXCEPTION_ERROR_CODE_DELIMITER)>0){
			errCode = errMsg.substring(0, errMsg.indexOf(AwrRestURIConstants.EXCEPTION_ERROR_CODE_DELIMITER));
		    }
	
		    if(!errCode.isEmpty())
			errCodeValue = Integer.parseInt(errCode);
		    else
			return "";
	
		    if(errCodeValue>0){
			AwrError errEnum = AwrError.forValue(errCodeValue);
			log.error("Error Code: "+errEnum.toString());
			return errEnum.getErrorMessageId();
		    }
		    return "";
		}catch(Exception e){
		    log.error(e.getMessage(),e);
		    return "";
		}
    }

    /**
     * Get Error Message
     * @param ex exception
     * @return string error message
     */
    public String getErrorMessage(Exception ex){

		String errorMessageId = getErrorCodeId(ex);
		String errorMessageValue = ex.getMessage();
		if(!errorMessageId.isEmpty())
		    errorMessageValue = messageSource.getMessage(errorMessageId.trim(),null, Locale.US);
		return errorMessageValue;
	    }
	    
	    public String getLocalizedErrorMessage(Exception ex, Locale locale){
	
		String errorMessageId = getErrorCodeId(ex);
		String errorMessageValue = ex.getMessage();
		if(!errorMessageId.isEmpty())
		    errorMessageValue = messageSource.getMessage(errorMessageId.trim(),null, locale);
		return errorMessageValue;
    }

    /**
     * Exception handler
     * @param ex exception
     */
    public void handleException(Exception ex){
    	handleException(ex, false, true);
    }

    /**
     * Exception handler
     * @param ex exception
     * @param notifyByEmail boolean value
     */
    public void handleException(Exception ex, boolean notifyByEmail){
    	handleException(ex, notifyByEmail, true);
    }

    /**
     * Exception handler
     * 
     * @param ex exception
     * @param notifyByEmail boolean value
     * @param returnErrorView boolean value
     */
    private void handleException(Exception ex, boolean notifyByEmail, boolean returnErrorView) {
		try {
		    String errMsg = getError(ex);
		    log.error(errMsg);
		    log.error("handleException - Catching: " + ex.getMessage());	    
		   /* if (notifyByEmail == true)
				emailUtil.sendMail(null, "ERROR", errMsg);*/
		} catch (Exception e) {
		    log.error(e.getMessage(), e);
		}
    }
}

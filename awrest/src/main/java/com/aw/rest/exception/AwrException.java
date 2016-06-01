
package com.aw.rest.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aw.rest.vo.ErrorVo.ErrorEnum;

/**
 * @author Rahul Vishwakarma
 *
 */
public class AwrException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private static final Logger log = LoggerFactory.getLogger(AwrException.class);
	
	private String errorCode;
	
	/**
	 * Constructor with a custom exception message 
	 * @param message
	 */
	public AwrException(String message) {
		super(message);
		setErrorCode(ErrorEnum.ERROR.toString());
	}
	
	/**
	 * Constructor with a custom exception message 
	 * @param message
	 */
	public AwrException(String message,String errorCode) {
		super(message);
		setErrorCode(errorCode);
	}
	
	/**
	 * Constructor with a custom exception message
	 * @param message
	 * @param cause
	 */
    public AwrException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * Constructor
     * @param cause
     */
    public AwrException(Throwable cause) {
        super(cause);
    }
    
    /**
     * Constructor with a custom exception message
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public AwrException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    /**
     * Exception constructed with a error code message
	 * Using construct/enumeration for each error recognized and throw that as error
	 * ExceptionHandler can extract this message and code to handle it accordingly
	 */
    public AwrException(AwrError err, Throwable cause) {
    	super(err.getErrorId()+"$"+cause.getMessage(),cause);
    	log.error("WMSError: "+err.toString()+", Message: "+cause.getMessage());
    }
    
    /**
     * Constructor with a custom exception message
     * @param err
     * @param cause
     */
    public AwrException(AwrError err, String cause) {
        super(err.getErrorId()+"$"+cause);
        log.error("WMSError: "+err.toString()+", Message: "+cause);
    }

	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}

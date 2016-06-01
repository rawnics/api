package com.aw.rest.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.aw.rest.service.utility.Utility;
import com.aw.rest.utility.MessageSourceUtility;
import com.aw.rest.vo.ErrorVo;
import com.aw.rest.vo.Result;
import com.aw.rest.vo.ErrorVo.ErrorEnum;

@ControllerAdvice
public class AwrExceptionAdvice {

	@Autowired
	private MessageSourceUtility messageSourceUtility;

	
	/**
     * Exception handler to log error
     * Handle general exceptions here
     * 
     * @param ex Exception
     */
    @ExceptionHandler({AwrException.class,Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result handleException(Exception ex) {
    	Result result = new Result();
    	List<ErrorVo> errors = new ArrayList<ErrorVo>();
    	ErrorVo error = new ErrorVo();
    	
    	if(ex instanceof AwrException){
    		AwrException awex = (AwrException)ex;
    		String errorCode = awex.getErrorCode();
    		if(Utility.isValidString(errorCode)){
    			error.setErrorCode(errorCode);
    		}else{
    			error.setErrorCode(ErrorEnum.ERROR.toString());
    		}
    	}else{
    		error.setErrorCode(ErrorEnum.ERROR.toString());
    	}
    	error.setDescription(ex.getMessage());
    	errors.add(error);
    	result.setErrors(errors);
    	return result;
    }
	
   @ExceptionHandler(MethodArgumentNotValidException.class)
   @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {    	
	    List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();	    
	    Result result = new Result();
	    Locale locale = LocaleContextHolder.getLocale();
	    List<ErrorVo> errors = new ArrayList<ErrorVo>();	    
	    StringBuilder errorMessage = new StringBuilder("");		
		for (FieldError fieldError : fieldErrors) {
			
			errorMessage.append(fieldError.getCode()).append(".");
			errorMessage.append(fieldError.getObjectName()).append(".");
			errorMessage.append(fieldError.getField());
			
			String localizedErrorMsg = messageSourceUtility.getMessageValue(errorMessage.toString(), locale);
			ErrorVo error = new ErrorVo();
	    	error.setErrorCode(ErrorEnum.ERROR.toString());
	    	error.setDescription(localizedErrorMsg);
	    	errors.add(error);
			errorMessage.delete(0, errorMessage.capacity());
		}	
	    result.setErrors(errors);
	    return result;
    }
	
    
   @ExceptionHandler({BadCredentialsException.class})
   @ResponseStatus(HttpStatus.UNAUTHORIZED)
   public Result handleUnauthorizedException(Exception ex) {
   	Result result = new Result();
   	List<ErrorVo> errors = new ArrayList<ErrorVo>();
   	ErrorVo error = new ErrorVo();
   	
   	if(ex instanceof AwrException){
   		AwrException awex = (AwrException)ex;
   		String errorCode = awex.getErrorCode();
   		if(Utility.isValidString(errorCode)){
   			error.setErrorCode(errorCode);
   		}else{
   			error.setErrorCode(ErrorEnum.UNAUTHORIZED.toString());
   		}
   	}else{
   		error.setErrorCode(ErrorEnum.UNAUTHORIZED.toString());
   	}
   	error.setDescription(ex.getMessage());
   	errors.add(error);
   	result.setErrors(errors);
   	return result;
   }
}

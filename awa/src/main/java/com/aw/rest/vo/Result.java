
package com.aw.rest.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.aw.rest.service.utility.Utility;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "result")
public class Result implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@XmlElement(name="error")
	private Collection<ErrorVo> errors;

	public Result() {
		super();
	}

	/**
	 * 
	 * @param error
	 */
	public void addError(ErrorVo error){
		if(!Utility.isValidCollection(errors))
			errors = new ArrayList<ErrorVo>();
		errors.add(error);		
	}


	/**
	 * @return the errors
	 */
	public Collection<ErrorVo> getErrors() {
		return errors;
	}


	/**
	 * @param errors the errors to set
	 */
	public void setErrors(Collection<ErrorVo> errors) {
		this.errors = errors;
	}

}

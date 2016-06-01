/**
 * 
 */
package com.aw.rest.vo;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author vishwaka
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "errors")
public class Errors implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	public Errors() {
		super();
	}
	
	@XmlElement(name = "error")
	private List<ErrorVo> errors;

	/**
	 * @return the errors
	 */
	public List<ErrorVo> getErrors() {
		return errors;
	}

	/**
	 * @param errors the errors to set
	 */
	public void setErrors(List<ErrorVo> errors) {
		this.errors = errors;
	}
	
	
	
	

	
}

/**
 * 
 */
package com.aw.rest.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "error")
public class ErrorVo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public enum ErrorEnum {
		ERROR,
		SUCCESS,
		INFO,
		UNAUTHORIZED
	}
	
	private String errorCode;
	private String description;
	
	/**
	 * 
	 */
	public ErrorVo() {
		// TODO Auto-generated constructor stub
	}
	
	
	public ErrorVo(String errorCode,String description) {
		this.errorCode = errorCode;
		this.description = description;
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

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Error [errorCode=" + errorCode + ", description=" + description
				+ "]";
	}
}

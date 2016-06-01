/**
 * 
 */
package com.aw.rest.vo;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * @author vishwaka
 *
 */

@XmlRootElement(name = "countrycodes")
@JsonRootName(value="countrycodes")
@XmlAccessorType(XmlAccessType.FIELD)
public class Countries implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public Countries() {
	}
	
	private List<CountryVo> countrycode;

	/**
	 * @return the countrycode
	 */
	public List<CountryVo> getCountrycode() {
		return countrycode;
	}

	/**
	 * @param countrycode the countrycode to set
	 */
	public void setCountrycode(List<CountryVo> countrycode) {
		this.countrycode = countrycode;
	}
}

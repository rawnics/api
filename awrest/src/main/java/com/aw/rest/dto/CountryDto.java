package com.aw.rest.dto;

import java.io.Serializable;

import com.aw.rest.dal.entity.Country;

/**
 * @author vishwaka
 *
 */
public class CountryDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String code;
	private String name;
	private String continentCode;
	private String continentName;
	private Double latitude;
	private Double longitude;
	private Double maxLatitude;
	private Double minLatitude;
	private Double maxLongitude;
	private Double minLongitude;

	public CountryDto() {
		super();
	}
	
	public CountryDto(Country countryCode) {
		this.code = countryCode.getIsoa2();
		this.name = countryCode.getName();
		this.continentCode = countryCode.getContinentCode();
		this.latitude = countryCode.getLatitude();
		this.longitude = countryCode.getLongitude();
		this.maxLatitude = countryCode.getMaxLatitude();
		this.minLatitude = countryCode.getMinLatitude();
		this.maxLongitude = countryCode.getMaxLongitude();
		this.minLongitude = countryCode.getMinLongitude();
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the continentCode
	 */
	public String getContinentCode() {
		return continentCode;
	}

	/**
	 * @param continentCode the continentCode to set
	 */
	public void setContinentCode(String continentCode) {
		this.continentCode = continentCode;
	}

	/**
	 * @return the continentName
	 */
	public String getContinentName() {
		return continentName;
	}

	/**
	 * @param continentName the continentName to set
	 */
	public void setContinentName(String continentName) {
		this.continentName = continentName;
	}

	/**
	 * @return the latitude
	 */
	public Double getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return the longitude
	 */
	public Double getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return the maxLatitude
	 */
	public Double getMaxLatitude() {
		return maxLatitude;
	}

	/**
	 * @param maxLatitude the maxLatitude to set
	 */
	public void setMaxLatitude(Double maxLatitude) {
		this.maxLatitude = maxLatitude;
	}

	/**
	 * @return the minLatitude
	 */
	public Double getMinLatitude() {
		return minLatitude;
	}

	/**
	 * @param minLatitude the minLatitude to set
	 */
	public void setMinLatitude(Double minLatitude) {
		this.minLatitude = minLatitude;
	}

	/**
	 * @return the maxLongitude
	 */
	public Double getMaxLongitude() {
		return maxLongitude;
	}

	/**
	 * @param maxLongitude the maxLongitude to set
	 */
	public void setMaxLongitude(Double maxLongitude) {
		this.maxLongitude = maxLongitude;
	}

	/**
	 * @return the minLongitude
	 */
	public Double getMinLongitude() {
		return minLongitude;
	}

	/**
	 * @param minLongitude the minLongitude to set
	 */
	public void setMinLongitude(Double minLongitude) {
		this.minLongitude = minLongitude;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CountryDto [code=" + code + ", name=" + name
				+ ", continentCode=" + continentCode + ", continentName="
				+ continentName + ", latitude=" + latitude + ", longitude="
				+ longitude + ", maxLatitude=" + maxLatitude + ", minLatitude="
				+ minLatitude + ", maxLongitude=" + maxLongitude
				+ ", minLongitude=" + minLongitude + "]";
	}

	
}

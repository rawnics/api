package com.aw.rest.dal.entity;

import java.io.Serializable;


public class Country implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String isoa2;
	private String name;
	private String fullName;
	private String isoa3;
	private Integer countryId;
	private String continentCode;
	private String continentName;
	private Double latitude;
	private Double longitude;
	private Double maxLatitude;
	private Double minLatitude;
	private Double maxLongitude;
	private Double minLongitude;

	public Country() {
	}

	public Country(String isoa2, String name) {
		this.isoa2 = isoa2;
		this.name = name;
	}

	public Country(String isoa2, String name, String fullName, String isoa3,
			Integer countryId, String continentCode, Double latitude,
			Double longitude, Double maxLatitude, Double minLatitude,
			Double maxLongitude, Double minLongitude) {
		this.isoa2 = isoa2;
		this.name = name;
		this.fullName = fullName;
		this.isoa3 = isoa3;
		this.countryId = countryId;
		this.continentCode = continentCode;
		this.latitude = latitude;
		this.longitude = longitude;
		this.maxLatitude = maxLatitude;
		this.minLatitude = minLatitude;
		this.maxLongitude = maxLongitude;
		this.minLongitude = minLongitude;
	}

	/**
	 * @return the isoa2
	 */
	public String getIsoa2() {
		return isoa2;
	}

	/**
	 * @param isoa2 the isoa2 to set
	 */
	public void setIsoa2(String isoa2) {
		this.isoa2 = isoa2;
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
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return the isoa3
	 */
	public String getIsoa3() {
		return isoa3;
	}

	/**
	 * @param isoa3 the isoa3 to set
	 */
	public void setIsoa3(String isoa3) {
		this.isoa3 = isoa3;
	}

	/**
	 * @return the countryId
	 */
	public Integer getCountryId() {
		return countryId;
	}

	/**
	 * @param countryId the countryId to set
	 */
	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
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

	public String getContinentName() {
		return continentName;
	}

	public void setContinentName(String continentName) {
		this.continentName = continentName;
	}



}

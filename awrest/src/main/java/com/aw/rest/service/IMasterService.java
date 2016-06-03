/**
 * 
 */
package com.aw.rest.service;

import java.util.List;

import com.aw.rest.dal.entity.Continent;
import com.aw.rest.dto.CountryDto;
import com.aw.rest.exception.AwrException;


/**
 * @author vishwaka
 *
 */
public interface IMasterService {

	/**
	 * Get all countries
	 * 
	 * @return
	 * @throws AwrException
	 */
	public List<CountryDto> getAllCountries() throws AwrException;
	
	/**
	 * Get countries by country code 
	 * 
	 * @param countryCode
	 * @return
	 * @throws AwrException
	 */
	public CountryDto getCountry(String countryCode) throws AwrException;
	
	/**
	 * Get all continents
	 * 
	 * @return
	 * @throws AwrException
	 */
	public List<Continent> getAllContinents() throws AwrException;
}

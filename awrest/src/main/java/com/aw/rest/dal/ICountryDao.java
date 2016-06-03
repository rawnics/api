/**
 * 
 */
package com.aw.rest.dal;

import java.util.List;

import com.aw.rest.dal.entity.Continent;
import com.aw.rest.dal.entity.Country;
import com.aw.rest.exception.AwrException;


/**
 * @author vishwaka
 *
 */
public interface ICountryDao {

	/**
	 * Get all countries
	 * 
	 * @return
	 * @throws AwrException
	 */
	public List<Country> getAllCountries() throws AwrException;
	
	/**
	 * Get a country by country code
	 * 
	 * @param id
	 * @return
	 * @throws AwrException
	 */
	public Country getCountry(String id) throws AwrException;
	
	/**
	 * Get all continents
	 * 
	 * @return
	 * @throws AwrException
	 */
	public List<Continent> getAllContinents() throws AwrException;
	
}

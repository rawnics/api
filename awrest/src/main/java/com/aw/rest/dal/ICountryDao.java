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

	public List<Country> getAllCountries() throws AwrException;
	public Country getCountry(String id) throws AwrException;
	
	public List<Continent> getAllContinents() throws AwrException;
	
}

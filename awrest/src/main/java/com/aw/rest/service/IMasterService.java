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

	public List<CountryDto> getAllCountries() throws AwrException;
	public CountryDto getCountry(String countryCode) throws AwrException;
	public List<Continent> getAllContinents() throws AwrException;
}

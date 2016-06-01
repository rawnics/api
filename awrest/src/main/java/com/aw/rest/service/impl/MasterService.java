/**
 * 
 */
package com.aw.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aw.rest.dal.ICountryDao;
import com.aw.rest.dal.entity.Continent;
import com.aw.rest.dal.entity.Country;
import com.aw.rest.dto.CountryDto;
import com.aw.rest.exception.AwrError;
import com.aw.rest.exception.AwrException;
import com.aw.rest.service.IMasterService;


/**
 * @author Rahul
 *
 */
@Service
public class MasterService implements IMasterService {
	
	private static final Logger log = LoggerFactory.getLogger(MasterService.class);
	
	@Autowired
	private ICountryDao countryDao;


	/* (non-Javadoc)
	 * @see com.hp.wms.service.IMasterDataService#getAllCountries()
	 */
	@Override
	public List<CountryDto> getAllCountries() throws AwrException {
		log.debug("Get all country info");
		List<Country> cts = countryDao.getAllCountries();
		if(cts==null)
			throw new AwrException(AwrError.DATA_UNAVAILABLE, "");
		List<CountryDto> ccs = new ArrayList<CountryDto>();
		for(Country c : cts)
			ccs.add(new CountryDto(c));
		return ccs;
	}

	/* (non-Javadoc)
	 * @see com.hp.wms.service.IMasterDataService#getCountry(java.lang.String)
	 */
	@Override
	public CountryDto getCountry(String countryCode)
			throws AwrException {
		log.debug("Get country info for "+countryCode);
		Country c = countryDao.getCountry(countryCode);
		return new CountryDto(c);
	}

	/* (non-Javadoc)
	 * @see com.hp.wms.service.IMasterDataService#getAllContinents()
	 */
	@Override
	public List<Continent> getAllContinents() throws AwrException {
		log.debug("Get all continents info");
		List<Continent> cts = countryDao.getAllContinents();
		if(cts==null)
			throw new AwrException(AwrError.DATA_UNAVAILABLE, "");
		return cts;
	}
	
	
}

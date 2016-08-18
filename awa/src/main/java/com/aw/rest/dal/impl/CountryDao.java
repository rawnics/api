/**
 * 
 */
package com.aw.rest.dal.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.aw.rest.dal.ICountryDao;
import com.aw.rest.dal.entity.Continent;
import com.aw.rest.dal.entity.Country;
import com.aw.rest.exception.AwrException;


/**
 * @author vishwaka
 *
 */
@Repository
public class CountryDao implements ICountryDao {

	static final Logger log = LoggerFactory.getLogger(CountryDao.class);
	
	@Autowired
	private JdbcTemplate awrServiceJdbcTemplate; 
	
	
	/* (non-Javadoc)
	 * @see com.aw.rest.dal.ICountryDao#getAllCountries()
	 */
	@Override
	public List<Country> getAllCountries() throws AwrException {
		String query = "SELECT c.isoa2, c.name, c.full_name, c.isoa3, c.country_id, "
				+ " cn.code AS continentCode, cn.name AS continentName, c.latitude, c.longitude, "
				+ " c.max_latitude, c.min_latitude, c.max_longitude, c.min_longitude  "
				+ " FROM country c, continent cn "
				+ " WHERE c.continent_code = cn.code ORDER BY c.name";
		log.info(query);	
		List<Country> countryCodes = awrServiceJdbcTemplate.query(query,new BeanPropertyRowMapper<Country>(Country.class));
		return countryCodes;
	}
	
	/* (non-Javadoc)
	 * @see com.aw.rest.dal.ICountryDao#getCountry(java.lang.String)
	 */
	@Override
	public Country getCountry(String id) throws AwrException {
		String query = "SELECT c.isoa2, c.name, c.full_name, c.isoa3, c.country_id, "
				+ " cn.code AS continentCode, cn.name AS continentName, c.latitude, c.longitude, "
				+ " c.max_latitude, c.min_latitude, c.max_longitude, c.min_longitude  "
				+ " FROM country c, continent cn "
				+ " WHERE c.continent_code = cn.code AND c.isoa2 = ?"
				+ " ORDER BY c.name";
		log.info(query);	
		Country country = awrServiceJdbcTemplate.queryForObject(query,new Object[] {id}, 
									new BeanPropertyRowMapper<Country>(Country.class));
		return country;
	}


	/* (non-Javadoc)
	 * @see com.aw.rest.dal.ICountryDao#getAllContinents()
	 */
	@Override
	public List<Continent> getAllContinents() throws AwrException {
		String query = "SELECT code, name FROM continent";
		log.info(query);	
		List<Continent> continents = awrServiceJdbcTemplate.query(query, 
									new BeanPropertyRowMapper<Continent>(Continent.class));
		return continents;
	}
	
	
	
	
}

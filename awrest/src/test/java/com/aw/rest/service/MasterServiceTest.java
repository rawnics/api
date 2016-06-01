/**
 * 
 */
package com.aw.rest.service;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.aw.rest.dto.CountryDto;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Rahul
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring-module.xml" })
public class MasterServiceTest {
	
	protected static Logger log = LoggerFactory.getLogger(MasterServiceTest.class);

	@Autowired
	private IMasterService masterDataService;
	
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		log.info("------------ START OF TEST ------------" );
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		log.info("------------ END OF TEST ------------" );
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}
	
	
	/**
	 * Countries Test
	 * @throws Exception
	 */
	@Test
	public void testGetCountries() throws Exception {
		
		ObjectMapper om = new ObjectMapper();
		
		List<CountryDto> countries = masterDataService.getAllCountries();
		String jsonProjectVo = om.writeValueAsString(countries);
		log.info(jsonProjectVo);
		Assert.assertNotNull(countries);
	}
	
}

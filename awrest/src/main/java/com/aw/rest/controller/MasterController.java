/**
 * 
 */
package com.aw.rest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aw.rest.constants.AwrRestURIConstants;
import com.aw.rest.dto.CountryDto;
import com.aw.rest.exception.AwrError;
import com.aw.rest.exception.AwrException;
import com.aw.rest.service.IMasterService;
import com.aw.rest.service.utility.Utility;
import com.aw.rest.utility.MessageSourceUtility;
import com.aw.rest.vo.Countries;
import com.aw.rest.vo.CountryVo;
import com.aw.rest.vo.ErrorVo.ErrorEnum;

/**
 * @author Rahul
 *
 */
@Controller
public class MasterController {
	
	static final Logger log = LoggerFactory.getLogger(MasterController.class);
	
	@Autowired
	private MessageSourceUtility messageSourceUtility;
	
	@Autowired
	private IMasterService masterDataService;



	/** Get All Country Codes
	 * 
	 * @return
	 * @throws AwrException
	 */
	@RequestMapping(value = AwrRestURIConstants.GET_ALL_COUNTRY_CODES, method = RequestMethod.GET)
	public Countries getCountryContinentCodes(HttpServletRequest request ,
			HttpServletResponse httpResponse,
			Locale locale) throws AwrException {
		Countries countries = new Countries();
		List<CountryDto> countryDtoList =  masterDataService.getAllCountries();
		if(Utility.isValidCollection(countryDtoList)){
			List<CountryVo> countryVoList = new ArrayList<>();
			for(CountryDto countryDto : countryDtoList)
				countryVoList.add(new CountryVo(countryDto));
			countries.setCountrycode(countryVoList);
		}else{
			AwrError ctfsError = AwrError.forValue(51);
			Object[] values = null;
			String message =  messageSourceUtility.getMessageValue(ctfsError, values, locale);
			throw new AwrException(message,ErrorEnum.INFO.toString());
		}
		return countries;
	}
	
	/**
	 * Get a country by code
	 * 
	 * @param countryCode
	 * @param request
	 * @param httpResponse
	 * @param locale
	 * @return
	 * @throws AwrException
	 */
	@RequestMapping(value = AwrRestURIConstants.GET_COUNTRY_BY_CODE, method = RequestMethod.GET)
	public Countries getCountryContinentCodes(@PathVariable("countryCode") String countryCode,
			HttpServletRequest request ,HttpServletResponse httpResponse,
			Locale locale) throws AwrException {
		Countries countries = new Countries();
		CountryDto countryDto =  masterDataService.getCountry(countryCode);
		if(countryDto != null){
			List<CountryVo> countryVoList = new ArrayList<>();
			countryVoList.add(new CountryVo(countryDto));
			countries.setCountrycode(countryVoList);
		}else{
			AwrError ctfsError = AwrError.forValue(51);
			Object[] values = null;
			String message =  messageSourceUtility.getMessageValue(ctfsError, values, locale);
			throw new AwrException(message,ErrorEnum.INFO.toString());
		}
		return countries;
	}
	
}

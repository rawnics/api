
package com.aw.rest.exception;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Rahul Vishwakarma
 * 
 * @category Exceptions AwrError enumeration keeps all the error codes and their
 *           respective message identification codes
 * 
 */
public enum AwrError {

	DATABASE_READ(1, "message.error.database.read"), 
	DATABASE_WRITE(2, "message.error.database.writeoperation"),
	DATABASE_UPDATE(3, "message.error.database.updateoperation"),
	DATABASE_DELETE(4, "message.error.database.deleteoperation"),
	DATABASE_COMPUTE(5,	"message.error.database.compute"), 
	DATABASE_VALIDATE(6,"message.error.database.validate"), 
	DATABASE_CACHE(7,"message.error.database.cache"),
	DATABASE_REQUIRED_DATA_UNAVAILABLE(10, "message.error.database.required.nodata"),

	DATABASE_DELETE_SUCCESS(11, "message.info.deleteoperation.success"),
	DATABASE_INSERT_SUCCESS(12, "message.info.insertoperation.success"),
	DATABASE_UPDATE_SUCCESS(13, "message.info.updateoperation.success"),

	//Organization Related Error Codes
	DATABASE_READ_ORGANIZATION_ID_NOT_AVAILABLE(40,"message.error.organization.data.notavilable"),
	
	DATA_UNAVAILABLE(51,"message.error.data.unavailable"),
	DATA_OFFICIAL_UNAVAILABLE(52,"message.error.data.official.unavailable"),
	DATA_PARSE_ERROR(53,"message.error.data.parse"),

	//GET Master data
	COUNTRY_CODES_NO_DATA(210,"message.countrycodes.nodatainfo"),
	ORGANIZATION_CODES_NO_DATA(211,"message.organization.nodatainfo"),
	
	CONFIGURATION_NOT_FOUND(301,"message.error.configuration.key.invalid");
	
	private final int errorId;
	private final String errorMessageId;

	/**
	 * @param errorId
	 * @param errorMessageId
	 */
	private AwrError(int errorId, String errorMessageId) {
		this.errorId = errorId;
		this.errorMessageId = errorMessageId;
	}

	private static final Map<Integer, AwrError> typesByValue = new HashMap<Integer, AwrError>();
	static {
        for (AwrError type : AwrError.values()) {
            typesByValue.put(type.errorId, type);
        }
    }
	
	public static AwrError forValue(int value) {
        return typesByValue.get(value);
    }
	
	/**
	 * @return the errorId
	 */
	public int getErrorId() {
		return errorId;
	}

	/**
	 * @return the errorMessageId
	 */
	public String getErrorMessageId() {
		return errorMessageId;
	}

	@Override
	public String toString() {
		return errorId + ": " + errorMessageId;
	}

}
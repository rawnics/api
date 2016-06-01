
package com.aw.rest.constants;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class AwrConstants {

    public static final String ERROR_PAGE = "errorpage";
	public static final String EXCEPTION_ERROR_CODE_DELIMITER = "$";	
	
	
	public static final String REG_EX_CLEAN_FILENAME="[^a-zA-Z0-9\\.\\s]+";
	static final String REG_EX_FILENAME_WITHOUT_EXTN="[.][^.]+$";
	public final static Charset ENCODING = StandardCharsets.UTF_8;
	String sectionBreak = "\n\n";
	public static String lineBreak = "\n";
	
	public static final char DATA_CSV_DELIMITER = ',';
	
}

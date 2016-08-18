package com.aw.rest.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Locale;
import java.util.jar.Manifest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Category;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aw.rest.constants.AwrRestURIConstants;

/**
 * Used to configure or validate system services and settings.
 * 
 * @author Rahul Vishwakarma
 * 
 */
@RestController
public class AdminController {

    static final Logger log = LoggerFactory.getLogger(AdminController.class);

    /**
     * Used to modify web application logging level.
     * 
     * @param level
     *            Options: INFO, WARN, ERROR, DEBUG, TRACE, OFF, ALL
     * @return
     * @throws Exception 
     */
    //@PreAuthorize("hasRole('GET_CONFIG_LOGGING_LEVEL')")	
    @RequestMapping(value = AwrRestURIConstants.GET_CONFIG_LOGGING_LEVEL, method = RequestMethod.GET)
    public String setLogLevel(String level, HttpServletResponse response) throws Exception {
		try {
		    Level currentLevel = LogManager.getRootLogger().getLevel();
		    if (level == null || level.isEmpty()) {
		    	log.debug("Invalid logging options, " + level);
		    	return "Usage: "+AwrRestURIConstants.GET_CONFIG_LOGGING_LEVEL+"?level=? level can be (INFO, WARN, ERROR, DEBUG, TRACE, OFF, ALL). Current level: "
		    	+ LogManager.getRootLogger().getLevel();
		    }
		    Level desiredlevel = Level.toLevel(level);
		    LogManager.getRootLogger().setLevel(desiredlevel);
		    Level newLevel = LogManager.getRootLogger().getLevel();
		    if(newLevel.toString().equalsIgnoreCase(currentLevel.toString())){
		    	log.info("Logging level changed from " + currentLevel + " to "
			    + newLevel);
		    }
			return "New Level @ " + newLevel;
		} catch (Exception ex) {
		    ex.getStackTrace();
		    log.error("error @ setLogLevel method. ",ex);
		    return ex.getMessage()+" : ERROR";	    
		}

    }

    /**
     * Set Log Level for package
     * @param classes
     * @param level
     * @param response
     * @return String
     */
    //@PreAuthorize("hasRole('GET_CONFIG_LOGGING_PACKAGE')")	
    @RequestMapping(value = AwrRestURIConstants.GET_CONFIG_LOGGING_PACKAGE, method = RequestMethod.GET)
    public String setLogLevelForPackage(String classes, String level, HttpServletResponse response) throws Exception {
		try {
		    if (level == null || level.isEmpty()) {
		    	log.warn("Invalid logging options, " + level);
				return "Usage: "+AwrRestURIConstants.GET_CONFIG_LOGGING_PACKAGE+"?classes=?&level=? "
						+ " classes: space seperated class expressions, level: can be (INFO, WARN, ERROR, DEBUG, TRACE, OFF, ALL). "
						+ " For others logging is set to WARN ";
		    }
		    Level desiredlevel = Level.toLevel(level);
		    org.apache.log4j.Logger root =  LogManager.getRootLogger();
		    String[] tokens = null;
		    if(classes==null){
		    	classes="";
		    	tokens = new String[0];
		    }else{
		    	tokens = classes.split(" ");
		    }
		    StringBuilder sb = new StringBuilder();
		    StringBuilder sbOthers = new StringBuilder();
		    //set for root logger
		    ArrayList<String> doneList = new ArrayList<String>();
		    for(String token : tokens){
				if(root.getName().startsWith(token)){
				    root.setLevel(desiredlevel);
				    doneList.add(root.getName());
				    sb.append(root.getName()).append('=').append(desiredlevel).append("\n");
				}else{
				    if(!doneList.contains(root.getName())){
				    	root.setLevel(Level.WARN);
				    	sbOthers.append(root.getName()).append('=').append(Level.WARN).append("\n");
				    }
				}	
				Enumeration<?> allLoggers = root.getLoggerRepository().getCurrentCategories();
				//set for other loggers
				while (allLoggers.hasMoreElements()){
				    Category tmpLogger = (Category) allLoggers.nextElement();
				    if(tmpLogger.getName().contains(token)){
						tmpLogger.setLevel(desiredlevel);
						doneList.add(tmpLogger.getName());
						sb.append(tmpLogger.getName()).append('=').append(desiredlevel).append("\n");
				    }else{
						if(!doneList.contains(tmpLogger.getName())){
						    tmpLogger.setLevel(Level.WARN);
						    sbOthers.append(tmpLogger.getName()).append('=').append(Level.WARN).append("\n");
						}
				    }
				}	    
		    }
		    return "OK. \n" + sb +"\n"+ sbOthers;
		} catch (Exception ex) {
			 ex.getStackTrace();
			 log.error("error @ setLogLevelForPackage method. ",ex);
			 return ex.getMessage()+" : ERROR";	    
		}
    }

    /**
     * Version information for the application
     * @return String
     */
    //@PreAuthorize("hasRole('GET_APPLICATION_VERSION')")	
    @RequestMapping(value = AwrRestURIConstants.GET_APPLICATION_VERSION, method = RequestMethod.GET)
    public String getVersion(HttpServletRequest request,
    		HttpServletResponse response, 
    		Locale locale) {
		try {
			InputStream in = request.getSession().getServletContext().getResourceAsStream("/META-INF/MANIFEST.MF");
		    if(in==null)
		    	return "/META-INF/MANIFEST.MF Not Found!";
		    Manifest manifest = new Manifest(in);
		    java.util.jar.Attributes attr = manifest.getMainAttributes();
		    String value = attr.getValue("SCM-Revision");
		    String time = attr.getValue("timestamp");
		    String builtBy = attr.getValue("Built-By");
		    return "Build Version:" + value + ", Timestamp: " + time + ", Built By: "+builtBy;
		} catch (Exception ex) {
		    ex.printStackTrace();
		    log.error("Error reading manifiest. ",ex);
		    return ex.getMessage()+" : ERROR";
		}
    }

    
}
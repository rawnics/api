
package com.aw.rest.exception;

import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.aw.rest.service.utility.Utility;
/**
 * @author Rahul Vishwakarma
 *
 */
@Aspect
public class AwrLoggerAspect {
	
    private static final Logger log = LoggerFactory.getLogger(AwrLoggerAspect.class);

    @Autowired
    private Utility utility;

    /**
     * Default Constructor
     */
    public AwrLoggerAspect() {	}

    enum IssueType{
    	ISSUE_URL_SUFFIX, OTHER, NONE
    }
    public static ConcurrentHashMap<String,Object> responseTime = new ConcurrentHashMap<String,Object>();

    /**
     * 
     * @param params
     * @return
     */
    public String getRepresentation(Object [] params){
		StringBuilder sb = new StringBuilder();
		if(params!=null){
		    String value = null;
		    for(int i=0;i<params.length;i++){
				value = params[i] + ",";
				if(value.contains("@")){
				    value = "";
				}
				sb.append(value);
		    }
		    if(sb.length()>0)
			return sb.substring(0, sb.length() - 1);
		}
		return sb.toString();
    }
    /**
     * Get Client IP Address based on the request
     * @param request
     * @return String IPAddress
     */
    public String getClientIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-FORWARDED-FOR");  
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
		    ip = request.getHeader("Proxy-Client-IP");  
		}  
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
		    ip = request.getHeader("WL-Proxy-Client-IP");  
		}  
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
		    ip = request.getHeader("HTTP_CLIENT_IP");  
		}  
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
		    ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
		}  
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
		    ip = request.getRemoteAddr();  
		}  
		return ip;  
    }  

    /**
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("execution(@*..RequestMapping * * (..))")
    public Object log_around(ProceedingJoinPoint pjp) throws Throwable {
		Object obj = null;
		IssueType issueType = IssueType.NONE;
		String error="";
		try{
		    String methodSignature =  pjp.getSignature()+"";
		    String args = getRepresentation(pjp.getArgs());
		    String requestType = "";
		    log.info("START {}-{}", methodSignature+" ["+Thread.currentThread().getId()+"]",args);
		    ServletRequestAttributes sra = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		    String urlPath="";	   
		    String session = "";
		    String clientIp = "";
		    if(sra!=null){
		    	HttpServletRequest req = sra.getRequest();
		    	//userAgent = req.getHeader("User-Agent");
				if(req!=null){
				    urlPath = req.getServletPath();
				    session = req.getSession().getId();
				    clientIp = getClientIpAddr(req);
				    requestType = req.getMethod().toUpperCase();
				}            
		    }
	
		    long start = System.currentTimeMillis();
		    if(urlPath.endsWith("/")){
		    	issueType = IssueType.ISSUE_URL_SUFFIX;
		    	obj = null;
		    }
		    else
		    	obj = pjp.proceed();
		    
		    if(obj!=null && (obj instanceof ModelAndView)){
		    	requestType = "page";
		    }
		    int elapsedTime = (int) (System.currentTimeMillis() - start);        
		    log.info("SERVED "+session +" "+requestType+ " REQ {} by user@"+clientIp+", time {} mills; args ["+args+"]", urlPath + " ["+ Thread.currentThread().getId() +"]" , elapsedTime);
		} catch(Exception ex){
		    issueType = IssueType.OTHER;
		    error = ex.getMessage();
		    log.error(ex.getMessage(),ex);
		}
		
		if(obj==null){
		    switch(issueType){
		    	case ISSUE_URL_SUFFIX:throw new Exception("URL ends with trailling /"); 
		    	case OTHER:throw new Exception(error);
			default:
				break;
		    }	    
		}
		return obj;
    }

    /**
     * 
     * @param ex
     */
    @AfterThrowing(pointcut="execution(public *  com.aw.rest.controller.*.*(..))",throwing="ex")    
    public void MethodError(Exception ex){  
    	log.error("@Exception {}", ex.toString()); 
    }  

}

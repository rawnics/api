package com.aw.rest.security;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
public class SessionListener implements HttpSessionListener {
 
    private static final Logger log = LoggerFactory.getLogger(SessionListener.class);
    @Override
    public void sessionCreated(HttpSessionEvent arg0) {
	log.info("Session Created: "+arg0.getSession().getId());	
          
    }
 
    @Override
    public void sessionDestroyed(HttpSessionEvent arg0) {
	log.info("Session Destroyed: "+arg0.getSession().getId());	
    }	
 }

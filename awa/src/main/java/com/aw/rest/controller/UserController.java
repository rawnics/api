package com.aw.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.aw.rest.utility.MessageSourceUtility;

@RestController
public class UserController {

static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private MessageSourceUtility messageSourceUtility;
	
}

package com.tpa.sb.profile.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ProfileService {

	public static String DEV_STRING="desarrollo";
	
	public static String PROD_STRING="produccion";
	
	private final Logger logger = LoggerFactory.getLogger(ProfileService.class);

	abstract public String getActiveProfile();

	public void printActiveProfile() {
		for (int i = 0; i < 10; i++) {
			logger.info(getActiveProfile());
		}
	}

}
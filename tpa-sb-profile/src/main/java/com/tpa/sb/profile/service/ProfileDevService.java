package com.tpa.sb.profile.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({ "dev", "default" })
public class ProfileDevService extends ProfileService {

	@Override
	public String getActiveProfile() {
		
		return  DEV_STRING;
	}

}
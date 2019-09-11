package com.tpa.sb.profile.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({ "prod" })
public class ProfileProdService extends ProfileService {

	@Override
	public String getActiveProfile() {
		return PROD_STRING;
	}

}
package com.tpa.sb.profile;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.tpa.sb.profile.service.ProfileService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class TestDevProfile {

	@Autowired
	ProfileService profileService;

	@Test
	public void testRainingProfile() {
		String output = profileService.getActiveProfile();
		assertThat(output).contains(ProfileService.DEV_STRING);
	}

}
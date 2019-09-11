package com.tpa.sb.profile;

import org.junit.Rule;
import org.junit.Test;
import org.springframework.boot.test.rule.OutputCapture;

public class TestProfiles {

	@Rule
	public OutputCapture outputCapture = new OutputCapture();

	@Test
	public void testDefaultProfile() throws Exception {
		System.setProperty("spring.profiles.active", "dev");
		TpaSbProfileApplication.main(new String[0]);
		String output = this.outputCapture.toString();
//		assertThat(output).contains(ProfileService.DEV_STRING);
		
	}

	@Test
	public void testProdProfile() throws Exception {

		System.setProperty("spring.profiles.active", "prod");
		TpaSbProfileApplication.main(new String[0]);
		String output = this.outputCapture.toString();
//		assertThat(output).contains(ProfileService.PROD_STRING);
		
	}

	@Test
    public void testRainingProfile_DEV() throws Exception {
    	TpaSbProfileApplication.main(new String[]{"--spring.profiles.active=dev"});
        String output = this.outputCapture.toString();
//        assertThat(output).contains(ProfileService.DEV_STRING);
    }
	@Test
    public void testRainingProfile_Prod() throws Exception {
		TpaSbProfileApplication.main(new String[]{"--spring.profiles.active=prod"});
        String output = this.outputCapture.toString();
//        assertThat(output).contains(ProfileService.PROD_STRING);
    }
	

}
package com.tpa.mercadolibre.ejercicio;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MlTestApplication {

	@Autowired(required = true)
	CustomizationBean contextInfo;

	@PostConstruct
	void started() {

		// create time zone object
		// TimeZone tzone = TimeZone.getTimeZone("America/Los_Angeles");
		TimeZone tzone = TimeZone.getTimeZone("America/Argentina/Mendoza");

		// set time zone to default
		tzone.setDefault(tzone);

		// checking default time zone
		System.out.println("Default time zone:" + tzone);

		System.out.println(contextInfo.printInfo());

	}

	public static void main(String[] args) {
		SpringApplication.run(MlTestApplication.class, args);

	}

}

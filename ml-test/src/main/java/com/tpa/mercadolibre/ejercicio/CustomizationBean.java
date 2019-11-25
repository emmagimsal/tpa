package com.tpa.mercadolibre.ejercicio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.stereotype.Component;

@Component
public class CustomizationBean implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {

	private final Logger logger = LoggerFactory.getLogger(CustomizationBean.class);

	@Value("${server.port}")
	String serverPort;

	@Value("${context.path}")
	String contexPath;

	@Override
	public void customize(ConfigurableServletWebServerFactory container) {

		container.setContextPath("/" + contexPath);
//		logger.info("################################################################################");
//		logger.info(printInfo());
//		logger.info("################################################################################");
	}

	public String printInfo() {

		String info = "Open browser  http://localhost:" + serverPort + "/" + contexPath + "\n";

		return info;
	}

}
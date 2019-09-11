package com.tp.db.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.stereotype.Component;

@Component
public class CustomizationBean implements
		WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {

	private final Logger logger = LoggerFactory
			.getLogger(TpaDbJdbcApplication.class);

	/* inyección de variables applicatio properties */
	@Value("${server.port}")
	String serverPort;

	@Value("${contexPath}")
	String contexPath;

	@Value("${spring.h2.console.path}")
	String consolePath;

	@Override
	public void customize(ConfigurableServletWebServerFactory container) {

		container.setContextPath("/" + contexPath);

		logger.info(printInfo());
	}

	public String printInfo() {

		String info = "abrir navegador en  http://localhost:" + serverPort
				+ "/" + contexPath + "\n";

		info += "abrir navegador en  http://localhost:" + serverPort + "/"
				+ contexPath + consolePath + "\n";

		info += "DB folder" + System.getProperty("user.home") + "\n";

		return info;
	}

	/*
	 * 
	 * Ejercicio 1 : Modificar la variable contextPath en
	 * "application.properties"
	 * 
	 * 
	 */

}
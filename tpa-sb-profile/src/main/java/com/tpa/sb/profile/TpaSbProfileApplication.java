package com.tpa.sb.profile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.tpa.sb.profile.service.ProfileService;

@SpringBootApplication
public class TpaSbProfileApplication {

private final Logger logger = LoggerFactory.getLogger(TpaSbProfileApplication.class);
	
	@Value("${limite}")
	String limite;
	
	@Autowired
    private ProfileService profileService;
	

	public static void main(String[] args) throws Exception {
		SpringApplication.run(TpaSbProfileApplication.class, args);
	}

//	@Override
//	public void run(ApplicationArguments args) throws Exception {
////		env.setActiveProfiles("dev");
//		
//		profileService.printActiveProfile();
//		
//		logger.info("Application started with command-line arguments: {}", Arrays.toString(args.getSourceArgs()));
//		logger.info("NonOptionArgs: {}", args.getNonOptionArgs());
//		logger.info("OptionNames: {}", args.getOptionNames());
//
//		for (String name : args.getOptionNames()) {
//			logger.info("arg-" + name + "=" + args.getOptionValues(name));
//		}
//
//	}
	
	@Bean
	CommandLineRunner init() {
		return args -> {
			int limite_= Integer.valueOf(limite);
			
			for (int i = 0; i < limite_; i++) {
				logger.info("OptionNames: {}",i);
				
				logger.debug("OptionNames: {}",i);
			}
		};
	}

}

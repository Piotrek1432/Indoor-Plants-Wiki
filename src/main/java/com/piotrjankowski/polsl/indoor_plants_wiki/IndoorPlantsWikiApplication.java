package com.piotrjankowski.polsl.indoor_plants_wiki;

import com.piotrjankowski.polsl.indoor_plants_wiki.logic.FileStorageConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Validator;

@EnableAsync
@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageConfig.class
})
public class IndoorPlantsWikiApplication {

	public static void main(String[] args) {
		SpringApplication.run(IndoorPlantsWikiApplication.class, args);
	}

	@Bean
	Validator validator() {
		return new LocalValidatorFactoryBean();
	}

}

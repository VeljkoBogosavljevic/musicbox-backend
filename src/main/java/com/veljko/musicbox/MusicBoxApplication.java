package com.veljko.musicbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.veljko.musicbox.configuration.RestTemplateConfiguration;

@SpringBootApplication
public class MusicBoxApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusicBoxApplication.class, args);
	}
	
	@Bean("restTemplate")
	public RestTemplate getRestTemplate() {
		return new RestTemplate(RestTemplateConfiguration.configureHttpClientRequest());
	}

}

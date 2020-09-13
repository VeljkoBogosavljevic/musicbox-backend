package com.veljko.musicbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*");
			}
		};
	}

}

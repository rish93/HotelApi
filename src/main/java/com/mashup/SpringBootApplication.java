package com.mashup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.databind.ObjectMapper;



@ComponentScan(basePackages= {"com.mashup"})
@EnableJpaRepositories
@EnableAutoConfiguration
@Configuration
@EntityScan("com.mashup.hotel.model") 
@PropertySource("classpath:/application.properties")
public class SpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootApplication.class, args);
	}
	@Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	   @Bean
	   public ObjectMapper getObjectMapper() {
	     return new ObjectMapper();
	   }

	   @Bean
	   public MappingJackson2HttpMessageConverter messageConverter() {
	     MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
	     converter.setObjectMapper(getObjectMapper());
	     return converter;
	   }
	   
}

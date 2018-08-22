package com.mashup;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@PropertySource("classpath:application.properties")
public class SwaggerConfig {
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select().apis(RequestHandlerSelectors.basePackage("com.mashup.hotel.controller"))
               .paths(PathSelectors.any())
                .build().pathMapping("/").apiInfo(metadata())
               .securitySchemes(Collections.singletonList(apiKey()));
    }
    
    
    private ApiInfo metadata() {
        return new ApiInfoBuilder()
                .title("Hotel REST API documentation")
                .description("see https://github.com/rish93/HotelApi")
                .version("1.0")
                .license("licence 1.0")
                .licenseUrl("https://github.com/rish93/HotelApi/LICENSE")
                .contact("rish93@gmail.com")
                .build();
    }
    
    private ApiKey apiKey() {
        return new ApiKey("apiKey", "Authorization", "header");
        }
}
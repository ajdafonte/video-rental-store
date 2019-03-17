package com.casumo.hometest.videorentalstore.config;

import java.time.OffsetDateTime;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * SwaggerConfig class.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig
{
    @Bean
    public Docket api()
    {
        return new Docket(DocumentationType.SWAGGER_2)
            .groupName("Video Rental Store")
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.casumo.hometest.videorentalstore"))
            .build()
            .directModelSubstitute(OffsetDateTime.class, String.class)
            .useDefaultResponseMessages(false)
            .apiInfo(apiInfo());
    }

    /**
     * API Info as it appears on the swagger-ui page
     */
    private ApiInfo apiInfo()
    {
        return new ApiInfo(
            "Video Rental Store",
            "Video Rental Store API.<BR/>"
                + "<P>System for managing the rental administration of a video rental store</P>",
            "0.1.0",
            "API terms of service URL",
            new Contact("Alejandro Caires", "", ""),
            "License of API",
            "API license URL",
            Collections.emptyList()
        );
    }

}

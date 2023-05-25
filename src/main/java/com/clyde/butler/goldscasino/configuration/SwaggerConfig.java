package com.clyde.butler.goldscasino.configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

// @TODO removed enablement of swagger here as it ends up not picking up the endpoints for some reason.

//@Configuration
//@EnableSwagger2
public class SwaggerConfig {

//    @Bean
    public Docket swaggerConfiguration() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.clyde.butler.goldscasino"))
                .paths(PathSelectors.ant("/casino/*"))
                .build()
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {

        return new ApiInfo(
                "Golds casino API",
                "This is the API documentation for the POC Golds casino API.",
                "1.0",
                "Terms of service",
                new Contact("Clyde Butler", "https://www.linkedin.com/in/clyde-butler/", "clyde.javacoder@gmail.com"),
                "https://github.com/clyde-butler/gold-scasino-api/blob/master/LICENSE",
                "",
                Collections.emptyList());
    }
}

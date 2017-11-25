package com.n26.api.webtransactions.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket analyticsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(regex(".*"))
                .build()
                .apiInfo(metaData());
    }

    private ApiInfo metaData() {
        return  new ApiInfo(
                "Spring Boot REST API",
                "Web Transactions API N26 Code Test",
                "0.0.1",
                "Terms of service",
                new Contact("Jackson Coelho", null, "fcjack@gmail.com"),
                "Apache License Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0");
    }
}

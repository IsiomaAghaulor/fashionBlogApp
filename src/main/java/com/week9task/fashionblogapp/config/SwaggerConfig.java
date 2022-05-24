package com.week9task.fashionblogapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private ApiKey apiKey(){
        return new ApiKey("JWT", AUTHORIZATION_HEADER,"header");
    }
    private ApiInfo apiInfo(){
        return new ApiInfo(
                "spring boot blog rest api",
                "spring boot blog rest api documentation",
                "1",
                "terms of service",
                new Contact("isioma", "www.javaguides.new","isioma@gmail.com"),
                "License of Api",
                "API license url",
                Collections.emptyList()
                );
    }
    @Bean
    public Docket api(){
    return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .securityContexts(Arrays.asList(securityContext()))
            .securitySchemes(Arrays.asList(apiKey()))
            .select()
            .apis(RequestHandlerSelectors.basePackage(""))
            .paths(PathSelectors.any())
            .build();
    }

    private SecurityContext securityContext(){
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }

    private List<SecurityReference> defaultAuth(){
        AuthorizationScope authorizationScope  = new AuthorizationScope("global", "accessEveerything");
        AuthorizationScope [] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
    }



}

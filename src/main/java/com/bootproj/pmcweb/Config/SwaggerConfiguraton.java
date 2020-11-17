package com.bootproj.pmcweb.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

@Configuration
@EnableSwagger2
public class SwaggerConfiguraton {

    @Autowired
    private Environment env;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .consumes(getConsumeContentTypes())
                .produces(getProduceContentTypes())
                .apiInfo(getApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bootproj.pmcweb.Controller"))
                .paths(PathSelectors.ant("/user/**"))
                .build();

    }

    private Set<String> getConsumeContentTypes(){
        Set<String> consumes = new HashSet<>();
        consumes.add("application/json;charset=UTF-8");
        consumes.add("application/x-www-form-urlencoded");
        return consumes;
    }

    private Set<String> getProduceContentTypes(){
        Set<String> produces = new HashSet<>();
        produces.add("application/json;charset=UTF-8");
        return produces;
    }

    private ApiInfo getApiInfo(){
        Properties properties = new Properties();
        return new ApiInfoBuilder()
                .title("API")
                .description("[PMC]API")
                .contact(new Contact("PMC Swagger", "https://github.com/kymkyj/pmcFirstWeb", env.getProperty("email.admin.account")))
                .version("2.0")
                .build();
    }
}

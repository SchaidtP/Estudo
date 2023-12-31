package br.com.erudio.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("RESTful API with Java 19 and Spring Boot 3.0.1")
                        .version("v1")
                        .description("Some description about your API")
                        .termsOfService("https://termsOfService.com.br")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://license.com.br"))
                );
    }
}

package com.leonardolorenz.apirest.apirest.Swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition
public class SwaggerConfig {
    @Bean
    public OpenAPI api(){
        return new OpenAPI().info(new Info().title("Api Rest con Postgres y Spring Boot").version("1.0")
                .contact(new Contact().name("Leonardo Lorenzatti").url("https://github.com/JustAKing12").email("lorenzattileo@gmail.com"))
                .description("Api Rest encargada de realizar peticiones a la base de datos de Personas."));
    }
}

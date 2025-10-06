package com.example.ms2_proyecto.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("MS2 - Estudiantes y Matrículas API")
                        .version("1.0.0")
                        .description("Microservicio para gestión de estudiantes y matrículas - Plataforma Educativa")
                        .contact(new Contact()
                                .name("Equipo de Desarrollo")
                                .email("equipo@educativa.com")));
    }
}
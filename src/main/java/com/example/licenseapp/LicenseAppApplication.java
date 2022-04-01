package com.example.licenseapp;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import java.net.URI;

import static org.springframework.web.servlet.function.RequestPredicates.GET;
import static org.springframework.web.servlet.function.RouterFunctions.route;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Example of sync REST app",
                version = "1.0",
                description = "Example of sync REST app"))
@Log4j2
public class LicenseAppApplication {

    public static void main(String[] args) {

        SpringApplication.run(LicenseAppApplication.class, args);
    }

    @Bean
    RouterFunction<ServerResponse> routerFunction() {

        return route(GET("/swagger"), req ->
                ServerResponse.temporaryRedirect(URI.create("swagger-ui.html")).build());
    }
}

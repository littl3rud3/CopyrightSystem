package com.example.licenseapp;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Log4j2
@SpringBootApplication
@OpenAPIDefinition(
    info = @Info(title = "Example of sync REST app", version = "1.0", description = "Example of sync REST app"))
public class LicenseAppApplication {
    
    public static void main(String[] args) {
        
        SpringApplication.run(LicenseAppApplication.class, args);
    }
}

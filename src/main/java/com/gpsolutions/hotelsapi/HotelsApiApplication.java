package com.gpsolutions.hotelsapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "HotelsAPI",
                version = "1.0.0",
                description = "Test task for GPSolutions",
                contact = @Contact(
                        name = "Dmitry Pasmurtsev",
                        email = "pasmurtsevd@gmail.com"
                )

        )
)
public class HotelsApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotelsApiApplication.class, args);
    }
}

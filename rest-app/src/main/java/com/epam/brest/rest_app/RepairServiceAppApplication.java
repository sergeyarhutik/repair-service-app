package com.epam.brest.rest_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@ComponentScan(basePackages = {"com.epam.brest"})
@ImportResource(locations = {"classpath:test-db.xml"})
@SpringBootApplication
public class RepairServiceAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(RepairServiceAppApplication.class, args);
    }

}

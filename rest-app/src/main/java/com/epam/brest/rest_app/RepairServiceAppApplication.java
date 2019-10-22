package com.epam.brest.rest_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.epam.brest"})
@SpringBootApplication
public class RepairServiceAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(RepairServiceAppApplication.class, args);
    }

}

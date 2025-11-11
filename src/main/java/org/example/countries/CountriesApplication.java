package org.example.countries;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CountriesApplication {

    public static void main(String[] args) {
        SpringApplication.run(CountriesApplication.class, args);
    }

}

package com.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


//ebut-comment

@SpringBootApplication
@EnableJpaAuditing
public class InventoryAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryAppApplication.class, args);
    }

}

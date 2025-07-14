package com.sporty.bet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.sporty.*"})
public class SportBetApplication {

    public static void main(String[] args) {
        SpringApplication.run(SportBetApplication.class, args);
    }

}

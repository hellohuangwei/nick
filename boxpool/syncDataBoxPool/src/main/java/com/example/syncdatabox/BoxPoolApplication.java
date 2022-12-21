package com.example.syncdatabox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class BoxPoolApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoxPoolApplication.class, args);
    }

}

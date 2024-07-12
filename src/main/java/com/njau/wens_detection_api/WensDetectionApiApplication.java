package com.njau.wens_detection_api;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={ "com.njau.wens_detection_api.mapper"})
public class WensDetectionApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(WensDetectionApiApplication.class, args);
    }

}

package com.example.samplekafkaproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SampleKafkaProducerApplication {

    public static void main(String[] args) {

        SpringApplication.run(SampleKafkaProducerApplication.class, args);
        String javaVersion = System.getProperty("java.version");
        System.out.println("Server running on Java version:  " +  javaVersion);
    }

}

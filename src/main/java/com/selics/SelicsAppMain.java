package com.selics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This is the main class that is used to start the REST API using SpringBoot Application using an
 * embedded Tomcat server in default port 8080
 *
 * @author Karthikeyan Varadarajan
 */
@SpringBootApplication
public class SelicsAppMain {
    public static void main(String[] args) {
        SpringApplication.run(SelicsAppMain.class,args);
    }
}
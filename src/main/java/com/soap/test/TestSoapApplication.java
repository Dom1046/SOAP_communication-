package com.soap.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class TestSoapApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestSoapApplication.class, args);
    }

}

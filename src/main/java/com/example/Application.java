package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Seyyu
 * @version 1.0
 * @date 2022-9-10
 */
@SpringBootApplication
@MapperScan(basePackages = "com.example.mapper")

public class Application {
    public static void main(String[] args) {

        SpringApplication.run(Application.class,args);
    }
}

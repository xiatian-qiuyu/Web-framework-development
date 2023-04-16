package com.briup;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan("com.briup.web")
@MapperScan("com.briup.mapper")
public class BriupShopApplication {
    public static void main(String[] args) {
        System.out.println("http://localhost:8081/");
        SpringApplication.run(BriupShopApplication.class, args);

    }
}

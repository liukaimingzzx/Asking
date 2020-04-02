package com.lkm.asking;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lkm.asking.dao")
public class AskingApplication {

    public static void main(String[] args) {
        SpringApplication.run(AskingApplication.class, args);
    }

}

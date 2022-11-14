package com.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        System.setProperty("aws.accessKeyId", "AKIARPGYHCATDRWWKRIK");
        System.setProperty("aws.secretKey", "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");
        System.setProperty("aws.region", "ap-northeast-2");
        SpringApplication.run(BackendApplication.class, args);
    }

}

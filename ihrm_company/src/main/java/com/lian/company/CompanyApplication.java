package com.lian.company;


import com.lian.common.utils.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

// 1. 配置SpringBoot的包扫描
@SpringBootApplication(scanBasePackages = "com.lian.company")
// 2. 配置jpa注解的扫描
@EntityScan(value = "com.lian.domain.company")

public class CompanyApplication {

    // 启动方法
    public static void main(String[] args) {
        SpringApplication.run(CompanyApplication.class, args);
    }

    // 将IdWorker注入company工程中
    @Bean
    public IdWorker idWorker() {
        return new IdWorker();
    }

}

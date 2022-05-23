package com.fly;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication
@EnableDiscoveryClient
@RefreshScope
@MapperScan("com.fly.admin.mapper")
public class FlySystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlySystemApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  Fly System Start-up success  ლ(´ڡ`ლ)ﾞ ");
    }
}

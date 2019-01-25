package com.chen.wechat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 应用程序入口
 *
 * @Author LeifChen
 * @Date 2019-01-25
 */
@SpringBootApplication
@MapperScan(basePackages = "com.chen.wechat.mapper")
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}

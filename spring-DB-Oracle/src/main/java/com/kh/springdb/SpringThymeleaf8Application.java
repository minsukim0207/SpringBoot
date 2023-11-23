package com.kh.springdb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.kh.springdb.mapper")
public class SpringThymeleaf8Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringThymeleaf8Application.class, args);
	}

}

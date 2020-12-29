package com.blueCat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.blueCat.mapper")
public class BlueCatApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlueCatApplication.class, args);
	}

}

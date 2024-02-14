package com.sns.dropcat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;



@ServletComponentScan
@EnableTransactionManagement
@SpringBootApplication
public class DropcatApplication {

	public static void main(String[] args) {

		SpringApplication.run(DropcatApplication.class, args);
	}

}

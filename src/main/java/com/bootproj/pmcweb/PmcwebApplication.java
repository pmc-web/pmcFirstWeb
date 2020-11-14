package com.bootproj.pmcweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.sql.DataSource;

@EnableAspectJAutoProxy
@SpringBootApplication
public class PmcwebApplication {

	public static void main(String[] args) {
		SpringApplication.run(PmcwebApplication.class, args);
	}
}

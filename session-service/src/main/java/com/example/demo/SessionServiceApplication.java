package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;


 @SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
 //@SpringBootApplication
@EnableCassandraRepositories
public class SessionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SessionServiceApplication.class, args);
	}

}

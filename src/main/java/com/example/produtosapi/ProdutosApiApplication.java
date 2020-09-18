package com.example.produtosapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

import com.example.produtosapi.conf.ProjectProperty;

@SpringBootApplication
@EnableCaching
@EnableAsync
@EnableConfigurationProperties(ProjectProperty.class)
public class ProdutosApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProdutosApiApplication.class, args);
	}

}

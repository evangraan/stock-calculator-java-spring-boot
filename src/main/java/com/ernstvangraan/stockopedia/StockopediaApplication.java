package com.ernstvangraan.stockopedia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import com.ernstvangraan.stockopedia.aspects.configuration.DALConfiguration;

@SpringBootApplication
public class StockopediaApplication {
	public static void main(String[] args) {
		SpringApplication.run(StockopediaApplication.class, args);
	}
}

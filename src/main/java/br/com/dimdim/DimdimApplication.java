package br.com.dimdim;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class DimdimApplication {

	public static void main(String[] args) {
		SpringApplication.run(DimdimApplication.class, args);
	}

}

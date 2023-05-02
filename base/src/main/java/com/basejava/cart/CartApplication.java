package com.basejava.cart;


import com.basejava.cart.service.upload.FileStorageService;
import jakarta.annotation.Resource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class CartApplication implements CommandLineRunner {

	@Resource
	FileStorageService storageService;
	public static void main(String[] args) {

		SpringApplication.run(CartApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		storageService.init();
	}
}

package com.techu.apitechu;

import com.techu.apitechu.models.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class ApitechuApplication {
	public static ArrayList<Product> productList;

	public static void main(String[] args) {
		SpringApplication.run(ApitechuApplication.class, args);
		ApitechuApplication.productList = ApitechuApplication.getTestModels();
	}

	private static ArrayList<Product> getTestModels() {
		System.out.println("getTestModels");
		ArrayList<Product> result = new ArrayList<>();
		result.add(
				new Product(
						"0",
						"Producto 0",
						5
				)
		);
		result.add(
				new Product(
						"10",
						"Producto 10",
						10
				)
		);
		result.add(
				new Product(
						"20",
						"Producto 20",
						100
				)
		);
		return result;
	}

}
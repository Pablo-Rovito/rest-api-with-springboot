package com.techu.apitechu;

import com.techu.apitechu.models.ProductModel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class ApitechuApplication {
	public static ArrayList<ProductModel> productList;

	public static void main(String[] args) {
		SpringApplication.run(ApitechuApplication.class, args);
		ApitechuApplication.productList = ApitechuApplication.getTestModels();
	}

	private static ArrayList<ProductModel> getTestModels() {
		ArrayList<ProductModel> result = new ArrayList<>();
		result.add(
				new ProductModel(
						"0",
						"Producto 0",
						5f,
						null
				)
		);
		result.add(
				new ProductModel(
						"10",
						"Producto 10",
						10f,
						null
				)
		);
		result.add(
				new ProductModel(
						"20",
						"Producto 20",
						100f,
						null
				)
		);
		return result;
	}



}
package com.techu.apitechu;

import com.techu.apitechu.models.ProductModel;
import com.techu.apitechu.models.PurchaseModel;
import com.techu.apitechu.models.UserModel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

// TODO: implement interfaces!

@SpringBootApplication
public class ApitechuApplication {
	public static ArrayList<ProductModel> productList;
	public static ArrayList<UserModel> userList;
	public static ArrayList<PurchaseModel> purchaseList;
	public static final String API_BASE_URL = "/apitechu/v3";

	public static void main(String[] args) {
		SpringApplication.run(ApitechuApplication.class, args);
		ApitechuApplication.productList = ApitechuApplication.getProductList();
		ApitechuApplication.userList = ApitechuApplication.getUserModels();
		ApitechuApplication.purchaseList = ApitechuApplication.getPurchaseList();
	}

	private static ArrayList<ProductModel> getProductList() {
		ArrayList<ProductModel> products = new ArrayList<>();
		products.add(
				new ProductModel(
						"product-0",
						"papa",
						1f
				)
		);
		products.add(
				new ProductModel(
						"product-1",
						"cebolla",
						0.5f
				)
		);
		products.add(
				new ProductModel(
						"product-2",
						"aji",
						2f
				)
		);
		return products;
	}

	private static ArrayList<UserModel> getUserModels() {
		ArrayList<UserModel> users = new ArrayList<>();
		users.add(
				new UserModel("user-0", "Pablo", 34)
		);
		users.add(
				new UserModel("user-1", "Eliana", 35)
		);
		users.add(
				new UserModel("user-2", "Juan", 80)
		);
		users.add(
				new UserModel("user-3", "Eva", 72)
		);
		users.add(
				new UserModel("user-4", "José", 19)
		);
		users.add(
				new UserModel("user-5", "Juana", 23)
		);
		users.add(
				new UserModel("user-6", "Evelina", 72)
		);

		return users;
	}

	private static ArrayList<PurchaseModel> getPurchaseList() {
        return new ArrayList<>();
	}
}
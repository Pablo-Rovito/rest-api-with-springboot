package com.techu.apitechu;

import com.techu.apitechu.models.ProductModel;
import com.techu.apitechu.models.PurchaseModel;
import com.techu.apitechu.models.UserModel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.HashMap;

// TODO: implement interfaces!

@SpringBootApplication
public class ApitechuApplication {
	public static ArrayList<ProductModel> productList;
	public static ArrayList<UserModel> userList;
	public static ArrayList<PurchaseModel> purchaseList;
	public static HashMap<String, Float> priceList;
	public static final String API_BASE_URL = "/apitechu/v3";

	public static void main(String[] args) {
		SpringApplication.run(ApitechuApplication.class, args);
		ApitechuApplication.productList = ApitechuApplication.getTestModels();
		ApitechuApplication.userList = ApitechuApplication.getUserModels();
		ApitechuApplication.purchaseList = ApitechuApplication.getPurchaseList();
		ApitechuApplication.priceList = ApitechuApplication.getPricesList();
	}

	private static ArrayList<ProductModel> getTestModels() {
		ArrayList<ProductModel> products = new ArrayList<>();
		products.add(
				new ProductModel(
						"0",
						"Producto 0",
						5f
				)
		);
		products.add(
				new ProductModel(
						"10",
						"Producto 10",
						10f
				)
		);
		products.add(
				new ProductModel(
						"20",
						"Producto 20",
						100f
				)
		);
		return products;
	}

	private static ArrayList<UserModel> getUserModels() {
		ArrayList<UserModel> users = new ArrayList<>();
		users.add(
				new UserModel("0", "Pablo", 34)
		);
		users.add(
				new UserModel("10", "Eliana", 35)
		);
		users.add(
				new UserModel("20", "Juan", 80)
		);
		users.add(
				new UserModel("30", "Eva", 72)
		);
		users.add(
				new UserModel("40", "José", 19)
		);
		users.add(
				new UserModel("50", "Juana", 23)
		);
		users.add(
				new UserModel("60", "Evelina", 72)
		);

		return users;
	}

	private static ArrayList<PurchaseModel> getPurchaseList() {
        return new ArrayList<>();
	}

	private static HashMap<String, Float> getPricesList() {
		HashMap<String, Float> pricesList = new HashMap<>();
		pricesList.put("papa", 1f);
		pricesList.put("cebolla", 1.50f);
		pricesList.put("ají", 3f);
		pricesList.put("FerrariTestarossa", 150000f);
		return pricesList;
	}
}
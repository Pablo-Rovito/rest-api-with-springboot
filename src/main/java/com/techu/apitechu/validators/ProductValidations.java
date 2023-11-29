package com.techu.apitechu.validators;

import com.techu.apitechu.models.ProductModel;

public class ProductValidations {
    private static final String NUCLEAR = "nuclear";
    private static final Double MAX_PRICE = 1000d;
    public static boolean productIsOfNuclearOrigin(ProductModel product) {
        return product.getDescription().toLowerCase().contains(NUCLEAR);
    }

    public static boolean productIsTooExpensive(ProductModel product) {
        return product.getPrice() > MAX_PRICE;
    }
}

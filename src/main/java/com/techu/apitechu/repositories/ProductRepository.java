package com.techu.apitechu.repositories;

import com.techu.apitechu.ApitechuApplication;
import com.techu.apitechu.error.ProductException;
import com.techu.apitechu.models.ProductModel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {
    private final String NAME = "ProductRepository";

    public List<ProductModel> findAll() {
        System.out.printf("%n%s.findAll()", NAME);
        return ApitechuApplication.productList;
    }

    public ProductModel findById(String id) throws ProductException {
        System.out.printf("%n%s.findById(%s)", NAME, id);
        List<ProductModel> filteredList = ApitechuApplication.productList
                        .stream()
                        .filter(product -> id.equals(product.getId()))
                        .toList();

        if(filteredList.isEmpty()) throw new ProductException(NAME + "-findById()", String.format("Couldn´t find ID %s", id));

        return filteredList.get(0);
    }

    public Optional<ProductModel> createProduct(ProductModel productModel) {
        System.out.printf("%n%s.createProduct() with id = %s", NAME, productModel.getId());

        ApitechuApplication.productList.add(productModel);
        return Optional.of(productModel);
    }
}

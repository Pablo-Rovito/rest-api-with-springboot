package com.techu.apitechu.repositories;

import com.techu.apitechu.ApitechuApplication;
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

    public Optional<ProductModel> findById(String id) {
        System.out.printf("%n%s.findById(%s)", NAME, id);
        List<ProductModel> filteredList = ApitechuApplication.productList
                        .stream()
                        .filter(product -> id.equals(product.getId()))
                        .toList();

        return !filteredList.isEmpty() ? Optional.ofNullable(filteredList.get(0)) : Optional.empty();
    }

    public Optional<ProductModel> createProduct(ProductModel productModel) {
        System.out.printf("%n%s.createProduct() with id = %s", NAME, productModel.getId());

        ApitechuApplication.productList.add(productModel);
        return Optional.of(productModel);
    }
}

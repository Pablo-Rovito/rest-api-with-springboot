package com.techu.apitechu.services;

import com.techu.apitechu.error.ProductException;
import com.techu.apitechu.models.ProductModel;
import com.techu.apitechu.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final String NAME = "ProductService";
    @Autowired
    ProductRepository productRepository;

    public List<ProductModel> findAll() {
        System.out.printf("%n%s.findAll()", NAME);
        return productRepository.findAll();
    }

    public ProductModel findById(String id) throws ProductException {
        System.out.printf("%n%s.findById(%s)", NAME, id);
        return productRepository.findById(id);
    }

    public Optional<ProductModel> createProduct(ProductModel productModel) {
        System.out.printf("%n%s.createProduct() with id = %s", NAME, productModel.getId());
        return productRepository.createProduct(productModel);
    }
}

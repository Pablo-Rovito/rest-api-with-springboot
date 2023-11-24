package com.techu.apitechu.controllers;

import com.techu.apitechu.ApitechuApplication;
import com.techu.apitechu.models.Product;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {
    static final String API_BASE_URL = "/apitechu/v1";

    @GetMapping(API_BASE_URL + "/products")
    public ArrayList<Product> getProducts() {
        System.out.println("getProducts");
        return ApitechuApplication.productList;
    }

    @GetMapping(API_BASE_URL + "/products/{id}")
    public Product getProductById(@PathVariable String id) {
        System.out.println("getProductById");
        System.out.println(String.format("id: %s", id));
        List<Product> result = ApitechuApplication.productList.stream()
                .filter(model -> model.id.equals(id))
                .toList();
        if(result.isEmpty()) return new Product("not found", "not found", 0);
        return result.get(0);
    }

    @PostMapping(API_BASE_URL + "/products")
    public Product createProduct(
            @RequestBody Product product
    ){
        System.out.println("createProduct");
        System.out.println(String.format("Id del nuevo producto: %s", product.getId()));
        System.out.println(String.format("Descripción del nuevo producto: %s", product.getDescription()));
        System.out.println(String.format("Precio del nuevo producto: %s", product.getPrice()));
        ApitechuApplication.productList.add(product);
        return product;
    }
}

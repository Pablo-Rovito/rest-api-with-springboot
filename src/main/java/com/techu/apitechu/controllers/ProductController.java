package com.techu.apitechu.controllers;

import com.techu.apitechu.ApitechuApplication;
import com.techu.apitechu.models.ProductModel;
import com.techu.apitechu.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@RestController
public class ProductController {
    private final String NAME = "ProductController";
    @Autowired
    ProductService productService;
    static final String API_BASE_URL = "/apitechu/v2";

    @GetMapping(API_BASE_URL + "/products")
    public ResponseEntity<List<ProductModel>> getProducts() {
        System.out.printf("%n%s.getProducts()", NAME);
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping(API_BASE_URL + "/products/{id}")
    public ResponseEntity<ProductModel> getProductById(@PathVariable String id) {
        System.out.printf("%n%s.getProductById(%s)", NAME, id);

        Optional<ProductModel> response = productService.findById(id);

        return new ResponseEntity<>(
                response.orElse(new ProductModel("Error", "Not found", null)),
                response.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }

    // TODO: Throw error instead of returning ProductModel with error
    @PostMapping(API_BASE_URL + "/products")
    public ResponseEntity<ProductModel> createProduct(
            @RequestBody ProductModel productModel
    ){
        System.out.printf("%n%s.createProduct()", NAME);
        System.out.printf("  Id: %s  ", productModel.getId());
        System.out.printf("  Descripción: %s  ", productModel.getDescription());
        System.out.printf("  Precio: %s  ", productModel.getPrice());

        Optional<ProductModel> response = productService.createProduct(productModel);
        return new ResponseEntity<>(
                response.orElse(new ProductModel("Error", "not created", null)),
                response.isPresent() ? HttpStatus.CREATED : HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    // put if whole object is updated, patch if partial object is updated
    // Warning: if url = base + "/products", it would mean "update all products" in REST.
    // Although both work, url = base + "/products" is REST compliant.
    @PutMapping(API_BASE_URL + "/products/{id}")
    public ProductModel updateProduct(
            @RequestBody ProductModel productModel,
            @PathVariable String id
    ) {
        System.out.printf("%n%s.updateProduct(%s)", NAME, id);
        if(!id.equals(productModel.getId())) return new ProductModel("Error", "Ids don´t match", 0f);

        AtomicReference<Boolean> success = new AtomicReference<>(false);
        ApitechuApplication.productList.forEach(
                        productInList -> {
                            if (productInList.getId().equals(id)) {
                                productInList.setDescription(productModel.getDescription());
                                productInList.setPrice(productModel.getPrice());
                                success.set(true);
                            }
                        });
        if(!success.get()) return new ProductModel("Error", "product not found", 0f);
        return productModel;
    }

    // put if whole object is updated, patch if partial object is updated
    // Warning: if url = base + "/products", it would mean "update all products" in REST.
    // Although both work, url = base + "/products" is REST compliant.
    @PatchMapping(API_BASE_URL + "/products/{id}")
    public ProductModel patchProduct(
            @RequestBody ProductModel productModel,
            @PathVariable String id
    ) {
        System.out.println("patchProduct");

        System.out.printf("%nId del producto a actualizar es %s", id);
        AtomicReference<Boolean> success = new AtomicReference<>(false);
        ApitechuApplication.productList.forEach(
                productInList -> {
                    if (productInList.getId().equals(id)) {
                        if(productModel.getId() != null)
                            productInList.setId(productModel.getId());
                        if(productModel.getDescription() != null)
                            productInList.setDescription(productModel.getDescription());
                        if(productModel.getPrice() != null)
                            productInList.setPrice(productModel.getPrice());
                        success.set(true);
                    }
                });
        if(!success.get()) return new ProductModel("Error", "product not found", 0f);
        return productModel;
    }

    @DeleteMapping(API_BASE_URL + "/products/{id}")
    public ProductModel deleteProduct(
            @PathVariable String id
    ) {
        System.out.println("deleteProduct controller");

        ProductModel result = ApitechuApplication.productList
                .stream()
                .filter(productModel -> id.equals(productModel.getId()))
                .findAny()
                .orElse(new ProductModel(
                        "Error",
                        String.format("No product with id %s found", id), 0f)
                );

        ApitechuApplication.productList.removeIf(p -> id.equals(p.getId()));

        return result;
    }
}

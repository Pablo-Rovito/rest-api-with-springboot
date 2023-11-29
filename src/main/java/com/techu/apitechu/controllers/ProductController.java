package com.techu.apitechu.controllers;

import com.techu.apitechu.error.ProductException;
import com.techu.apitechu.models.ProductModel;
import com.techu.apitechu.services.ProductService;
import com.techu.apitechu.utils.ProductEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.List;

import static com.techu.apitechu.ApitechuApplication.API_BASE_URL;

@RestController
public class ProductController {
    private final String NAME = this.getClass().getSimpleName();
    @Autowired
    ProductService productService;

    @GetMapping(API_BASE_URL + "/products")
    public ResponseEntity<List<ProductModel>> getProducts() {
        final String METHOD_NAME = "getProducts";
        final String LOCATOR = NAME + " - " + METHOD_NAME;
        System.out.printf("%n%s", LOCATOR);
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping(API_BASE_URL + "/products/{id}")
    public ResponseEntity<ProductModel> getProductById(@PathVariable String id) {
        final String METHOD_NAME = "getProductById";
        final String LOCATOR = NAME + " - " + METHOD_NAME;
        System.out.printf("%n%s(%s)", LOCATOR, id);

            Optional<ProductModel> result = this.productService.findById(id);

            return result.isPresent()
                    ? ResponseEntity.ok(result.get())
                    : new ResponseEntity<>(
                            ProductWithException(ProductEnum.PRODUCT_NOT_FOUND.getMessage()),
                            ProductEnum.PRODUCT_NOT_FOUND.getStatusCode()
            );
    }

    @PostMapping(API_BASE_URL + "/products")
    public ResponseEntity<ProductModel> createProduct(
            @RequestBody ProductModel productModel
    ){
        final String METHOD_NAME = "createProduct";
        final String LOCATOR = NAME + " - " + METHOD_NAME;
        System.out.printf("%n%s", LOCATOR);
        System.out.printf("  Id: %s  ", productModel.getId());
        System.out.printf("  Descripción: %s  ", productModel.getDescription());
        System.out.printf("  Precio: %s  ", productModel.getPrice());

        try {
            return ResponseEntity.ok(this.productService.createProduct(productModel));
        } catch (ProductException exception) {
            return new ResponseEntity<>(
                    ProductWithException(exception.getMessage()),
                    exception.getStatusCode()
            );
        }
    }

    // put if whole object is updated, patch if partial object is updated
    // Warning: if url = base + "/products", it would mean "update all products" in REST.
    // Although both work, url = base + "/products" is REST compliant.
    @PutMapping(API_BASE_URL + "/products/{id}")
    public ResponseEntity<ProductModel> updateProduct(
            @RequestBody ProductModel productModel,
            @PathVariable String id
    ) {
        final String METHOD_NAME = "updateProduct";
        final String LOCATOR = NAME + " - " + METHOD_NAME;
        System.out.printf("%n%s(%s)", LOCATOR, id);

        try {
            return ResponseEntity.ok(productService.updateProduct(productModel));
        } catch (ProductException ex) {
            return new ResponseEntity<>(
                    ProductWithException(ex.getMessage()),
                    ex.getStatusCode()
            );
        }
    }

    // put if whole object is updated, patch if partial object is updated
    // Warning: if url = base + "/products", it would mean "update all products" in REST.
    // Although both work, url = base + "/products/{id}" is REST-compliant.
    @PatchMapping(API_BASE_URL + "/products/{id}")
    public ResponseEntity<ProductModel> patchProduct(
            @RequestBody ProductModel productModel,
            @PathVariable String id
    ) {
        final String METHOD_NAME = "patchProduct";
        final String LOCATOR = NAME + " - " + METHOD_NAME;
        System.out.printf("%n%s(%s)", LOCATOR, id);

        try {
            return ResponseEntity.ok(this.productService.patchProduct(productModel, id));
        } catch (ProductException exception) {
            return new ResponseEntity<>(
                    ProductWithException(exception.getMessage()),
                    exception.getStatusCode()
            );
        }
    }

    @DeleteMapping(API_BASE_URL + "/products/{id}")
    public ResponseEntity<ProductModel> deleteProduct(
            @PathVariable String id
    ) {
        final String METHOD_NAME = "deleteProduct";
        final String LOCATOR = NAME + " - " + METHOD_NAME;
        System.out.printf("%n%s(%s)", LOCATOR, id);

        try {
            return ResponseEntity.ok(this.productService.deleteProduct(id));
        } catch (ProductException exception) {
            return new ResponseEntity<>(
                    ProductWithException(exception.getMessage()),
                    exception.getStatusCode()
            );
        }
    }

    private ProductModel ProductWithException(String message) {
        return new ProductModel(message);
    }
}

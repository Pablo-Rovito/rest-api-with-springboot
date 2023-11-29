package com.techu.apitechu.services;

import com.techu.apitechu.error.ProductException;
import com.techu.apitechu.models.ProductModel;
import com.techu.apitechu.repositories.ProductRepository;
import com.techu.apitechu.utils.ProductEnum;
import com.techu.apitechu.validators.ProductValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final String NAME = this.getClass().getSimpleName();
    @Autowired
    ProductRepository productRepository;

    public List<ProductModel> findAll() {
        final String METHOD_NAME = "findAll";
        final String LOCATOR = NAME + " - " + METHOD_NAME;
        System.out.printf("%n%s", LOCATOR);

        return productRepository.findAll();
    }

    public Optional<ProductModel> findById(String id) {
        final String METHOD_NAME = "findById";
        final String LOCATOR = NAME + " - " + METHOD_NAME;
        System.out.printf("%n%s(%s)", LOCATOR, id);

        return this.productRepository.findById(id);
    }

    public ProductModel createProduct(ProductModel productModel) throws ProductException {
        final String METHOD_NAME = "createProduct";
        final String LOCATOR = NAME + " - " + METHOD_NAME;
        System.out.printf("%n%s with id = %s", LOCATOR, productModel.getId());

        Optional<ProductModel> productInList = this.findById(productModel.getId());

        if(productInList.isPresent()) {
            throw new ProductException(
                    LOCATOR,
                    ProductEnum.PRODUCT_ALREADY_IN_LIST.getMessage(),
                    ProductEnum.PRODUCT_ALREADY_IN_LIST.getStatusCode()
            );
        }

        boolean success = productRepository.createProduct(productModel);

        if(!success) {
            throw new ProductException(
                    LOCATOR,
                    ProductEnum.PRODUCT_NOT_CREATED.getMessage(),
                    ProductEnum.PRODUCT_NOT_CREATED.getStatusCode()
            );
        }

        return productModel;
    }

    public ProductModel updateProduct(ProductModel productModel, String id) throws ProductException {
        final String METHOD_NAME = "updateProduct";
        final String LOCATOR = NAME + " - " + METHOD_NAME;
        System.out.printf("%n%s with id = %s", LOCATOR, id);

        Optional<ProductModel> productInList = this.findById(id);

        if(productInList.isEmpty()) {
            throw new ProductException(
                    LOCATOR,
                    ProductEnum.PRODUCT_NOT_FOUND.getMessage(),
                    ProductEnum.PRODUCT_NOT_FOUND.getStatusCode()
            );
        }

        productRepository.updateProduct(productModel, id);

        // I need to make sure the change has been successful
        if(!productModel.equals(this.findById(id).get())) {
            throw new ProductException(
                    LOCATOR,
                    ProductEnum.PRODUCT_NOT_UPDATED.getMessage(),
                    ProductEnum.PRODUCT_NOT_UPDATED.getStatusCode()
            );
        }

        return productModel;
    }

    public ProductModel patchProduct(ProductModel productModel, String id) throws ProductException {
        final String METHOD_NAME = "patchProduct";
        final String LOCATOR = NAME + " - " + METHOD_NAME;
        System.out.printf("%n%s(%s)", LOCATOR, productModel.getId());

        Optional<ProductModel> productInList = this.findById(id);

        if(productInList.isEmpty()) {
            throw new ProductException(
                    LOCATOR,
                    ProductEnum.PRODUCT_NOT_FOUND.getMessage(),
                    ProductEnum.PRODUCT_NOT_FOUND.getStatusCode()
            );
        }

        ProductModel updatedProduct = this.productRepository.patchProduct(productModel, id);

        // I need to make sure the change has been successful
        if(!productModel.equals(updatedProduct)) {
            throw new ProductException(
                    LOCATOR,
                    ProductEnum.PRODUCT_NOT_UPDATED.getMessage(),
                    ProductEnum.PRODUCT_NOT_UPDATED.getStatusCode()
            );
        }

        return updatedProduct;
    }

    public ProductModel deleteProduct(String id) throws ProductException {
        final String METHOD_NAME = "deleteProduct";
        final String LOCATOR = NAME + " - " + METHOD_NAME;
        System.out.printf("%n%s(%s)", LOCATOR, id);

        Optional<ProductModel> result = this.findById(id);

        if(result.isEmpty()) {
            throw new ProductException(
                    LOCATOR,
                    ProductEnum.PRODUCT_NOT_FOUND.getMessage(),
                    ProductEnum.PRODUCT_NOT_FOUND.getStatusCode()
            );
        }

        if(ProductValidations.productIsOfNuclearOrigin(result.get())) {
            throw new ProductException(
                    LOCATOR,
                    ProductEnum.DELETE_NUCLEAR_PRODUCT.getMessage(),
                    ProductEnum.DELETE_NUCLEAR_PRODUCT.getStatusCode()
            );
        }

        if(ProductValidations.productIsTooExpensive(result.get())) {
            throw new ProductException(
                    LOCATOR,
                    ProductEnum.DELETE_PRODUCT_PRICE_TOO_HIGH.getMessage(),
                    ProductEnum.DELETE_PRODUCT_PRICE_TOO_HIGH.getStatusCode()
            );
        }

        if(!this.productRepository.deleteProduct(id)) {
            throw new ProductException(
                    LOCATOR,
                    ProductEnum.PRODUCT_NOT_DELETED.getMessage(),
                    ProductEnum.PRODUCT_NOT_DELETED.getStatusCode()
            );
        }

        return result.get();
    }
}

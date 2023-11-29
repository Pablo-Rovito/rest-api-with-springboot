package com.techu.apitechu.repositories;

import com.techu.apitechu.ApitechuApplication;
import com.techu.apitechu.models.ProductModel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {
    private final String NAME = this.getClass().getSimpleName();

    public List<ProductModel> findAll() {
        final String METHOD_NAME = "findAll";
        final String LOCATOR = NAME + " - " + METHOD_NAME;
        System.out.printf("%n%s", LOCATOR);

        return ApitechuApplication.productList;
    }

    public Optional<ProductModel> findById(String id) {
        final String METHOD_NAME = "findById";
        final String LOCATOR = NAME + " - " + METHOD_NAME;
        System.out.printf("%n%s(%s)", LOCATOR, id);

        return ApitechuApplication.productList
                .stream()
                .filter(p -> id.equals(p.getId()))
                .findAny();
    }

    public boolean createProduct(ProductModel productModel) {
        final String METHOD_NAME = "createProduct";
        final String LOCATOR = NAME + " - " + METHOD_NAME;
        System.out.printf("%n%s with id = %s", LOCATOR, productModel.getId());

        return ApitechuApplication.productList.add(productModel);
    }

    // TODO: Está roto updateProduct desde acá. Arreglar!!
    public void updateProduct(ProductModel productModel, String id) {
        final String METHOD_NAME = "updateProduct";
        final String LOCATOR = NAME + " - " + METHOD_NAME;
        System.out.printf("%n%s with id = %s", LOCATOR, id);

        int productIndex = ApitechuApplication.productList.indexOf(this.findById(id).get());
        ApitechuApplication.productList.set(productIndex, productModel);
    }

    public ProductModel patchProduct(ProductModel productModel, String id) {
        final String METHOD_NAME = "patchProduct";
        final String LOCATOR = NAME + " - " + METHOD_NAME;
        System.out.printf("%n%s with id = %s", LOCATOR, id);

        ApitechuApplication.productList.forEach(
                productInList -> {
                    if (id.equals(productInList.getId())) {
                        if(productModel.getDescription() != null) {
                            productInList.setDescription(productModel.getDescription());
                        }
                        if(productModel.getPrice() != null) {
                            productInList.setPrice(productModel.getPrice());
                        }
                    }
                });
        return productModel;
    }

    public boolean deleteProduct(String id) {
        final String METHOD_NAME = "deleteProduct";
        final String LOCATOR = NAME + " - " + METHOD_NAME;
        System.out.printf("%n%s(%s)", LOCATOR, id);

        // Product existence is checked in service
        return ApitechuApplication.productList.removeIf(p -> id.equals(p.getId()));
    }
}

package com.techu.apitechu.validators;

import com.techu.apitechu.ApitechuApplication;
import com.techu.apitechu.models.ProductModel;
import com.techu.apitechu.services.ProductService;
import com.techu.apitechu.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class PurchaseValidations {
    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;
    public boolean isRegisteredUser(String userId) {
        return userService.getUsers(null, userId).size() == 1;
    }

    public boolean purchaseIdAlreadyGenerated(String purchaseId) {
        return ApitechuApplication.purchaseList.stream().anyMatch(u -> purchaseId.equals(u.getId()));
    }

    public boolean allItemsInProductList(Set<String> itemList) {

        List<String> productIdList = productService.findAll().stream()
                .map(ProductModel::getId)
                .toList();

        return productIdList.containsAll(itemList);
    }
}

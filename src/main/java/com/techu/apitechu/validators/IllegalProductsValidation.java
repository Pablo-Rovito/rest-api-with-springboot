package com.techu.apitechu.validators;

import com.techu.apitechu.models.PaymentModel;
import com.techu.apitechu.models.ProductModel;
import com.techu.apitechu.models.PurchaseModel;
import com.techu.apitechu.utils.PurchaseEnum;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;

@Component
public class IllegalProductsValidation extends AbstractPurchaseValidations {
    private final String NAME = this.getClass().getSimpleName();

    @Override
    public PurchaseModel apply(PaymentModel payment, PurchaseModel purchase) {
        final String LOCATOR = NAME + " - apply()";
        System.out.printf("%n%s", LOCATOR);

        if(purchase.getErrorMessage() != null) {
            return purchase;
        }

        List<String> productIdList = productService.findAll()
                .stream()
                .map(ProductModel::getId)
                .toList();

        if(!new HashSet<>(productIdList).containsAll(payment.getPurchaseItems().keySet())) {
            purchase.setErrorMessage(PurchaseEnum.ILLEGAL_PRODUCTS.getMessage());
            purchase.setHttpStatus(PurchaseEnum.ILLEGAL_PRODUCTS.getStatusCode());
        }

        return purchase;
    }
}

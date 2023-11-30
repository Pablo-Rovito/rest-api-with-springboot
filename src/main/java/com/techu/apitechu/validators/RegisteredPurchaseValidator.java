package com.techu.apitechu.validators;

import com.techu.apitechu.ApitechuApplication;
import com.techu.apitechu.models.PaymentModel;
import com.techu.apitechu.models.PurchaseModel;
import com.techu.apitechu.utils.PurchaseEnum;
import org.springframework.stereotype.Component;

@Component
public class RegisteredPurchaseValidator extends AbstractPurchaseValidations {
    private final String NAME = this.getClass().getSimpleName();

    @Override
    public PurchaseModel apply(PaymentModel payment, PurchaseModel purchase) {
        final String LOCATOR = NAME + " - apply()";
        System.out.printf("%n%s", LOCATOR);

        if(purchase.getErrorMessage() != null) {
            return purchase;
        }

        if(ApitechuApplication.purchaseList.stream().anyMatch(u -> purchase.getId().equals(u.getId()))) {
            purchase.setErrorMessage(PurchaseEnum.PURCHASE_ALREADY_REGISTERED.getMessage());
            purchase.setHttpStatus(PurchaseEnum.PURCHASE_ALREADY_REGISTERED.getStatusCode());
        }

        return purchase;
    }
}

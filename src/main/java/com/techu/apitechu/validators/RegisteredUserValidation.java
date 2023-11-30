package com.techu.apitechu.validators;

import com.techu.apitechu.models.PaymentModel;
import com.techu.apitechu.models.PurchaseModel;
import com.techu.apitechu.utils.PurchaseEnum;
import org.springframework.stereotype.Component;

@Component
public class RegisteredUserValidation extends AbstractPurchaseValidations {
    private final String NAME = this.getClass().getSimpleName();
    @Override
    public PurchaseModel apply(PaymentModel payment, PurchaseModel purchase) {
        final String LOCATOR = NAME + " - apply()";
        System.out.printf("%n%s", LOCATOR);

        if(purchase.getErrorMessage() != null) {
            return purchase;
        }

        if(userService.getUsers(null, payment.getUserId()).size() != 1) {
            purchase.setErrorMessage(PurchaseEnum.USER_NOT_REGISTERED.getMessage());
            purchase.setHttpStatus(PurchaseEnum.USER_NOT_REGISTERED.getStatusCode());
        } else {
            purchase.setUserId(payment.getUserId());
        }

        return purchase;
    }
}

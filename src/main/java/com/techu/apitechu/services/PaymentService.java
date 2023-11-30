package com.techu.apitechu.services;

import com.techu.apitechu.models.PaymentModel;
import com.techu.apitechu.models.PurchaseModel;
import com.techu.apitechu.validators.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private final String NAME = this.getClass().getSimpleName();
    @Autowired
    ValidatePayment validatePayment;

    public PurchaseModel addPayment(PaymentModel payment) {
        final String METHOD_NAME = "addPayment";
        final String LOCATOR = NAME + " - " + METHOD_NAME;
        System.out.printf("%n%s", LOCATOR);

        PurchaseModel purchase = validatePayment.validate(payment);

        if(purchase.getErrorMessage() == null) {
            purchase.getPayments().add(payment);
        }

        return purchase;
    }
}

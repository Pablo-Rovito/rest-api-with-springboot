package com.techu.apitechu.validators;

import com.techu.apitechu.models.PaymentModel;
import com.techu.apitechu.models.PurchaseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ValidatePayment {
    private final String NAME = this.getClass().getSimpleName();
    @Autowired
    PaymentAmountValidation paymentAmountValidation;
    @Autowired
    PaymentExistenceValidation paymentExistenceValidation;
    @Autowired
    PaymentDateValidation paymentDateValidation;

    public PurchaseModel validate(PaymentModel payment) {
        final String METHOD_NAME = "validate";
        final String LOCATOR = NAME + " - " + METHOD_NAME;
        System.out.printf("%n%s", LOCATOR);

        ArrayList<AbstractPaymentValidation> validations = new ArrayList<>();
        validations.add(paymentExistenceValidation);
        validations.add(paymentDateValidation);
        validations.add(paymentAmountValidation);

        PurchaseModel purchase = new PurchaseModel();

        for (AbstractPaymentValidation validation : validations) {
            purchase = validation.apply(payment);
        }

        return purchase;
    }
}

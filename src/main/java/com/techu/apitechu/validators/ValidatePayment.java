package com.techu.apitechu.validators;

import com.techu.apitechu.models.PaymentModel;
import com.techu.apitechu.models.ValidationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValidatePayment {
    private final String NAME = this.getClass().getSimpleName();
    /*
    This Autowired should add automatically every AbstractPaymentValidation into the list.
    Really like the analyzers from Visita técnica.
    * */
    @Autowired
    private List<AbstractPaymentValidation> validations;

    public ValidationResponse validate(PaymentModel payment) {
        final String METHOD_NAME = "validate";
        final String LOCATOR = NAME + " - " + METHOD_NAME;
        System.out.printf("%n%s", LOCATOR);

        ValidationResponse response = new ValidationResponse();

        for (AbstractPaymentValidation validation : validations) {
            response = validation.apply(payment);
            if(!response.isSuccess()) {
                return response;
            }
        }

        return response;
    }
}

package com.techu.apitechu.validators;

import com.techu.apitechu.models.PaymentModel;
import com.techu.apitechu.models.ValidationResponse;
import org.springframework.cglib.core.internal.Function;

public abstract class AbstractPaymentValidation implements Function<PaymentModel, ValidationResponse> {
    public abstract ValidationResponse apply(PaymentModel payment);
}

package com.techu.apitechu.validators;

import com.techu.apitechu.models.PaymentModel;
import com.techu.apitechu.models.PurchaseModel;
import org.springframework.cglib.core.internal.Function;

public abstract class AbstractPaymentValidation implements Function<PaymentModel, PurchaseModel> {
    public abstract PurchaseModel apply(PaymentModel payment);
}

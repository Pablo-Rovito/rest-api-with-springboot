package com.techu.apitechu.validators;

import com.techu.apitechu.models.PaymentModel;
import com.techu.apitechu.models.PurchaseModel;
import com.techu.apitechu.models.ValidationResponse;
import com.techu.apitechu.services.PurchaseService;
import com.techu.apitechu.utils.PurchaseEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
public class PaymentDateValidation extends AbstractPaymentValidation {
    private final String NAME = this.getClass().getSimpleName();

    @Autowired
    PurchaseService purchaseService;

    @Override
    public ValidationResponse apply(PaymentModel payment) {
        final String LOCATOR = NAME + " - apply()";
        System.out.printf("%n%s", LOCATOR);

        ValidationResponse response = new ValidationResponse();

        PurchaseModel purchase = this.purchaseService.getPurchases()
                .stream()
                .filter(p -> payment.getPurchaseId().equals(p.getId()))
                .toList()
                .get(0);

        if(Calendar.getInstance().getTime().after(purchase.getLastPurchaseDate())) {
            response.setSuccess(false);
            response.addMessage(PurchaseEnum.PAYMENT_TOO_LATE.getMessage());
            response.setPayload(new PurchaseModel());
        }

        return response;
    }
}

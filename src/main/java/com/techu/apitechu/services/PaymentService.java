package com.techu.apitechu.services;

import com.techu.apitechu.models.PaymentModel;
import com.techu.apitechu.models.PurchaseModel;
import com.techu.apitechu.models.ValidationResponse;
import com.techu.apitechu.utils.PurchaseStatuses;
import com.techu.apitechu.validators.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private final String NAME = this.getClass().getSimpleName();
    @Autowired
    ValidatePayment validatePayment;
    @Autowired
    PurchaseService purchaseService;

    public ValidationResponse addPayment(PaymentModel payment) {
        final String METHOD_NAME = "addPayment";
        final String LOCATOR = NAME + " - " + METHOD_NAME;
        System.out.printf("%n%s", LOCATOR);

        ValidationResponse response = validatePayment.validate(payment);

        if(response.isSuccess()) {
            PurchaseModel purchase = this.purchaseService.getPurchases().stream()
                    .filter(p -> payment.getPurchaseId().equals(p.getId()))
                    .filter(p -> (p.getStatus() == PurchaseStatuses.PENDING))
                    .toList()
                    .get(0);
            purchase.getPayments().add(payment);
            response.setPayload(purchase);
        }

        return response;
    }
}

package com.techu.apitechu.validators;

import com.techu.apitechu.models.PaymentModel;
import com.techu.apitechu.models.PurchaseModel;
import com.techu.apitechu.models.ValidationResponse;
import com.techu.apitechu.services.PurchaseService;
import com.techu.apitechu.utils.PurchaseEnum;
import com.techu.apitechu.utils.PurchaseStatuses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PaymentExistenceValidation extends AbstractPaymentValidation {
    private final String NAME = this.getClass().getSimpleName();

    @Autowired
    PurchaseService purchaseService;

    @Override
    public ValidationResponse apply(PaymentModel payment) {
        final String LOCATOR = NAME + " - apply()";
        System.out.printf("%n%s", LOCATOR);

        ValidationResponse response = new ValidationResponse();

        List<PurchaseModel> purchaseList = this.purchaseService.getPurchases().stream()
                .filter(p -> payment.getPurchaseId().equals(p.getId()))
                .filter(p -> (p.getStatus() == PurchaseStatuses.PENDING))
                .toList();

        if(purchaseList.isEmpty()) {
            response.setSuccess(false);
            response.addMessage(PurchaseEnum.PURCHASE_NOT_FOUND.getMessage());
            response.setPayload(new PurchaseModel());
        }

        return response;
    }
}

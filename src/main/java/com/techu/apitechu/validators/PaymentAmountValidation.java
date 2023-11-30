package com.techu.apitechu.validators;

import com.techu.apitechu.models.PaymentModel;
import com.techu.apitechu.models.PurchaseModel;
import com.techu.apitechu.models.ValidationResponse;
import com.techu.apitechu.services.PurchaseService;
import com.techu.apitechu.utils.PurchaseEnum;
import com.techu.apitechu.utils.PurchaseStatuses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.List;

@Component
public class PaymentAmountValidation extends AbstractPaymentValidation {
    private final String NAME = this.getClass().getSimpleName();

    @Autowired
    PurchaseService purchaseService;

    @Override
    public ValidationResponse apply(PaymentModel payment) {
        final String LOCATOR = NAME + " - apply()";
        System.out.printf("%n%s", LOCATOR);

        ValidationResponse response = new ValidationResponse();

        PurchaseModel purchase = this.purchaseService.getPurchases().stream()
                .filter(p -> payment.getPurchaseId().equals(p.getId()))
                .filter(p -> (p.getStatus() == PurchaseStatuses.PENDING))
                .toList()
                .get(0);

        Float amountDue = purchase.getAmount() -
                purchase.getPayments().stream()
                    .map(PaymentModel::getAmount)
                    .reduce(0f, Float::sum);

        if(amountDue - payment.getAmount() < 0f) {
            response.setSuccess(false);
            response.addMessage(PurchaseEnum.PAYMENT_TOO_BIG.getMessage() + amountDue);
        }
/*        if(Float.valueOf(0f).equals(amountDue - payment.getAmount())) {
            response.setPaymentDate(Calendar.getInstance().getTime());
            response.setStatus(PurchaseStatuses.COMPLETED);
        }*/

        return response;
    }
}

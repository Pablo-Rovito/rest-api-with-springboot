package com.techu.apitechu.controllers;

import com.techu.apitechu.models.PaymentModel;
import com.techu.apitechu.models.PurchaseModel;
import com.techu.apitechu.models.ValidationResponse;
import com.techu.apitechu.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.techu.apitechu.ApitechuApplication.API_BASE_URL;

@RestController
public class PaymentController {
    private final String NAME = this.getClass().getSimpleName();
    @Autowired
    PaymentService paymentService;
    @PostMapping(API_BASE_URL + "/payments")
    public ResponseEntity<PurchaseModel> addPayment(
            @RequestBody PaymentModel payment
    ) {
        final String METHOD_NAME = "addPayment";
        final String LOCATOR = NAME + " - " + METHOD_NAME;
        System.out.printf("%n%s", LOCATOR);

        ValidationResponse response = this.paymentService.addPayment(payment);

        return new ResponseEntity<>(
                response.isSuccess() ? response.getPayload() : new PurchaseModel(response.getMessages().get(0)),
                response.getHttpStatus()
        );
    }
}

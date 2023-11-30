package com.techu.apitechu.controllers;

import com.techu.apitechu.models.PaymentModel;
import com.techu.apitechu.models.PurchaseModel;
import com.techu.apitechu.services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.techu.apitechu.ApitechuApplication.API_BASE_URL;

@RestController
public class PurchaseController {
    private final String NAME = this.getClass().getSimpleName();
    @Autowired
    PurchaseService purchaseService;

    @PostMapping(API_BASE_URL + "/purchase")
    public ResponseEntity<PurchaseModel> createPurchase(
            @RequestBody PaymentModel purchase
    ) {
        final String METHOD_NAME = "createPurchase";
        final String LOCATOR = NAME + " - " + METHOD_NAME;
        System.out.printf("%n%s", LOCATOR);
        System.out.printf("  UserId: %s  ", purchase.getUserId());
        System.out.printf("  Items: %s  ", purchase.getPurchaseItems());

        PurchaseModel response = this.purchaseService.createPurchase(purchase);

        if(response.getErrorMessage() != null) {
            return new ResponseEntity<>(
                    response,
                    response.getHttpStatus()
            );
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping(API_BASE_URL + "/purchase")
    public ResponseEntity<List<PurchaseModel>> getPurchases() {
        final String METHOD_NAME = "getPurchases";
        final String LOCATOR = NAME + " - " + METHOD_NAME;
        System.out.printf("%n%s", LOCATOR);

        return ResponseEntity.ok(this.purchaseService.getPurchases());
    }
}

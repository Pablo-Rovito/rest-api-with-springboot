package com.techu.apitechu.controllers;

import com.techu.apitechu.models.PurchaseModel;
import com.techu.apitechu.services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.techu.apitechu.ApitechuApplication.API_BASE_URL;


@RestController
public class PurchaseController {
    private final String NAME = this.getClass().getSimpleName();
    @Autowired
    PurchaseService purchaseService;

    @PostMapping(API_BASE_URL + "/purchase")
    public ResponseEntity<PurchaseModel> createPurchase(
            @RequestBody PurchaseModel purchaseModel
    ) {
        final String METHOD_NAME = "createPurchase";
        final String LOCATOR = NAME + " - " + METHOD_NAME;
        System.out.printf("%n%s", LOCATOR);
        System.out.printf("  Id: %s  ", purchaseModel.getId());
        System.out.printf("  UserId: %s  ", purchaseModel.getUserId());
        System.out.printf("  Amount: %s  ", purchaseModel.getAmount());
        System.out.printf("  Items: %s  ", purchaseModel.getPurchaseItems());

        return ResponseEntity.ok(this.purchaseService.createPurchase(purchaseModel));
    }
}

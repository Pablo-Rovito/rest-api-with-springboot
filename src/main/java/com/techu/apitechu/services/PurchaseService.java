package com.techu.apitechu.services;

import com.techu.apitechu.models.PurchaseModel;
import com.techu.apitechu.repositories.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {
    private final String NAME = this.getClass().getSimpleName();

    @Autowired
    PurchaseRepository purchaseRepository;

    public PurchaseModel createPurchase(PurchaseModel purchaseModel) {
        final String METHOD_NAME = "createPurchase";
        final String LOCATOR = NAME + " - " + METHOD_NAME;
        System.out.printf("%n%s", LOCATOR);

        return this.purchaseRepository.createPurchase(purchaseModel);
    }
}

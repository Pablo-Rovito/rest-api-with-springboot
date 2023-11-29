package com.techu.apitechu.services;

import com.techu.apitechu.ApitechuApplication;
import com.techu.apitechu.models.PurchaseModel;
import com.techu.apitechu.models.PurchaseRequest;
import com.techu.apitechu.repositories.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class PurchaseService {
    private final String NAME = this.getClass().getSimpleName();

    @Autowired
    PurchaseRepository purchaseRepository;

    public PurchaseModel createPurchase(PurchaseRequest purchaseRequest) {
        final String METHOD_NAME = "createPurchase";
        final String LOCATOR = NAME + " - " + METHOD_NAME;
        System.out.printf("%n%s", LOCATOR);

        PurchaseModel purchaseModel = new PurchaseModel(purchaseRequest.getUserId(), calculateTotal(purchaseRequest.getPurchaseItems()), purchaseRequest.getPurchaseItems());


        return this.purchaseRepository.createPurchase(purchaseModel);
    }

    private Float calculateTotal(HashMap<String, Float> items) {
        Set<String> itemList = items.keySet();
        ArrayList<Float> subTotals = new ArrayList<>();

        itemList.forEach(item -> {
            subTotals.add(ApitechuApplication.priceList.get(item) * items.get(item));
        });

        return subTotals.stream().reduce(0f,Float::sum);
    }
}

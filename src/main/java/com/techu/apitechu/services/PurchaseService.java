package com.techu.apitechu.services;

import com.techu.apitechu.models.PurchaseModel;
import com.techu.apitechu.models.PurchaseRequest;
import com.techu.apitechu.repositories.PurchaseRepository;
import com.techu.apitechu.utils.PurchaseEnum;
import com.techu.apitechu.validators.PurchaseValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Service
public class PurchaseService {
    private final String NAME = this.getClass().getSimpleName();
    @Autowired
    PurchaseValidations purchaseValidations;
    @Autowired
    PurchaseRepository purchaseRepository;
    @Autowired
    ProductService productService;

    public PurchaseModel createPurchase(PurchaseRequest purchaseRequest) {
        final String METHOD_NAME = "createPurchase";
        final String LOCATOR = NAME + " - " + METHOD_NAME;
        System.out.printf("%n%s", LOCATOR);

        if(!purchaseValidations.isRegisteredUser(purchaseRequest.getUserId())) {
            return new PurchaseModel(
                    PurchaseEnum.USER_NOT_REGISTERED.getMessage(),
                    PurchaseEnum.USER_NOT_REGISTERED.getStatusCode()
            );
        }

        if(!purchaseValidations.allItemsInProductList(
                purchaseRequest.getPurchaseItems().keySet())
        ) {
            return new PurchaseModel(
                    PurchaseEnum.ILLEGAL_PRODUCTS.getMessage(),
                    PurchaseEnum.ILLEGAL_PRODUCTS.getStatusCode()
            );
        }

        PurchaseModel newPurchase = new PurchaseModel(
                    purchaseRequest.getUserId(),
                    calculateTotal(purchaseRequest.getPurchaseItems()),
                    purchaseRequest.getPurchaseItems()
        );

        // this one doesn´t actually validate anything bc IDs are automatically generated.

        if(purchaseValidations.purchaseIdAlreadyGenerated(newPurchase.getId())) {
            return new PurchaseModel(
                    PurchaseEnum.PURCHASE_ALREADY_REGISTERED.getMessage(),
                    PurchaseEnum.PURCHASE_ALREADY_REGISTERED.getStatusCode()
            );
        }

        return this.purchaseRepository.createPurchase(newPurchase);
    }



    public List<PurchaseModel> getPurchases() {
        final String METHOD_NAME = "getPurchases";
        final String LOCATOR = NAME + " - " + METHOD_NAME;
        System.out.printf("%n%s", LOCATOR);

        return this.purchaseRepository.getPurchases();
    }

    private Float calculateTotal(HashMap<String, Float> items) {

        // I need a Map that links productIds with their price.
        HashMap<String, Float> priceList = new HashMap<>();
        productService.findAll().forEach(p -> {
            priceList.put(p.getId(), p.getPrice());
        });

        // I need a way to store the partial values for number * price
        ArrayList<Float> subTotals = new ArrayList<>();

        Set<String> itemList = items.keySet();
        itemList.forEach(item -> {
            subTotals.add(priceList.get(item) * items.get(item));
        });

        return subTotals.stream().reduce(0f,Float::sum);
    }
}
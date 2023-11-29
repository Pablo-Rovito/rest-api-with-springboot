package com.techu.apitechu.repositories;

import com.techu.apitechu.ApitechuApplication;
import com.techu.apitechu.models.PurchaseModel;
import com.techu.apitechu.utils.PurchaseEnum;
import org.springframework.stereotype.Repository;

@Repository
public class PurchaseRepository {
    private final String NAME = this.getClass().getSimpleName();

    public PurchaseModel createPurchase(PurchaseModel purchaseModel) {
        final String METHOD_NAME = "createPurchase";
        final String LOCATOR = NAME + " - " + METHOD_NAME;
        System.out.printf("%n%s", LOCATOR);
        System.out.printf("  Id: %s  ", purchaseModel.getId());
        System.out.printf("  UserId: %s  ", purchaseModel.getUserId());
        System.out.printf("  Amount: %s  ", purchaseModel.getAmount());
        System.out.printf("  Items: %s  ", purchaseModel.getPurchaseItems());

        if(ApitechuApplication.purchaseList.add(purchaseModel)) {
            return purchaseModel;
        } else {
            return new PurchaseModel(PurchaseEnum.PURCHASE_NOT_CREATED.getMessage());
        }
    }
}

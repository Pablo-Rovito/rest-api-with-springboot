package com.techu.apitechu.services;

import com.techu.apitechu.models.PaymentModel;
import com.techu.apitechu.models.PurchaseModel;
import com.techu.apitechu.repositories.PurchaseRepository;
import com.techu.apitechu.utils.PurchaseStatuses;
import com.techu.apitechu.validators.IllegalProductsValidation;
import com.techu.apitechu.validators.RegisteredPurchaseValidator;
import com.techu.apitechu.validators.RegisteredUserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PurchaseService {
    private final String NAME = this.getClass().getSimpleName();
    @Autowired
    PurchaseRepository purchaseRepository;
    @Autowired
    ProductService productService;
    @Autowired
    RegisteredUserValidation registeredUserValidation;
    @Autowired
    IllegalProductsValidation illegalProductsValidation;
    @Autowired
    RegisteredPurchaseValidator registeredPurchaseValidator;

    public PurchaseModel createPurchase(PaymentModel payment) {
        final String METHOD_NAME = "createPurchase";
        final String LOCATOR = NAME + " - " + METHOD_NAME;
        System.out.printf("%n%s", LOCATOR);

        // TODO: turn this into a list of AbstractPurchaseValidations
        PurchaseModel newPurchase = registeredUserValidation.apply(payment, new PurchaseModel());
         newPurchase = illegalProductsValidation.apply(payment, newPurchase);
         newPurchase = registeredPurchaseValidator.apply(payment, newPurchase);

        if(newPurchase.getErrorMessage() != null) {
            return newPurchase;
        }

        return this.purchaseRepository.createPurchase(setPurchase(newPurchase, payment));
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

    private PurchaseModel setPurchase(PurchaseModel purchase, PaymentModel payment) {

        Float purchaseAmount = calculateTotal(payment.getPurchaseItems());
        boolean paid = purchaseAmount <= payment.getAmount();
        Calendar now = Calendar.getInstance();
        Calendar dueDate = Calendar.getInstance();
        dueDate.add(Calendar.DAY_OF_YEAR, purchase.getDaysPaymentDue());
        ArrayList<PaymentModel> payments = new ArrayList<>();
        payments.add(new PaymentModel(now.getTime(), payment.getAmount()));

        purchase.setPurchaseItems(payment.getPurchaseItems());
        purchase.setAmount(purchaseAmount);
        purchase.setPurchaseDate(now.getTime());
        purchase.setPaymentDate(paid ? now.getTime() : null);
        purchase.setPayments(payments);
        purchase.setLastPurchaseDate(paid ? null : dueDate.getTime());
        purchase.setStatus(paid ? PurchaseStatuses.COMPLETED : PurchaseStatuses.PENDING);

        return purchase;
    }
}
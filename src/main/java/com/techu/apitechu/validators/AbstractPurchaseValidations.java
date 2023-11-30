package com.techu.apitechu.validators;

import com.techu.apitechu.models.PaymentModel;
import com.techu.apitechu.models.PurchaseModel;
import com.techu.apitechu.services.ProductService;
import com.techu.apitechu.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

// TODO: implementar Function<entrada, salida> en esta clase abstracta para que sea más portable y chequee tipos
public abstract class AbstractPurchaseValidations {
    // TODO: protected boolean usar isValid para chequear si pasa o no el validador.
    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;
    public abstract PurchaseModel apply(PaymentModel payment, PurchaseModel purchase);
}

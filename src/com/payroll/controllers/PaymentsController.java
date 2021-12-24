package com.payroll.controllers;

import com.payroll.DB;
import com.payroll.models.payment.method.PaymentMethod;

import java.util.List;

public class PaymentsController {

    private final DB db;

    public PaymentsController() {
        this.db = DB.getInstance();
    }

    public List<PaymentMethod> getPaymentMethods() {
        return this.db.paymentMethods.values().stream().toList();
    }

}

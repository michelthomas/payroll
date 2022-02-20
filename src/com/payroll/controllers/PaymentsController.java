package com.payroll.controllers;

import com.payroll.DB;
import com.payroll.models.payment.method.PaymentMethod;
import com.payroll.services.PaymentService;

import java.time.LocalDate;
import java.util.List;

public class PaymentsController {

    private final DB db;
    private final PaymentService paymentService = new PaymentService();

    public PaymentsController() {
        this.db = DB.getInstance();
    }

    public List<PaymentMethod> getPaymentMethods() {
        return this.db.paymentMethods.values().stream().toList();
    }

    public void runPayroll(int day) {
        paymentService.payEmployeesByDay(day);
    }

}

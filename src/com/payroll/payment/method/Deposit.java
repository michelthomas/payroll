package com.payroll.payment.method;

public class Deposit implements PaymentMethod {

    @Override
    public String getDescription() {
        return "Depósito em conta bancária.";
    }
}

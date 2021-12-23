package com.payroll.models.payment.method;

public class Deposit implements PaymentMethod {

    @Override
    public String getDescription() {
        return "Depósito em conta bancária.";
    }

    @Override
    public String toString() {
        return this.getDescription();
    }
}

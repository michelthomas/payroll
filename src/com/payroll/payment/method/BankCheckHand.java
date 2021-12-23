package com.payroll.payment.method;

public class BankCheckHand implements PaymentMethod {

    @Override
    public String getDescription() {
        return "Cheque em mãos.";
    }
}

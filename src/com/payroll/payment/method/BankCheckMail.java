package com.payroll.payment.method;

public class BankCheckMail implements PaymentMethod {

    @Override
    public String getDescription() {
        return "Cheque pelos correios.";
    }

    @Override
    public String toString() {
        return this.getDescription();
    }
}

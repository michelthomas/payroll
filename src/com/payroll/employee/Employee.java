package com.payroll.employee;

import com.payroll.payment.PaymentCheck;
import com.payroll.payment.PaymentInfo;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public abstract class Employee {

    private UUID id;
    private String documentNumber;
    private String name;
    private String address;
    private PaymentInfo paymentInfo;
    private List<PaymentCheck> paymentChecks;

    public Employee(String documentNumber, String name, String address) {
        this.id = UUID.randomUUID();
        this.documentNumber = documentNumber;
        this.name = name;
        this.address = address;
    }

    public Employee(UUID id, String documentNumber, String name, String address) {
        this.id = id;
        this.documentNumber = documentNumber;
        this.name = name;
        this.address = address;
    }

    public UUID getId() {
        return this.id;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public abstract Double calculateGrossSalary();

    public PaymentInfo getPaymentInfo() {
        return this.paymentInfo;
    }

    public void setPaymentInfo(PaymentInfo paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    public List<PaymentCheck> getPaymentChecks() {
        return paymentChecks;
    }

    public void setPaymentChecks(List<PaymentCheck> paymentChecks) {
        this.paymentChecks = paymentChecks;
    }

    public PaymentCheck getLastPaymentCheck() {
        return this.paymentChecks.stream()
                .max(Comparator.comparing(PaymentCheck::getDate))
                .orElse(null);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", documentNumber='" + documentNumber + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", \npaymentInfo=" + paymentInfo +
                ", paymentChecks=" + paymentChecks +
                "}\n";
    }
}

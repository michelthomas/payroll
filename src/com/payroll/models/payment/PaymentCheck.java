package com.payroll.models.payment;

import java.time.LocalDate;
import java.util.UUID;

public class PaymentCheck {

    private UUID id;
    private final Double grossSalary;
    private final Double syndicateDiscounts;
    private final Double netSalary;
    private final LocalDate date;

    public PaymentCheck(Double grossSalary, Double syndicateDiscounts, LocalDate date) {
        this.grossSalary = grossSalary;
        this.syndicateDiscounts = syndicateDiscounts;
        this.date = date;
        this.netSalary = this.calculateNetSalary();
    }

    private Double calculateNetSalary() {
        return this.grossSalary - this.syndicateDiscounts;
    }

    public UUID getId() {
        return id;
    }

    public Double getGrossSalary() {
        return grossSalary;
    }

    public Double getSyndicateDiscounts() {
        return syndicateDiscounts;
    }

    public LocalDate getDate() {
        return date;
    }

    public Double getNetSalary() {
        return netSalary;
    }

    @Override
    public String toString() {
        return "PaymentCheck{" +
                "id=" + id +
                ", grossSalary=" + grossSalary +
                ", syndicateDiscounts=" + syndicateDiscounts +
                ", netSalary=" + netSalary +
                ", date=" + date +
                "}\n";
    }
}

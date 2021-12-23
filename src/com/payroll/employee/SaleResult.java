package com.payroll.employee;

import java.time.LocalDate;

public class SaleResult {

    private LocalDate date;
    private double value;

    public SaleResult(LocalDate date, double value) {
        this.date = date;
        this.value = value;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "\n\tSaleResult{" +
                "date=" + date +
                ", value=" + value +
                '}';
    }
}

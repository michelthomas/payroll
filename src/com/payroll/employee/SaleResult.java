package com.payroll.employee;

import java.util.Date;

public class SaleResult {

    private Date date;
    private double value;

    public SaleResult(Date date, double value) {
        this.date = date;
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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

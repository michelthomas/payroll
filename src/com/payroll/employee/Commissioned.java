package com.payroll.employee;

import java.util.UUID;

public class Commissioned extends Employee {

    private Double baseSalary;
    private Double percentage;

    public Commissioned(String documentNumber, String name, String address, Double baseSalary, Double percentage) {
        super(documentNumber, name, address);
        this.baseSalary = baseSalary;
        this.percentage = percentage;
    }

    public Commissioned(UUID id, String documentNumber, String name, String address, Double baseSalary, Double percentage) {
        super(id, documentNumber, name, address);
        this.baseSalary = baseSalary;
        this.percentage = percentage;
    }

    public Double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(Double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }
}

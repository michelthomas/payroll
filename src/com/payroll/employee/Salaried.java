package com.payroll.employee;

import java.util.UUID;

public class Salaried extends Employee {

    private Double monthlySalary;

    public Salaried(String documentNumber, String name, String address, Double monthlySalary) {
        super(documentNumber, name, address);
        this.monthlySalary = monthlySalary;
    }

    public Salaried(UUID id, String documentNumber, String name, String address, Double monthlySalary) {
        super(id, documentNumber, name, address);
        this.monthlySalary = monthlySalary;
    }

    public Double getMonthlySalary() {
        return monthlySalary;
    }

    public void setMonthlySalary(Double monthlySalary) {
        this.monthlySalary = monthlySalary;
    }

    @Override
    public Double calculateGrossSalary() {
        return this.monthlySalary;
    }
}

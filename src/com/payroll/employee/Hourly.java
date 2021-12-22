package com.payroll.employee;

import java.util.UUID;

public class Hourly extends Employee {

    private Double hourlySalary;

    public Hourly(String documentNumber, String name, String address, Double hourlySalary) {
        super(documentNumber, name, address);
        this.hourlySalary = hourlySalary;
    }

    public Hourly(UUID id, String documentNumber, String name, String address, Double hourlySalary) {
        super(id, documentNumber, name, address);
        this.hourlySalary = hourlySalary;
    }

    public Double getHourlySalary() {
        return hourlySalary;
    }

    public void setHourlySalary(Double hourlySalary) {
        this.hourlySalary = hourlySalary;
    }
}

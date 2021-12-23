package com.payroll.employee;

import java.util.List;
import java.util.UUID;

public class Commissioned extends Employee {

    private Double baseSalary;
    private Double percentage;
    private List<SaleResult> salesResults;

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

    public List<SaleResult> getSalesResults() {
        return salesResults;
    }

    public void setSalesResults(List<SaleResult> salesResults) {
        this.salesResults = salesResults;
    }

    @Override
    public Double calculateGrossSalary() {
        Double sumSalesResults = this.getSalesResults().stream().mapToDouble(SaleResult::getValue).sum();
        return this.baseSalary + (sumSalesResults * this.percentage);
    }

    @Override
    public String toString() {
        return "Commissioned{\n" +
                "\tbaseSalary=" + baseSalary +
                ", percentage=" + percentage +
                ", salesResults=\n\t" + salesResults +
                '}';
    }
}

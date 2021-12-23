package com.payroll.employee;

import java.time.LocalDate;
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

    public List<SaleResult> getSalesResultsByDate(LocalDate begin, LocalDate end) {
        return salesResults.stream().filter(saleResult -> {
            return (saleResult.getDate().isAfter(begin) || saleResult.getDate().isEqual(begin))
                    && (saleResult.getDate().isBefore(end) || saleResult.getDate().isEqual(end));
        }).toList();
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
        return "Commissioned{" +
                super.toString() +
                "baseSalary=" + baseSalary +
                ", percentage=" + percentage +
                ", salesResults=" + salesResults +
                "}\n";
    }
}

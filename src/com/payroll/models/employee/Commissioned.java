package com.payroll.models.employee;

import com.payroll.DB;
import com.payroll.models.payment.PaymentCheck;
import com.payroll.models.payment.PaymentInfo;

import java.time.LocalDate;
import java.time.Month;
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
        this.setPaymentInfo(new PaymentInfo(DB.getInstance().paymentSchedules.get("semanal-2-sexta"), null));
    }

    public Commissioned(UUID id, String documentNumber, String name, String address, Double baseSalary, Double percentage) {
        super(id, documentNumber, name, address);
        this.baseSalary = baseSalary;
        this.percentage = percentage;
        this.setPaymentInfo(new PaymentInfo(DB.getInstance().paymentSchedules.get("semanal-2-sexta"), null));
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
            return (saleResult.getDate().isAfter(begin))
                    && (saleResult.getDate().isBefore(end) || saleResult.getDate().isEqual(end));
        }).toList();
    }

    public void setSalesResults(List<SaleResult> salesResults) {
        this.salesResults = salesResults;
    }

    @Override
    public Double calculateGrossSalary(int payday) {
        if (this.getSalesResults() == null) {
            return baseSalary;
        }

        Month month = LocalDate.now().getMonth();
        int year = LocalDate.now().getYear();
        int lastPayday = 1;

        LocalDate begin;

        PaymentCheck lastPaymentCheck = this.getLastPaymentCheck();

        if (lastPaymentCheck != null) {
            LocalDate lastPaymentCheckDate = lastPaymentCheck.getDate();

            if (lastPaymentCheckDate != null) {

                lastPayday = lastPaymentCheckDate.getDayOfMonth();

                if (lastPaymentCheckDate.getMonth() != LocalDate.now().getMonth()) {
                    month = lastPaymentCheckDate.getMonth();
                }

                if (lastPaymentCheckDate.getYear() != LocalDate.now().getYear()) {
                    year = lastPaymentCheckDate.getYear();
                }
            }

            begin = LocalDate.of(year, month, lastPayday);

        } else {

            // If there are no paychecks, decrease one day to count from the first of the month
            begin = LocalDate.of(year, month, lastPayday).minusDays(1);
        }

        LocalDate end = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), payday);


        Double sumSalesResults = this.getSalesResultsByDate(begin, end).stream().mapToDouble(SaleResult::getValue).sum();

        return this.baseSalary + (sumSalesResults * (this.percentage / 100));
    }

    @Override
    public String toString() {
        return "\nCommissioned{\n\t" +
                super.toString() +
                "\tbaseSalary=" + baseSalary +
                ", percentage=" + percentage +
                ", salesResults=" + salesResults +
                "\n}\n";
    }
}

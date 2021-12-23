package com.payroll.models.syndicate;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class Affiliate {

    private UUID id;
    private String documentNumber;
    private Double monthlyFee;
    private List<AdditionalServiceFee> additionalServiceFees;

    public Affiliate(String documentNumber, Double monthlyFee) {
        this.id = UUID.randomUUID();
        this.documentNumber = documentNumber;
        this.monthlyFee = monthlyFee;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public Double getMonthlyFee() {
        return monthlyFee;
    }

    public void setMonthlyFee(Double monthlyFee) {
        this.monthlyFee = monthlyFee;
    }

    public List<AdditionalServiceFee> getAdditionalServiceFees() {
        return additionalServiceFees;
    }

    public void setAdditionalServiceFees(List<AdditionalServiceFee> additionalServiceFees) {
        this.additionalServiceFees = additionalServiceFees;
    }

    public Double calculateAdditionalFeeByDatePeriod(LocalDate begin, LocalDate end) {
        return this.additionalServiceFees.stream().filter(additionalServiceFee -> {
            return (additionalServiceFee.getDate().isAfter(begin) || additionalServiceFee.getDate().isEqual(begin))
                    && (additionalServiceFee.getDate().isBefore(end) || additionalServiceFee.getDate().isEqual(end));
        }).mapToDouble(AdditionalServiceFee::getFee).sum();
    }

    @Override
    public String toString() {
        return "Affiliate{" +
                "id=" + id +
                ", documentNumber='" + documentNumber + '\'' +
                ", monthlyFee=" + monthlyFee +
                ", additionalServiceFees=" + additionalServiceFees +
                "}\n";
    }
}

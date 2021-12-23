package com.payroll.models.syndicate;

import java.time.LocalDate;

public class AdditionalServiceFee {

    private String description;
    private Double fee;
    private LocalDate date;

    public AdditionalServiceFee(String description, Double fee, LocalDate date) {
        this.description = description;
        this.fee = fee;
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}

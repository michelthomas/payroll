package com.payroll.controllers;

import com.payroll.DB;
import com.payroll.exceptions.EmployeeDoesNotBelongToTheSyndicateException;
import com.payroll.models.syndicate.AdditionalServiceFee;
import com.payroll.models.syndicate.Affiliate;
import com.payroll.models.syndicate.Syndicate;
import com.payroll.utils.DateUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SyndicatesController {

    private final DB db;

    public SyndicatesController() {
        this.db = DB.getInstance();
    }

    public Syndicate getSyndicate() {
        return this.db.syndicate;
    }

    public List<Affiliate> getAllAffiliates() {
        return this.db.syndicate.getAffiliates();
    }

    public Affiliate getAffiliateByDocumentNumber(String documentNumber) {
        return this.db.syndicate.getAffiliateByDocumentNumber(documentNumber);
    }

    public void createAffiliate(String documentNumber, Double monthlyFee) {
        if (this.getAffiliateByDocumentNumber(documentNumber) == null) {
            this.db.syndicate.getAffiliates().add(new Affiliate(documentNumber, monthlyFee));
        } else {
            this.getAffiliateByDocumentNumber(documentNumber).setMonthlyFee(monthlyFee);
        }
    }

    public void removeAffiliate(String documentNumber) {
        this.db.syndicate.setAffiliates(this.db.syndicate.getAffiliates().stream()
                .filter(affiliate -> !affiliate.getDocumentNumber().equals(documentNumber))
                .collect(Collectors.toList())
        );
    }

    public void registerAdditionalServiceFee(String documentNumber, String description, Double fee, String date) {

        Affiliate affiliate = this.getAffiliateByDocumentNumber(documentNumber);

        if (affiliate != null) {
            AdditionalServiceFee serviceFee = new AdditionalServiceFee(description, fee, LocalDate.parse(date, DateUtils.formatter));

            if (affiliate.getAdditionalServiceFees() != null) {
                affiliate.getAdditionalServiceFees().add(serviceFee);
            } else {
                List<AdditionalServiceFee> list = new ArrayList<>();
                list.add(serviceFee);
                affiliate.setAdditionalServiceFees(list);
            }
        }
    }

    public void editAffiliateMonthlyFee(String documentNumber, Double newMonthlyFee) throws EmployeeDoesNotBelongToTheSyndicateException {

        Affiliate affiliate = this.getAffiliateByDocumentNumber(documentNumber);

        if (affiliate != null) {
            affiliate.setMonthlyFee(newMonthlyFee);
        } else {
            throw new EmployeeDoesNotBelongToTheSyndicateException();
        }
    }
}

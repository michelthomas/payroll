package com.payroll.models.syndicate;

import java.util.List;

public class Syndicate {

    private String name;
    private List<Affiliate> affiliates;

    public Syndicate(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Affiliate> getAffiliates() {
        return affiliates;
    }

    public void setAffiliates(List<Affiliate> affiliates) {
        this.affiliates = affiliates;
    }

    public Affiliate getAffiliateByDocumentNumber(String number) {
        return this.affiliates.stream()
                .filter(affiliate -> affiliate.getDocumentNumber().equals(number))
                .findAny()
                .orElse(null);
    }

    @Override
    public String toString() {
        return "Syndicate{" +
                "name='" + name + '\'' +
                ", affiliates=" + affiliates +
                "}\n";
    }
}

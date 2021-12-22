package com.payroll.employee;

import java.util.UUID;

public abstract class Employee {

    private UUID id;
    private String documentNumber;
    private String name;
    private String address;

    public Employee(String documentNumber, String name, String address) {
        this.id = UUID.randomUUID();
        this.documentNumber = documentNumber;
        this.name = name;
        this.address = address;
    }

    public Employee(UUID id, String documentNumber, String name, String address) {
        this.id = id;
        this.documentNumber = documentNumber;
        this.name = name;
        this.address = address;
    }

    public UUID getId() {
        return this.id;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

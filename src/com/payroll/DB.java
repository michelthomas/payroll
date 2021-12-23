package com.payroll;

import com.payroll.employee.Commissioned;
import com.payroll.employee.Employee;
import com.payroll.employee.Hourly;
import com.payroll.employee.Salaried;
import com.payroll.syndicate.Affiliate;
import com.payroll.syndicate.Syndicate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class DB {

    private static DB db = new DB();

    public HashMap<UUID, Employee> employees;

    public Syndicate syndicate;

    private DB() {
        this.employees = new HashMap<>();

        Employee[] employees = {
                new Salaried("123", "Marco", "Travessa travessa", 2000.0),
                new Commissioned("456", "Túlio", "Rua 111", 1000.0, 5.0),
                new Hourly("789", "Cícero", "Avenida avenue", 20.0)
        };

        for (Employee employee : employees) {
            this.employees.put(employee.getId(), employee);
        }

        List<Affiliate> affiliates = new ArrayList<Affiliate>();

        affiliates.add(new Affiliate(employees[0].getDocumentNumber(), 50.0));

        this.syndicate = new Syndicate("Sindicato Padrão");

        this.syndicate.setAffiliates(affiliates);
    }

    public static DB getInstance() {
        return db;
    }

    @Override
    public String toString() {
        return "DB{" +
                "employees=" + employees.values() +
                ",\nsyndicate=" + syndicate +
                '}';
    }
}

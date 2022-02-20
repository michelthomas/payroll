package com.payroll.controllers;

import com.payroll.DB;
import com.payroll.models.employee.*;
import com.payroll.utils.DateUtils;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class EmployeesController {

    private final DB db;

    public EmployeesController() {
        this.db = DB.getInstance();
    }

    public void store(Map<String, String> data) {

        Employee employee;

        switch (Integer.parseInt(data.get("type"))) {
            case 1:
                employee = new Hourly(
                        data.get("documentNumber"),
                        data.get("name"),
                        data.get("address"),
                        Double.parseDouble(data.get("hourlySalary"))
                );
                break;
            case 2:
                employee = new Salaried(
                        data.get("documentNumber"),
                        data.get("name"),
                        data.get("address"),
                        Double.parseDouble(data.get("salary"))
                );
                break;
            case 3:
                employee = new Commissioned(
                        data.get("documentNumber"),
                        data.get("name"),
                        data.get("address"),
                        Double.parseDouble(data.get("baseSalary")),
                        Double.parseDouble(data.get("percentage"))
                );
                break;
            default:
                return;
        }

        this.db.employees.put(employee.getId(), employee);
    }

    public List<Employee> index() {
        return this.db.employees.values().stream().toList();
    }

    public void update(Employee employee) {
        this.db.employees.replace(employee.getId(), employee);
    }

    public void delete(UUID id) {
        this.db.employees.remove(id);
    }

    public void registerTimeCard(Employee employee, Map<String, String> data) throws ParseException {
        TimeCard timeCard = new TimeCard(
                LocalDate.parse(data.get("date"), DateUtils.formatter),
                Integer.parseInt(data.get("dailyHours"))
        );

        if (((Hourly) employee).getTimeCards() != null) {
            ((Hourly) employee).getTimeCards().add(timeCard);
        } else {
            List<TimeCard> list = new ArrayList<>();
            list.add(timeCard);
            ((Hourly) employee).setTimeCards(list);
        }
    }

    public void registerSalesResult(Employee employee, Map<String, String> data) throws ParseException {
        SaleResult saleResult = new SaleResult(
                LocalDate.parse(data.get("date"), DateUtils.formatter),
                Double.parseDouble(data.get("value"))
        );

        if (((Commissioned) employee).getSalesResults() != null) {
            ((Commissioned) employee).getSalesResults().add(saleResult);
        } else {
            List<SaleResult> list = new ArrayList<>();
            list.add(saleResult);
            ((Commissioned) employee).setSalesResults(list);
        }
    }


    public void changeEmployeeType(Employee employee, Map<String, String> data) {
        this.db.employees.remove(employee.getId());

        Employee employeeUpdated;

        // TODO PaymentInfo will be the same?
        switch (data.get("type")) {
            case "hourly":
                employeeUpdated = new Hourly(employee.getId(),
                        employee.getDocumentNumber(),
                        employee.getName(),
                        employee.getAddress(),
                        Double.parseDouble(data.get("hourlySalary")));

                employeeUpdated.setPaymentInfo(employee.getPaymentInfo());
                employeeUpdated.setPaymentChecks(employee.getPaymentChecks());

                break;
            case "salaried":
                employeeUpdated = new Salaried(employee.getId(),
                        employee.getDocumentNumber(),
                        employee.getName(),
                        employee.getAddress(),
                        Double.parseDouble(data.get("salary")));

                employeeUpdated.setPaymentInfo(employee.getPaymentInfo());
                employeeUpdated.setPaymentChecks(employee.getPaymentChecks());

                break;
            case "commissioned":
                employeeUpdated = new Commissioned(employee.getId(),
                        employee.getDocumentNumber(),
                        employee.getName(),
                        employee.getAddress(),
                        Double.parseDouble(data.get("baseSalary")),
                        Double.parseDouble(data.get("percentage")));

                employeeUpdated.setPaymentInfo(employee.getPaymentInfo());
                employeeUpdated.setPaymentChecks(employee.getPaymentChecks());

                break;
            default:
                return;
        }

        this.db.employees.put(employeeUpdated.getId(), employeeUpdated);
    }

    public List<Employee> getHourlyEmployees() {
        return this.db.employees.values().stream().filter(employee -> employee instanceof Hourly).toList();
    }

    public List<Employee> getSalariedEmployees() {
        return this.db.employees.values().stream().filter(employee -> employee instanceof Salaried).toList();
    }

    public List<Employee> getCommissionedEmployees() {
        return this.db.employees.values().stream().filter(employee -> employee instanceof Commissioned).toList();
    }
}

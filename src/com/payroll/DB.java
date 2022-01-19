package com.payroll;

import com.payroll.models.employee.*;
import com.payroll.models.payment.PaymentInfo;
import com.payroll.models.payment.method.BankCheckHand;
import com.payroll.models.payment.method.BankCheckMail;
import com.payroll.models.payment.method.Deposit;
import com.payroll.models.payment.method.PaymentMethod;
import com.payroll.models.payment.schedule.LastWorkingDayOfTheMonth;
import com.payroll.models.payment.schedule.PaymentSchedule;
import com.payroll.models.payment.schedule.Weekly;
import com.payroll.models.syndicate.Affiliate;
import com.payroll.models.syndicate.Syndicate;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class DB {

    private static DB db = new DB();

    public HashMap<UUID, Employee> employees;

    public Syndicate syndicate;

    public HashMap<String, PaymentSchedule> paymentSchedules;

    public HashMap<String, PaymentMethod> paymentMethods;

    private DB() {
        this.employees = new HashMap<>();
        this.paymentSchedules = new HashMap<>();
        this.paymentMethods = new HashMap<>();
        this.syndicate = new Syndicate("Sindicato Padrão");
    }

    public static DB getInstance() {
        return db;
    }

    public void seed() {
        this.paymentSchedules.put("mensal-$", new LastWorkingDayOfTheMonth());
        this.paymentSchedules.put("semanal-2-sexta", new Weekly(2, DayOfWeek.FRIDAY));
        this.paymentSchedules.put("semanal-1-sexta", new Weekly(4, DayOfWeek.FRIDAY));

        this.paymentMethods.put("deposito", new Deposit());
        this.paymentMethods.put("cheque-correios", new BankCheckMail());
        this.paymentMethods.put("cheque-maos", new BankCheckHand());

        Employee[] employees = {
                new Salaried("123", "Marco", "Travessa travessa", 2000.0),
                new Commissioned("456", "Túlio", "Rua 111", 1000.0, 5.0),
                new Hourly("789", "Cícero", "Avenida avenue", 20.0)
        };

        employees[0].getPaymentInfo().setMethod(this.paymentMethods.get("deposito"));
        employees[1].getPaymentInfo().setMethod(this.paymentMethods.get("cheque-correios"));
        employees[2].getPaymentInfo().setMethod(this.paymentMethods.get("cheque-maos"));


        List<TimeCard> timeCardList = new ArrayList<>();
        timeCardList.add(new TimeCard(LocalDate.of(2021, Month.NOVEMBER, 5), 8));
        timeCardList.add(new TimeCard(LocalDate.of(2021, Month.NOVEMBER, 6), 9));
        timeCardList.add(new TimeCard(LocalDate.of(2021, Month.DECEMBER, 8), 10));

        ((Hourly) employees[2]).setTimeCards(timeCardList);

        for (Employee employee : employees) {
            this.employees.put(employee.getId(), employee);
        }

        List<Affiliate> affiliates = new ArrayList<Affiliate>();

        affiliates.add(new Affiliate(employees[0].getDocumentNumber(), 50.0));


        this.syndicate.setAffiliates(affiliates);
    }

    @Override
    public String toString() {
        return "DB{" +
                "employees=" + employees.values() +
                ",\nsyndicate=" + syndicate +
                '}';
    }
}

package com.payroll.services;

import com.payroll.DB;
import com.payroll.models.employee.Employee;
import com.payroll.models.payment.PaymentCheck;
import com.payroll.models.syndicate.Affiliate;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class PaymentService {

    private final DB db = DB.getInstance();

    private void pay(Employee employee, PaymentCheck check) {
        System.out.println("**********\n" +
                "Pagamento Realizado!\nEmpregado: " + employee.getName() +
                "\nValor: " + check.getNetSalary() +
                "\nMétodo: " + employee.getPaymentInfo().getMethod().getDescription() +
                "\n**********\n"
        );
    }

    private Double calculateSyndicateDiscounts(Employee employee, int payday) {

        Affiliate affiliate = this.db.syndicate.getAffiliateByDocumentNumber(employee.getDocumentNumber());

        if (affiliate == null) {
            return 0.0;
        }

        // TODO Rename these
        Month month = LocalDate.now().getMonth();
        int year = LocalDate.now().getYear();
        int lastPayday = 1;

        LocalDate begin;

        PaymentCheck lastPaymentCheck = employee.getLastPaymentCheck();

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

        Double additionalFeesValue = affiliate.calculateAdditionalFeeByDatePeriod(begin, end);

        int numberOfPaydaysInMonth = employee.getPaymentInfo().getSchedule().getPaydaysInTheMonth().size();

        Double monthlyFeeValue = affiliate.getMonthlyFee() / numberOfPaydaysInMonth;

        return monthlyFeeValue + additionalFeesValue;
    }

    private PaymentCheck generatePaymentCheck(Employee employee, int day) {

        return new PaymentCheck(
                employee.calculateGrossSalary(),
                this.calculateSyndicateDiscounts(employee, day),
                LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), day)
        );
    }

    public void payEmployeesByDay(int day) {
        List<Employee> employees = this.db.employees.values().stream().filter(employee ->
                employee.getPaymentInfo().getSchedule().getPaydaysInTheMonth().contains(day)
        ).toList();


        employees.forEach(employee -> {
            List<PaymentCheck> paymentChecks = employee.getPaymentChecks();

            if (paymentChecks == null) {
                paymentChecks = new ArrayList<>();
            }

            PaymentCheck paymentCheck = this.generatePaymentCheck(employee, day);

            paymentChecks.add(paymentCheck);

            employee.setPaymentChecks(paymentChecks);

            this.pay(employee, paymentCheck);
        });
    }


}

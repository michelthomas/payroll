package com.payroll.payment;

import com.payroll.DB;
import com.payroll.employee.Employee;
import com.payroll.payment.schedule.PaymentSchedule;
import com.payroll.syndicate.Affiliate;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.List;
import java.util.stream.Stream;

public class PaymentService {

    private final DB db = DB.getInstance();

    private void pay(Employee employee, PaymentCheck check) {
        System.out.println("Pagamento Realizado!\nEmpregado: " + employee.getName() +
                "\nValor: " + check.getNetSalary() +
                "\nMétodo: " + employee.getPaymentInfo().getMethod().getDescription()
        );
    }

    private Double calculateSyndicateDiscounts(Employee employee, int payday) {

        Affiliate affiliate = this.db.syndicate.getAffiliateByDocumentNumber(employee.getDocumentNumber());

        // TODO Rename these
        Month month = LocalDate.now().getMonth();
        int year = LocalDate.now().getYear();
        int lastPayday = 1;

        LocalDate lastPaymentCheckDate = employee.getLastPaymentCheck().getDate();

        if (lastPaymentCheckDate != null) {

            lastPayday = lastPaymentCheckDate.getDayOfMonth();

            if (lastPaymentCheckDate.getMonth() != LocalDate.now().getMonth()) {
                month = lastPaymentCheckDate.getMonth();
            }

            if (lastPaymentCheckDate.getYear() != LocalDate.now().getYear()) {
                year = lastPaymentCheckDate.getYear();
            }
        }

        LocalDate begin = LocalDate.of(year, month, 1);
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

            PaymentCheck paymentCheck = this.generatePaymentCheck(employee, day);

            paymentChecks.add(paymentCheck);

            this.pay(employee, paymentCheck);
        });
    }


}

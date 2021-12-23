package com.payroll.payment.schedule;

import java.time.LocalDate;
import java.util.List;

public interface PaymentSchedule {

    List<Integer> getPaydaysInTheMonth();

    List<Integer> getPaydaysInTheMonthByDate(LocalDate date);

}

package com.payroll.payment.schedule;

import java.util.Date;
import java.util.List;

public interface PaymentSchedule {

    List<Integer> getPaydaysInTheMonth();

    List<Integer> getPaydaysInTheMonthByDate(Date date);

}

package com.payroll.payment.schedule;

import java.util.Date;
import java.util.List;

public class LastWorkingDayOfTheMonth implements PaymentSchedule {

    @Override
    public List<Integer> getPaydaysInTheMonth() {
        return null;
    }

    @Override
    public List<Integer> getPaydaysInTheMonthByDate(Date date) {
        return null;
    }
}

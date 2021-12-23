package com.payroll.payment.schedule;

import java.util.Date;
import java.util.List;

public class Monthly implements PaymentSchedule {

    private int day;

    public Monthly(int day) {
        this.day = day;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @Override
    public List<Integer> getPaydaysInTheMonth() {
        return null;
    }

    @Override
    public List<Integer> getPaydaysInTheMonthByDate(Date date) {
        return null;
    }
}

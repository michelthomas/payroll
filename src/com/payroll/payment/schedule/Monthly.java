package com.payroll.payment.schedule;

import java.time.LocalDate;
import java.util.ArrayList;
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
        return new ArrayList<>(day);
    }

    @Override
    public List<Integer> getPaydaysInTheMonthByDate(LocalDate date) {
        return new ArrayList<>(day);
    }
}
package com.payroll.payment.schedule;

import java.time.DayOfWeek;
import java.util.Date;
import java.util.List;

public class Weekly implements PaymentSchedule{

    private int timesInTheMonth;
    private DayOfWeek dayOfWeek;

    public Weekly(int timesInTheMonth, DayOfWeek dayOfWeek) {
        this.timesInTheMonth = timesInTheMonth;
        this.dayOfWeek = dayOfWeek;
    }

    public int getTimesInTheMonth() {
        return timesInTheMonth;
    }

    public void setTimesInTheMonth(int timesInTheMonth) {
        this.timesInTheMonth = timesInTheMonth;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
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

package com.payroll.employee;

import java.util.Date;

public class TimeCard {

    private final Date date;
    private final int dailyHours;

    public TimeCard(Date date, int dailyHours) {
        this.date = date;
        this.dailyHours = dailyHours;
    }

    public Date getDate() {
        return date;
    }

    public int getDailyHours() {
        return dailyHours;
    }

    @Override
    public String toString() {
        return "TimeCard{" +
                "date=" + date +
                ", dailyHours=" + dailyHours +
                '}';
    }
}

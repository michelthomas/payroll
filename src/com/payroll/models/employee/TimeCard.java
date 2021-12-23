package com.payroll.models.employee;

import java.time.LocalDate;

public class TimeCard {

    private final LocalDate date;
    private final int dailyHours;

    public TimeCard(LocalDate date, int dailyHours) {
        this.date = date;
        this.dailyHours = dailyHours;
    }

    public LocalDate getDate() {
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

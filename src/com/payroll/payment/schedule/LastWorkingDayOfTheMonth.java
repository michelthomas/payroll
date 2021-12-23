package com.payroll.payment.schedule;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LastWorkingDayOfTheMonth implements PaymentSchedule {

    @Override
    public List<Integer> getPaydaysInTheMonth() {
        return getPaydaysInTheMonthByDate(LocalDate.now());
    }

    @Override
    public List<Integer> getPaydaysInTheMonthByDate(LocalDate date) {

        int lastDayOfMonth = date.getMonth().maxLength();

        DayOfWeek dayOfWeek = LocalDate.of(date.getYear(), date.getMonth(), lastDayOfMonth).getDayOfWeek();

        switch (dayOfWeek) {
            case SUNDAY -> lastDayOfMonth -= 2;
            case SATURDAY -> lastDayOfMonth -= 1;
        }

        return new ArrayList<>(lastDayOfMonth);
    }
}

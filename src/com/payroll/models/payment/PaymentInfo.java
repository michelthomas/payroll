package com.payroll.models.payment;

import com.payroll.models.payment.method.PaymentMethod;
import com.payroll.models.payment.schedule.PaymentSchedule;

public class PaymentInfo {

    private PaymentSchedule schedule;
    private PaymentMethod method;

    public PaymentInfo(PaymentSchedule schedule, PaymentMethod method) {
        this.schedule = schedule;
        this.method = method;
    }

    public PaymentSchedule getSchedule() {
        return schedule;
    }

    public void setSchedule(PaymentSchedule schedule) {
        this.schedule = schedule;
    }

    public PaymentMethod getMethod() {
        return method;
    }

    public void setMethod(PaymentMethod method) {
        this.method = method;
    }

    @Override
    public String toString() {
        return "PaymentInfo{" +
                "schedule=" + schedule +
                ", method=" + method +
                "}\n";
    }
}

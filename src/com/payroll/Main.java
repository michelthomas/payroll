package com.payroll;

import com.payroll.models.employee.Employee;
import com.payroll.models.payment.PaymentCheck;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        DB.getInstance().seed();

        System.out.println(DB.getInstance());
        System.out.println("------------------");

        try {
            Menu.show();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println(DB.getInstance());
        System.out.println("------------------");



    }
}

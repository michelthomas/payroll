package com.payroll.exceptions;

public class EmployeeDoesNotBelongToTheSyndicateException extends Throwable {
    public EmployeeDoesNotBelongToTheSyndicateException() {
    }

    @Override
    public String getMessage() {
        return "Empregado não pertence ao sindicato.";
    }
}

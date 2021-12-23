package com.payroll.employee;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Hourly extends Employee {

    private Double hourlySalary;
    private List<TimeCard> timeCards;

    public Hourly(String documentNumber, String name, String address, Double hourlySalary) {
        super(documentNumber, name, address);
        this.hourlySalary = hourlySalary;
    }

    public Hourly(UUID id, String documentNumber, String name, String address, Double hourlySalary) {
        super(id, documentNumber, name, address);
        this.hourlySalary = hourlySalary;
    }

    public Double getHourlySalary() {
        return hourlySalary;
    }

    public void setHourlySalary(Double hourlySalary) {
        this.hourlySalary = hourlySalary;
    }

    @Override
    public Double calculateGrossSalary() {
        return this.getTimeCards().stream().mapToDouble(timeCard -> {
            Double dailySalary = timeCard.getDailyHours() > 8 ?
                    (timeCard.getDailyHours() - 8) * this.hourlySalary * 1.5 + 8 * this.hourlySalary
                    : timeCard.getDailyHours() * this.hourlySalary;
            System.out.println(dailySalary);
            return dailySalary;
        }).sum();
    }

    public List<TimeCard> getTimeCards() {
        return timeCards;
    }

    public List<TimeCard> getTimeCardsByDate(LocalDate begin, LocalDate end) {
        return timeCards.stream().filter(timeCard -> {
            return (timeCard.getDate().isAfter(begin) || timeCard.getDate().isEqual(begin))
                    && (timeCard.getDate().isBefore(end) || timeCard.getDate().isEqual(end));
        }).toList();
    }

    public void setTimeCards(List<TimeCard> timeCards) {
        this.timeCards = timeCards;
    }

    @Override
    public String toString() {
        return "Hourly{" +
                "hourlySalary=" + hourlySalary +
                ", timeCards=" + timeCards +
                '}';
    }
}

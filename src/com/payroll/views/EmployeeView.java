package com.payroll.views;

import com.payroll.Main;
import com.payroll.Menu;
import com.payroll.controllers.EmployeesController;
import com.payroll.models.employee.Employee;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class EmployeeView {

    public static Scanner scanner = new Scanner(System.in);
    public static EmployeesController employeesController = new EmployeesController();

    public static void listEmployees() throws ParseException {

        List<Employee> employeeList = employeesController.index();

        System.out.println("Lista de Empregados:");

        for (Employee employee : employeeList) {
            System.out.println(employee);
        }

        System.out.println("---------------------------");

        Menu.show();
    }

    public static void registerEmployee() throws ParseException {

        Map<String, String> data = new HashMap<>();

        System.out.println("Digite o nome:");
        data.put("name", scanner.nextLine());

        System.out.println("Digite o número do documento:");
        data.put("documentNumber", scanner.nextLine());

        System.out.println("Digite o endereço:");
        data.put("address", scanner.nextLine());

        System.out.println("Digite o tipo:");
        System.out.println("1 - Horista\n2 - Assalariado\n3 - Comissionado");
        String type = scanner.nextLine();
        data.put("type", type);

        switch (Integer.parseInt(type)) {
            case 1:
                System.out.println("Digite o valor da hora:");
                data.put("hourlySalary", scanner.nextLine());
                break;
            case 2:
                System.out.println("Digite o salário:");
                data.put("salary", scanner.nextLine());
                break;
            case 3:
                System.out.println("Digite o salário base:");
                data.put("baseSalary", scanner.nextLine());
                System.out.println("Digite a porcentagem:");
                data.put("percentage", scanner.nextLine());
                break;
        }

        employeesController.store(data);

        System.out.println("---------------------------");

        Menu.show();
    }

    public static void deleteEmployee() throws ParseException {

        List<Employee> employeeList = employeesController.index();

        System.out.println("Lista de Empregados:");

        int i = 0;
        for (Employee employee : employeeList) {
            System.out.println("[" + i + "] - " + employee.getName() + " - " + employee.getId());
            i++;
        }

        System.out.println("\nSelecione o empregado que será removido: ");
        int n = scanner.nextInt();

        employeesController.delete(employeeList.get(n).getId());

        System.out.println("---------------------------");

        Menu.show();
    }

    public static void registerTimeCard() throws ParseException {

        Map<String, String> data = new HashMap<>();

        List<Employee> employeeList = employeesController.getHourlyEmployees();

        System.out.println("Lista de Empregados:");

        int i = 0;
        for (Employee employee : employeeList) {
            System.out.println("[" + i + "] - " + employee.getName() + " - " + employee.getId());
            i++;
        }

        System.out.println("\nSelecione o empregado para submeter o cartão de ponto:");
        String n = scanner.nextLine();

        System.out.println("Digite a data do cartão de ponto:");
        data.put("date", scanner.nextLine());

        System.out.println("Digite a quantidade de horas trabalhadas: ");
        data.put("dailyHours", scanner.nextLine());

        employeesController.registerTimeCard(employeeList.get(Integer.parseInt(n)), data);

        System.out.println("---------------------------");

        Menu.show();
    }

    public static void registerSalesResult() throws ParseException {

        Map<String, String> data = new HashMap<>();

        List<Employee> employeeList = employeesController.getCommissionedEmployees();

        System.out.println("Lista de Empregados:");

        int i = 0;
        for (Employee employee : employeeList) {
            System.out.println("[" + i + "] - " + employee.getName() + " - " + employee.getId());
            i++;
        }

        System.out.println("\nSelecione o empregado para submeter o resultado de vendas:");
        String n = scanner.nextLine();

        System.out.println("Digite a data da venda:");
        data.put("date", scanner.nextLine());

        System.out.println("Digite o valor da venda: ");
        data.put("value", scanner.nextLine());

        employeesController.registerSalesResult(employeeList.get(Integer.parseInt(n)), data);

        System.out.println("---------------------------");

        Menu.show();
    }
}

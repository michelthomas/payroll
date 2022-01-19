package com.payroll.views;

import com.payroll.Menu;
import com.payroll.controllers.EmployeesController;
import com.payroll.controllers.PaymentsController;
import com.payroll.controllers.SyndicatesController;
import com.payroll.models.employee.Commissioned;
import com.payroll.models.employee.Employee;
import com.payroll.models.employee.Hourly;
import com.payroll.models.employee.Salaried;
import com.payroll.models.payment.method.PaymentMethod;
import com.payroll.models.syndicate.Affiliate;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class EditEmployeeView {


    public static Scanner scanner = new Scanner(System.in);
    public static EmployeesController employeesController = new EmployeesController();
    public static PaymentsController paymentsController = new PaymentsController();
    public static SyndicatesController syndicatesController = new SyndicatesController();

    public static void menu() throws ParseException {
        System.out.println("0. Alterar nome");
        System.out.println("1. Alterar endereço");
        System.out.println("2. Alterar tipo");
        System.out.println("3. Alterar método de pagamento");
        System.out.println("4. Sindicarlizar-se");
        System.out.println("5. Dessindicalizar-se");
        System.out.println("6. Alterar id no sindicato(?!)");
        System.out.println("7. Alterar taxa sindical");
        System.out.println("8. Listar sindicalizados");
        System.out.println("9. Voltar");


        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 0 -> EditEmployeeView.editName();
            case 1 -> EditEmployeeView.editAddress();
            case 2 -> EditEmployeeView.editType();
            case 3 -> EditEmployeeView.editPaymentMethod();
            case 4 -> EditEmployeeView.joinSyndicate();
            case 5 -> EditEmployeeView.leaveSyndicate();
            case 6 -> EditEmployeeView.editEmployeeSyndicateId();
            case 7 -> EditEmployeeView.editSyndicateMonthlyFee();
            case 8 -> EditEmployeeView.listAffiliates();
            case 9 -> Menu.show();
            default -> {
                System.out.println("Opção inválida!");
                menu();
            }
        }
        System.out.println("---------------------------");

        menu();
    }

    private static void listAffiliates() throws ParseException {
        List<Affiliate> employeeList = syndicatesController.getAllAffiliates();

        System.out.println("Lista de Sindicalizados:");

        employeeList.forEach(System.out::println);

        System.out.println("---------------------------");

        menu();
    }

    private static void editName() throws ParseException {
        List<Employee> employeeList = employeesController.index();

        System.out.println("Lista de Empregados:");

        int i = 0;
        for (Employee employee : employeeList) {
            System.out.println("[" + i + "] - " + employee.getName() + " - " + employee.getId());
            i++;
        }

        System.out.println("\nSelecione o empregado para editar seu nome: ");
        String n = scanner.nextLine();

        Employee employee = employeeList.get(Integer.parseInt(n));

        System.out.println("Digite o novo nome: ");
        employee.setName(scanner.nextLine());

        employeesController.update(employee);

        System.out.println("---------------------------");

        menu();
    }

    private static void editAddress() throws ParseException {
        List<Employee> employeeList = employeesController.index();

        System.out.println("Lista de Empregados:");

        int i = 0;
        for (Employee employee : employeeList) {
            System.out.println("[" + i + "] - " + employee.getName() + " - " + employee.getId());
            i++;
        }

        System.out.println("\nSelecione o empregado para editar seu endereço: ");
        String n = scanner.nextLine();

        Employee employee = employeeList.get(Integer.parseInt(n));

        System.out.println("Digite o novo endereço: ");
        employee.setAddress(scanner.nextLine());

        employeesController.update(employee);

        System.out.println("---------------------------");

        menu();
    }

    private static void editType() {
        List<Employee> employeeList = employeesController.index();

        System.out.println("Lista de Empregados:");

        int i = 0;
        for (Employee employee : employeeList) {
            System.out.println("[" + i + "] - " + employee.getName() + " - " + employee.getId());
            i++;
        }

        System.out.println("\nSelecione o empregado para mudar seu tipo: ");
        String n = scanner.nextLine();

        Employee employee = employeeList.get(Integer.parseInt(n));

        System.out.println("0.Horista\n1.Assalariado\n2.Comissionado");

        Map<String, String> data = new HashMap<>();

        switch (Integer.parseInt(scanner.nextLine())) {
            case 0:
                if (employee instanceof Hourly) {
                    System.out.println("O empregado já é horista!");
                    return;
                } else {
                    System.out.println("Digite o valor da hora de trabalho: ");
                    data.put("hourlySalary", scanner.nextLine());
                    data.put("type", "hourly");
                }
                break;
            case 1:
                if (employee instanceof Salaried) {
                    System.out.println("O empregado já é assalariado!");
                    return;
                } else {
                    System.out.println("Digite o valor do salário: ");
                    data.put("salary", scanner.nextLine());
                    data.put("type", "salaried");
                }
                break;
            case 2:
                if (employee instanceof Commissioned) {
                    System.out.println("O empregado já é comissionado!");
                    return;
                } else {
                    System.out.println("Digite o valor do salário base: ");
                    data.put("baseSalary", scanner.nextLine());
                    System.out.println("Digite o valor da porcentagem: ");
                    data.put("percentage", scanner.nextLine());
                    data.put("type", "commissioned");
                }
                break;
            default:
                System.out.println("Opção inválida!");
                return;
        }

        employeesController.changeEmployeeType(employee, data);
    }

    private static void editPaymentMethod() throws ParseException {
        List<Employee> employeeList = employeesController.index();

        System.out.println("Lista de Empregados:");

        int i = 0;
        for (Employee employee : employeeList) {
            System.out.println("[" + i + "] - " + employee.getName() + " - " + employee.getId());
            i++;
        }

        System.out.println("\nSelecione o empregado para alterar a forma de pagamento: ");
        String n = scanner.nextLine();

        Employee employee = employeeList.get(Integer.parseInt(n));

        System.out.println("Método de pagamento atual: " + employee.getPaymentInfo().getMethod().getDescription());

        List<PaymentMethod> paymentMethods = paymentsController.getPaymentMethods();

        System.out.println("Lista de métodos:");

        int j = 0;
        for (PaymentMethod paymentMethod : paymentMethods) {
            System.out.println("[" + j + "] - " + paymentMethod.getDescription());
            j++;
        }

        System.out.println("\nSelecione o método:");
        String m = scanner.nextLine();

        PaymentMethod paymentMethod = paymentMethods.get(Integer.parseInt(m));

        employee.getPaymentInfo().setMethod(paymentMethod);

        employeesController.update(employee);

        System.out.println("---------------------------");

        menu();
    }

    private static void joinSyndicate() throws ParseException {
        List<Employee> employeeList = employeesController.index();

        System.out.println("Lista de Empregados:");

        int i = 0;
        for (Employee employee : employeeList) {
            System.out.println("[" + i + "] - " + employee.getName() + " - " + employee.getId());
            i++;
        }

        System.out.println("\nSelecione o empregado para sindicalizar: ");
        String n = scanner.nextLine();

        Employee employee = employeeList.get(Integer.parseInt(n));

        System.out.println("Digite a taxa mensal: ");
        Double monthlyFee = Double.valueOf(scanner.nextLine());

        syndicatesController.createAffiliate(employee.getDocumentNumber(), monthlyFee);

        System.out.println("---------------------------");

        menu();
    }

    private static void leaveSyndicate() throws ParseException {
        List<Employee> employeeList = employeesController.index();

        System.out.println("Lista de Empregados:");

        int i = 0;
        for (Employee employee : employeeList) {
            System.out.println("[" + i + "] - " + employee.getName() + " - " + employee.getId());
            i++;
        }

        System.out.println("\nSelecione o empregado para dessindicalizar: ");
        String n = scanner.nextLine();

        Employee employee = employeeList.get(Integer.parseInt(n));

        syndicatesController.removeAffiliate(employee.getDocumentNumber());

        System.out.println("---------------------------");

        menu();
    }

    // TODO Why change this?...
    private static void editEmployeeSyndicateId() {
    }

    private static void editSyndicateMonthlyFee() {

    }


    public static void runPayroll() {
        employeesController.index().forEach(employee -> {
            List<Integer> paydaysInTheMonth = employee.getPaymentInfo().getSchedule().getPaydaysInTheMonth();
            System.out.println(employee.getPaymentInfo().getSchedule() + " --- " + paydaysInTheMonth);
        });
        paymentsController.runPayroll();

    }
}

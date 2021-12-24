package com.payroll;

import com.payroll.views.EditEmployeeView;
import com.payroll.views.EmployeeView;

import java.text.ParseException;
import java.util.Scanner;

public class Menu {


    public static Scanner scanner = new Scanner(System.in);

    public static void show() throws ParseException {
        System.out.println("0. Listar todos os empregados");
        System.out.println("1. Adição de um empregado");
        System.out.println("2. Remoção de um empregado");
        System.out.println("3. Lançar um Cartão de Ponto");
        System.out.println("4. Lançar um resultado de venda");
        System.out.println("5. Lançar uma taxa de serviço");
        System.out.println("6. Alterar detalhes de um empregado");
        System.out.println("99. Sair");

        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 0:
                EmployeeView.listEmployees();
                break;
            case 1:
                EmployeeView.registerEmployee();
                break;
            case 2:
                EmployeeView.deleteEmployee();
                break;
            case 3:
                EmployeeView.registerTimeCard();
                break;
            case 4:
                EmployeeView.registerSalesResult();
                break;
            case 5:

                break;
            case 6:
                EditEmployeeView.menu();
            case 99:
                return;
            default:
                System.out.println("Opção inválida!");
                show();
        }
    }

}

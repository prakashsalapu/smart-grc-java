package app;

import repository.DepartmentRepository;
import repository.EmployeeRepository;
import service.DepartmentService;
import service.EmployeeService;

import java.util.Scanner;

import static util.MenuUtil.displayMenu;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        EmployeeRepository employeeRepository = new EmployeeRepository();
        DepartmentRepository departmentRepository = new DepartmentRepository();

        EmployeeService employeeService =
                new EmployeeService(employeeRepository, departmentRepository);

        DepartmentService departmentService =
                new DepartmentService(departmentRepository);

        boolean running = true;

        while (running) {

            displayMenu();

            System.out.print("\nEnter Choice: ");

            if (!sc.hasNextInt()) {
                System.out.println("❌ Please enter a valid number.");
                sc.nextLine();
                continue;
            }

            int menuOption = sc.nextInt();
            sc.nextLine();

            switch (menuOption) {

                // ==========================
                // Employee Management
                // ==========================

                case 1 -> employeeService.addEmployee(sc);

                case 2 -> employeeService.displayAllEmployees();

                case 3 -> {
                    System.out.print("Enter Employee ID: ");
                    int searchID = sc.nextInt();
                    sc.nextLine();
                    employeeService.searchEmployee(searchID);
                }

                case 4 -> {
                    System.out.print("Enter Employee ID: ");
                    int updateID = sc.nextInt();
                    sc.nextLine();
                    employeeService.updateEmployee(updateID, sc);
                }

                case 5 -> {
                    System.out.print("Enter Employee ID: ");
                    int deleteID = sc.nextInt();
                    sc.nextLine();
                    employeeService.deleteEmployee(deleteID);
                }

                case 6 -> employeeService.totalEmployees();

                // ==========================
                // Department Management
                // ==========================

                case 7 -> departmentService.addDepartment(sc);

                case 8 -> departmentService.displayDepartments();

                case 9 -> departmentService.updateDepartment(sc);

                case 10 -> departmentService.deleteDepartment(sc);

                // ==========================
                // Exit
                // ==========================

                case 0 -> {
                    running = false;
                    System.out.println("""
                            
                            Thank you for using SmartGRC.
                            
                            Exiting...
                            """);
                }

                default -> System.out.println("❌ Invalid Choice.");
            }
        }

        sc.close();
    }
}
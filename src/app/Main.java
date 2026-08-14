package app;

import exception.DuplicateEmployeeException;
import exception.EmployeeNotFoundException;
import repository.DepartmentRepository;
import repository.EmployeeRepository;
import service.DepartmentService;
import service.EmployeeService;

import java.util.Scanner;

import static util.MenuUtil.displayMenu;

public class Main {

    public static void main(String[] args) throws EmployeeNotFoundException, DuplicateEmployeeException {

        Scanner sc = new Scanner(System.in);

        // Repositories
        EmployeeRepository employeeRepository = new EmployeeRepository();
        DepartmentRepository departmentRepository = new DepartmentRepository();

        // Services
        EmployeeService employeeService =
                new EmployeeService(employeeRepository, departmentRepository);

        DepartmentService departmentService =
                new DepartmentService(departmentRepository);

        boolean running = true;

        while (running) {

            displayMenu();

            int menuOption = readInt(sc, "\nEnter Choice: ");

            switch (menuOption) {

                // ==========================
                // Employee Management
                // ==========================

                case 1 -> employeeService.addEmployee(sc);

                case 2 -> employeeService.displayAllEmployees();

                case 3 -> {
                    int searchID = readInt(sc, "Enter Employee ID: ");
                    employeeService.searchEmployee(searchID);
                }

                case 4 -> {
                    int updateID = readInt(sc, "Enter Employee ID: ");
                    employeeService.updateEmployee(updateID, sc);
                }

                case 5 -> {
                    int deleteID = readInt(sc, "Enter Employee ID: ");
                    employeeService.deleteEmployee(deleteID);
                }

                case 6 -> employeeService.totalEmployees();

                // ==========================
                // Department Management
                // ==========================

                case 7 -> departmentService.displayDepartments();

                case 8 -> departmentService.addDepartment(sc);

                case 9 -> departmentService.updateDepartment(sc);

                case 10 -> departmentService.deleteDepartment(sc);

                // ==========================
                // Exit
                // ==========================

                case 0 -> {
                    running = false;

                    System.out.println("""
                            
                            ========================================
                                 Thank you for using SmartGRC!
                            ========================================
                            Application closed successfully.
                            """);
                }

                default -> System.out.println(" Invalid Choice! Please try again.");
            }
        }

        sc.close();
    }

    /**
     * Reads a valid integer from the user.
     */
    private static int readInt(Scanner sc, String message) {

        while (true) {

            System.out.print(message);

            if (sc.hasNextInt()) {
                int value = sc.nextInt();
                sc.nextLine(); // Clear buffer
                return value;
            }

            System.out.println(" Invalid input. Please enter a number.");
            sc.nextLine(); // Discard invalid input
        }
    }
}
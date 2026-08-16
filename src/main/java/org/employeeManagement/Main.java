package org.employeeManagement;

import org.employeeManagement.exception.DuplicateEmployeeException;
import org.employeeManagement.exception.EmployeeNotFoundException;
import org.employeeManagement.repository.DepartmentRepository;
import org.employeeManagement.repository.EmployeeRepository;
import org.employeeManagement.service.DepartmentService;
import org.employeeManagement.service.EmployeeService;

import java.sql.SQLException;
import java.util.Scanner;

import static org.employeeManagement.util.MenuUtil.displayMenu;

public class Main {

    public static void main(String[] args)
            throws EmployeeNotFoundException,
            DuplicateEmployeeException,
            SQLException {

        Scanner sc = new Scanner(System.in);

        EmployeeRepository employeeRepository =
                new EmployeeRepository();

        DepartmentRepository departmentRepository =
                new DepartmentRepository();

        EmployeeService employeeService =
                new EmployeeService(
                        employeeRepository,
                        departmentRepository
                );

        DepartmentService departmentService =
                new DepartmentService(
                        departmentRepository
                );

        boolean running = true;

        while (running) {

            displayMenu();

            int menuOption =
                    readInt(sc, "\nEnter Choice: ");

            switch (menuOption) {

                case 1 ->
                        employeeService.addEmployee(sc);

                case 2 ->
                        employeeService.displayAllEmployees();

                case 3 -> {
                    int searchID =
                            readInt(sc, "Enter Employee ID: ");

                    employeeService.searchEmployee(searchID);
                }

                case 4 -> {
                    int updateID =
                            readInt(sc, "Enter Employee ID: ");

                    employeeService.updateEmployee(
                            updateID,
                            sc
                    );
                }

                case 5 -> {
                    int deleteID =
                            readInt(sc, "Enter Employee ID: ");

                    employeeService.deleteEmployee(deleteID);
                }

                case 6 ->
                        employeeService.totalEmployees();

                case 7 ->
                        departmentService.displayDepartments();

                case 8 ->
                        departmentService.addDepartment(sc);

                case 9 ->
                        departmentService.updateDepartment(sc);

                case 10 ->
                        departmentService.deleteDepartment(sc);

                case 0 -> {
                    running = false;

                    System.out.println("""
                            
                            ========================================
                                 Thank you for using..!
                            ========================================
                            Application closed successfully.
                            """);
                }

                default ->
                        System.out.println(
                                "Invalid Choice! Please try again."
                        );
            }
        }

        sc.close();
    }

    private static int readInt(
            Scanner sc,
            String message
    ) {

        while (true) {

            System.out.print(message);

            if (sc.hasNextInt()) {

                int value = sc.nextInt();

                sc.nextLine();

                return value;
            }

            System.out.println(
                    "Invalid input. Please enter a number."
            );

            sc.nextLine();
        }
    }
}
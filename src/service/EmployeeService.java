package service;

import exception.DuplicateEmployeeException;
import exception.EmployeeNotFoundException;
import model.Department;
import model.Employee;
import repository.DepartmentRepository;
import repository.EmployeeRepository;
import util.ValidationUtil;
import model.Employee.Status;

import java.util.InputMismatchException;
import java.util.Scanner;

public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public EmployeeService(EmployeeRepository employeeRepository,
                           DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    // Add Employees
    public void addEmployee(Scanner sc)  {

        System.out.print("Enter number of employees to add: ");

        int n;

        try {
            n = sc.nextInt();
            sc.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Invalid number.");
            sc.nextLine();
            return;
        }

        int addedCount = 0;

        for (int i = 0; i < n; i++) {

            try {

                System.out.println("\n========== Employee " + (i + 1) + " ==========");

                System.out.print("Employee ID: ");
                int empID = sc.nextInt();
                sc.nextLine();

                if (!ValidationUtil.isValidId(empID)) {
                    System.out.println("Invalid Employee ID.");
                    continue;
                }

                if (employeeRepository.existsById(empID)) {
                    throw new DuplicateEmployeeException("Employee ID already exists, return to Main..");

                }

                System.out.print("Name: ");
                String name = sc.nextLine();

                if (!ValidationUtil.isValidName(name)) {
                    System.out.println("Invalid Name.");
                    continue;
                }

                System.out.print("Email: ");
                String email = sc.nextLine();

                if (!ValidationUtil.isValidEmail(email)) {
                    System.out.println("Invalid Email.");
                    continue;
                }

                // Department Selection
                System.out.println("\nAvailable Departments:");

                for (Department dept : departmentRepository.findAll()) {
                    System.out.println(dept.getId() + ". " + dept.getName());
                }

                System.out.print("Department ID: ");

                int deptId = sc.nextInt();
                sc.nextLine();

                Department department = departmentRepository.findById(deptId);

                if (department == null) {
                    System.out.println("Invalid Department.");
                    continue;
                }

                System.out.print("Designation: ");
                String designation = sc.nextLine();

                if (!ValidationUtil.isValidDesignation(designation)) {
                    System.out.println("Invalid Designation.");
                    continue;
                }

                System.out.print("Salary: ");
                double salary = sc.nextDouble();
                sc.nextLine();

                if (!ValidationUtil.isValidSalary(salary)) {
                    System.out.println("Invalid Salary.");
                    continue;
                }

                System.out.print("Status (ACTIVE/INACTIVE): ");
                String statusInput = sc.nextLine();

                if (!ValidationUtil.isValidStatus(statusInput)) {
                    System.out.println("Invalid Status.");
                    continue;
                }

                // Convert valid String to Enum constant
                Status status = Status.valueOf(statusInput.trim().toUpperCase());

                Employee employee = new Employee(
                        empID,
                        name,
                        email,
                        department.getName(),
                        designation,
                        salary,
                        status
                );

                employeeRepository.save(employee);

                addedCount++;

                System.out.println("Employee added successfully.");

            } catch (InputMismatchException e) {
                System.out.println("Invalid input.");
                sc.nextLine();
            }
            catch (DuplicateEmployeeException e){
                System.out.println(e.getMessage());
            }
        }

        System.out.println("\nTotal Employees Added: " + addedCount);
    }

    // Display All Employees
    public void displayAllEmployees() {

        if (employeeRepository.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }

        System.out.println("\n========== Employees ==========");

        for (Employee employee : employeeRepository.findAll().values()) {
            System.out.println(employee);
        }
    }

    // Search Employee
    public void searchEmployee(int searchId) {

        Employee employee = employeeRepository.findById(searchId);

        try {
            if (employee != null) {
                System.out.println(employee);
            } else {
               throw new EmployeeNotFoundException("Employee Not Found, Check Again..");
            }
        } catch (EmployeeNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    // Update Employee
    public void updateEmployee(int empID, Scanner sc) {

        Employee employee = employeeRepository.findById(empID);

        if (employee == null) {
            System.out.println("Employee not found.");
            return;
        }

        System.out.println("""
                
                ======== Update Menu ========
                1. Name
                2. Email
                3. Department
                4. Designation
                5. Salary
                6. Status
                """);

        System.out.print("Enter Choice: ");

        int option = sc.nextInt();
        sc.nextLine();

        switch (option) {

            case 1 -> {
                System.out.print("New Name: ");
                String name = sc.nextLine();

                if (ValidationUtil.isValidName(name)) {
                    employee.setEmpName(name);
                    System.out.println("Updated.");
                } else {
                    System.out.println("Invalid Name.");
                }
            }

            case 2 -> {
                System.out.print("New Email: ");
                String email = sc.nextLine();

                if (ValidationUtil.isValidEmail(email)) {
                    employee.setEmail(email);
                    System.out.println("Updated.");
                } else {
                    System.out.println("Invalid Email.");
                }
            }

            case 3 -> {

                System.out.println("\nAvailable Departments:");

                for (Department dept : departmentRepository.findAll()) {
                    System.out.println(dept.getId() + ". " + dept.getName());
                }

                System.out.print("Department ID: ");

                int deptId = sc.nextInt();
                sc.nextLine();

                Department department = departmentRepository.findById(deptId);

                if (department != null) {
                    employee.setDepartment(department.getName());
                    System.out.println("Updated.");
                } else {
                    System.out.println("Invalid Department.");
                }
            }

            case 4 -> {
                System.out.print("New Designation: ");
                String designation = sc.nextLine();

                if (ValidationUtil.isValidDesignation(designation)) {
                    employee.setDesignation(designation);
                    System.out.println("Updated.");
                } else {
                    System.out.println("Invalid Designation.");
                }
            }

            case 5 -> {
                System.out.print("New Salary: ");
                double salary = sc.nextDouble();
                sc.nextLine();

                if (ValidationUtil.isValidSalary(salary)) {
                    employee.setSalary(salary);
                    System.out.println("Updated.");
                } else {
                    System.out.println("Invalid Salary.");
                }
            }

            case 6 -> {
                System.out.print("Status (ACTIVE/INACTIVE): ");
                String statusInput = sc.nextLine();

                if (!ValidationUtil.isValidStatus(statusInput)) {
                    System.out.println("Invalid Status.");
                }

                // Convert valid String to Enum constant
                Status status = Status.valueOf(statusInput.trim().toUpperCase());
            }

            default -> System.out.println("Invalid Choice.");
        }
    }

    // Delete Employee
    public void deleteEmployee(int targetId) {

        Employee removed = employeeRepository.deleteById(targetId);

        if (removed != null) {
            System.out.println("Employee deleted successfully.");
        } else {
            System.out.println("Employee not found.");
        }
    }

    // Total Employees
    public void totalEmployees() {
        System.out.println("\nTotal Employees: " + employeeRepository.count());
    }
}
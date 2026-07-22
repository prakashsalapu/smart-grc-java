package service;
import model.Employee;

import java.util.ArrayList;
import java.util.Scanner;


public class EmployeeService {

    // ArrayList Database to store employees
    private ArrayList<Employee> employees = new ArrayList<>();

    // Add Employees to Database
    public void addEmployee(Scanner sc){

        System.out.print("Enter number of employees to add: ");
        int n = sc.nextInt();
        sc.nextLine();

        for(int i=0; i<n; i++){

            System.out.println("\nEnter the  Details of Employee "+ (i+1));
            System.out.print("Employee ID: ");
            int empID = sc.nextInt();
            sc.nextLine();

            System.out.print("Name: ");
            String name = sc.nextLine();

            System.out.print("Email: ");
            String email = sc.nextLine();

            System.out.print("Department: ");
            String department = sc.nextLine();

            System.out.print("Designation: ");
            String designation = sc.nextLine();

            System.out.print("Salary: ");
            double salary = sc.nextDouble();
            sc.nextLine();

            System.out.print("Status (ACTIVE/INACTIVE): ");
            String status = sc.nextLine();

            // Pass values to constructor (or setters)
            try {
                Employee employee = new Employee(empID, name, email, department, designation, salary, status);
                System.out.println("Employee added successfully!");
            } catch (IllegalArgumentException e) {
                System.out.println("Error adding employee: " + e.getMessage());
            }

        }

    }

    // Display all Employees
    public void displayAllEmployees(){
        if(employees.isEmpty()){
            System.out.println("No employees found!");
        }
        for(Employee employee : employees){
            System.out.println(employee);
        }
    }

    // Find Single Employee
    public void searchEmployee(int searchId){
        boolean found = false;
        for(Employee employee : employees){
            if(employee.getEmpID() == searchId){
                System.out.println(employee);
                found = true;
            }
        }
        if(!found){
            System.out.println("\nEmployee details not found!");
        }
    }

    // Update Employee Details
    public void updateEmployee(int empID, Scanner sc){
        boolean found = false;
        for(Employee employee : employees){
            if(employee.getEmpID() == empID){

                found = true;

                System.out.println("\n========= Update Menu =========");
                System.out.println("1. Name\n" +
                        "2. Email\n" +
                        "3. Department\n" +
                        "4. Designation\n" +
                        "5. Salary\n" +
                        "6. Status\n" +
                        "===============================");
                System.out.print("\nEnter Choice: ");
                int option = sc.nextInt();
                sc.nextLine();
                switch (option){
                    case 1:
                        System.out.print("Enter new Name: ");
                        String newName = sc.nextLine();
                        employee.setEmpName(newName);
                        System.out.println("Name updated successfully.");
                        break;

                    case 2:
                        System.out.print("Enter new Email (hellox@example.com): ");
                        String newEmail = sc.nextLine();
                        employee.setEmail(newEmail);
                        System.out.println("Email updated successfully.");
                        break;

                    case 3:
                        System.out.print("Enter new Department: ");
                        String newDept = sc.nextLine();
                        employee.setDepartment(newDept);
                        System.out.println("Department updated successfully.");
                        break;

                    case 4:
                        System.out.print("Enter new Designation: ");
                        String newDesignation = sc.nextLine();
                        employee.setDesignation(newDesignation);
                        System.out.println("Designation updated successfully.");
                        break;

                    case 5:
                        System.out.print("Enter new Salary: ");
                        double newSalary = sc.nextDouble();
                        sc.nextLine();
                        employee.setSalary(newSalary);
                        System.out.println("Salary updated successfully.");
                        break;

                    case 6:
                        System.out.print("Enter new Status (ACTIVE/INACTIVE): ");
                        String newStatus = sc.nextLine();
                        employee.setStatus(newStatus);
                        System.out.println("Status updated successfully.");
                        break;

                    default:
                        System.out.println("Invalid Input");

                }
                break;
            }
        }
        if(!found){
            System.out.println("\nEmployee details not found!");
        }

    }

    // Delete Employee
    public void deleteEmployee(int targetId){

        boolean wasRemoved = employees.removeIf(employee -> employee.getEmpID() == targetId);

        if (wasRemoved) {
            System.out.println("\nEmployee with ID " + targetId + " was removed.");
        } else {
            System.out.println("\nEmployee with ID " + targetId + " not found.");
        }

    }

    // Display MENU
    public void displayMenu(){
        System.out.println("\n========= SmartGRC =========");
        System.out.println("1. Add Employee");
        System.out.println("2. Display Employees");
        System.out.println("3. Search Employee");
        System.out.println("4. Update Employee");
        System.out.println("5. Delete Employee");
        System.out.println("6. Exit");
        System.out.println("=============================");
    }
}



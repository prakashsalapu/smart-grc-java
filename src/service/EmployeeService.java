package service;
import model.Employee;
import util.ValidationUtil;

import repository.EmployeeRepository;
import java.util.InputMismatchException;
import java.util.Scanner;


public class EmployeeService {

    private EmployeeRepository repository =  new EmployeeRepository();

    // Add Employees to Database
    public void addEmployee(Scanner sc) {
        System.out.print("Enter number of employees to add: ");

        int n;
        try {
            n = sc.nextInt();
            sc.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Invalid number format! Returning to main menu.");
            sc.nextLine();
            return;
        }

        int addedCount = 0;

        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter Details for Employee " + (i + 1));

            try {
                System.out.print("Employee ID: ");
                int empID = sc.nextInt();
                sc.nextLine();
                if(!ValidationUtil.isValidId(empID)){
                    System.out.println("Invalid Employee Id.");
                    continue;
                }

                /* check whether the ID already exists or not. */
                if (repository.existsById(empID)) {
                    System.out.println("Error: Employee ID " + empID + " already exists. Skipping...");
                    continue;
                }

                System.out.print("Name: ");
                String name = sc.nextLine();
                if(!ValidationUtil.isValidName(name)){
                    System.out.println("Invalid Employee Name.");
                    continue;
                }

                System.out.print("Email: ");
                String email = sc.nextLine();
                if(!ValidationUtil.isValidEmail(email)){
                    System.out.println("Invalid Email.");
                    continue;
                }

                System.out.print("Department: ");
                String department = sc.nextLine();
                if(!ValidationUtil.isValidDepartment(department)){
                    System.out.println("Invalid Department.");
                    continue;
                }

                System.out.print("Designation: ");
                String designation = sc.nextLine();
                if(!ValidationUtil.isValidDesignation(designation)){
                    System.out.println("Invalid Designation.");
                    continue;
                }

                System.out.print("Salary: ");
                double salary = sc.nextDouble();
                sc.nextLine();
                if(!ValidationUtil.isValidSalary(salary)){
                    System.out.println("Invalid Salary.");
                    continue;
                }

                System.out.print("Status (ACTIVE/INACTIVE): ");
                String status = sc.nextLine();
                if (!ValidationUtil.isValidStatus(status)) {
                    System.out.println("Invalid Status! Must be 'ACTIVE' or 'INACTIVE'. Skipping this entry...");
                    continue;
                }


                Employee employee = new Employee(empID, name, email, department, designation, salary, status);
                repository.save(employee);
                addedCount++;
                System.out.println("Employee (ID: " + empID + ") added successfully!");

            } catch (InputMismatchException e) {
                System.out.println("Invalid input type entered! Skipping this entry.");
                sc.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println("Error adding employee: " + e.getMessage());
            }
        }

        System.out.println("\nTotal " + addedCount + " employee(s) added successfully.");
    }

    // Display all Employees
    public void displayAllEmployees(){

        if(repository.isEmpty()){
            System.out.println("No employees found!");
            return;
        }
        System.out.println("\n========= Employees Data =========");
        for(Employee employee : repository.findAll().values()){
            System.out.println(employee);
        }
    }

    // Search Employee
    public void searchEmployee(int searchId){
       Employee employee = repository.findById(searchId);

       if(employee != null){
           System.out.println(employee);
       }else{
           System.out.println("\nEmployee details not found!");
       }
    }

    // Update Employee Details
    public void updateEmployee(int empID, Scanner sc){

            Employee employee = repository.findById(empID);
            if(employee == null) {
                System.out.println("\nEmployee details not found!");
                return;
            }

            System.out.println("\n========= Update Menu =========");
            System.out.println("""
                    1. Name
                    2. Email
                    3. Department
                    4. Designation
                    5. Salary
                    6. Status
                    ===============================""");
            System.out.print("\nEnter Choice: ");

            int option = sc.nextInt();
            sc.nextLine();

            switch (option){
                case 1:
                    System.out.print("Enter new Name: ");
                    String newName = sc.nextLine();
                    if(!ValidationUtil.isValidName(newName)){
                        System.out.println("Invalid Employee Name.");
                    }
                    employee.setEmpName(newName);
                    System.out.println("Name updated successfully.");
                    break;

                case 2:
                    System.out.print("Enter new Email (hellox@example.com): ");
                    String newEmail = sc.nextLine();
                    if(!ValidationUtil.isValidEmail(newEmail)){
                        System.out.println("Invalid Email.");
                    }
                    employee.setEmail(newEmail);
                    System.out.println("Email updated successfully.");
                    break;

                case 3:
                    System.out.print("Enter new Department: ");
                    String newDept = sc.nextLine();
                    if(!ValidationUtil.isValidDepartment(newDept)){
                        System.out.println("Invalid Department.");
                    }
                    employee.setDepartment(newDept);
                    System.out.println("Department updated successfully.");
                    break;

                case 4:
                    System.out.print("Enter new Designation: ");
                    String newDesignation = sc.nextLine();
                    if(!ValidationUtil.isValidDesignation(newDesignation)){
                        System.out.println("Invalid Designation.");
                    }
                    employee.setDesignation(newDesignation);
                    System.out.println("Designation updated successfully.");
                    break;

                case 5:
                    System.out.print("Enter new Salary: ");
                    double newSalary = sc.nextDouble();
                    sc.nextLine();
                    if(!ValidationUtil.isValidSalary(newSalary)){
                        System.out.println("Invalid Salary.");
                    }
                    employee.setSalary(newSalary);
                    System.out.println("Salary updated successfully.");
                    break;

                case 6:
                    System.out.print("Enter new Status (ACTIVE/INACTIVE): ");
                    String newStatus = sc.nextLine();
                    if (!ValidationUtil.isValidStatus(newStatus)) {
                        System.out.println("Invalid Status! Must be 'ACTIVE' or 'INACTIVE'. Skipping this entry...");
                    }
                    employee.setStatus(newStatus);
                    System.out.println("Status updated successfully.");
                    break;

                default:
                    System.out.println("Invalid Input");

            }
        }

    // Delete Employee
    public void deleteEmployee(int targetId){
        Employee removed = repository.deleteById(targetId);

        if(removed != null){
            System.out.println("\nEmployee with ID " + targetId + " was removed.");
        }else {
            System.out.println("\nEmployee with ID " + targetId + " not found.");
        }
    }

    // Display Toatal Employees
    public void totalEmployees(){
        System.out.println("\nTotal Employee(s): " + repository.count());
    }


}



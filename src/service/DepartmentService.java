package service;

import model.Department;
import repository.DepartmentRepository;
import util.ValidationUtil;

import java.util.List;
import java.util.Scanner;

public class DepartmentService {

    private final DepartmentRepository repository;

    public DepartmentService(DepartmentRepository repository) {
        this.repository = repository;
    }

    // Display all departments
    public void displayDepartments() {
        List<Department> departments = repository.findAll();

        if (departments.isEmpty()) {
            System.out.println("No departments available.");
            return;
        }

        System.out.println("\n========== Departments ==========");
        for (Department dept : departments) {
            System.out.println(dept);
        }
    }

    // Select department while adding employee
    public Department selectDepartment(Scanner sc) {

        List<Department> departments = repository.findAll();

        while (true) {

            System.out.println("\nSelect Department");

            for (Department dept : departments) {
                System.out.println(dept.getId() + ". " + dept.getName());
            }

            System.out.print("Choice : ");

            if (!sc.hasNextInt()) {
                System.out.println("Invalid input.");
                sc.nextLine();
                continue;
            }

            int choice = sc.nextInt();
            sc.nextLine();

            Department department = repository.findById(choice);

            if (department != null) {
                return department;
            }

            System.out.println("Department not found.");
        }
    }

    // Add Department
    public void addDepartment(Scanner sc) {

        System.out.print("Department Name : ");
        String name = sc.nextLine().trim();

        if (!ValidationUtil.isValidDepartment(name)) {
            System.out.println("Invalid Department Name.");
            return;
        }

        Department dept = repository.save(name);

        System.out.println("Department Added Successfully.");
        System.out.println(dept);
    }

    // Update Department
    public void updateDepartment(Scanner sc) {

        displayDepartments();

        System.out.print("Enter Department ID : ");

        if (!sc.hasNextInt()) {
            System.out.println("Invalid ID.");
            sc.nextLine();
            return;
        }

        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("New Department Name : ");
        String newName = sc.nextLine().trim();

        if (!ValidationUtil.isValidDepartment(newName)) {
            System.out.println("Invalid Department Name.");
            return;
        }

        if (repository.update(id, newName)) {
            System.out.println("Department Updated Successfully.");
        } else {
            System.out.println("Department Not Found.");
        }
    }

    // Delete Department
    public void deleteDepartment(Scanner sc) {

        displayDepartments();

        System.out.print("Enter Department ID : ");

        if (!sc.hasNextInt()) {
            System.out.println("Invalid ID.");
            sc.nextLine();
            return;
        }

        int id = sc.nextInt();
        sc.nextLine();

        if (repository.delete(id)) {
            System.out.println("Department Deleted Successfully.");
        } else {
            System.out.println("Department Not Found.");
        }
    }
}
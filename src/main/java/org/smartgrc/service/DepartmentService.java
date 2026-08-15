package org.smartgrc.service;

import org.smartgrc.exception.DepartmentNotFoundException;
import org.smartgrc.exception.DuplicateDepartmentException;
import org.smartgrc.model.Department;
import org.smartgrc.repository.DepartmentRepository;
import org.smartgrc.util.ValidationUtil;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class DepartmentService {

    private final DepartmentRepository repository;

    public DepartmentService(DepartmentRepository repository) {
        this.repository = repository;
    }

    // Display Departments
    public void displayDepartments() throws SQLException {

        List<Department> departments = repository.findAll();

        if (departments.isEmpty()) {
            System.out.println("\nNo departments found.");
            return;
        }

        System.out.println("\n========== Departments ==========");

        for (Department department : departments) {
            System.out.println(department);
        }
    }

    // Select Department
    public Department selectDepartment(Scanner sc) throws SQLException {

        while (true) {

            displayDepartments();

            System.out.print("\nEnter Department ID: ");

            if (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine();
                continue;
            }

            int departmentId = sc.nextInt();
            sc.nextLine();

            try {

                Department department =
                        repository.findById(departmentId);

                if (department == null) {
                    throw new DepartmentNotFoundException(
                            "Department with ID " + departmentId + " not found."
                    );
                }

                return department;

            } catch (DepartmentNotFoundException e) {

                System.out.println(e.getMessage());
            }
        }
    }

    // Add Department
    public void addDepartment(Scanner sc) {

        System.out.print("\nEnter Department Name: ");

        String name = sc.nextLine().trim();

        if (!ValidationUtil.isValidDepartment(name)) {
            System.out.println("Invalid Department Name.");
            return;
        }

        try {

            for (Department department : repository.findAll()) {

                if (department.getName().equalsIgnoreCase(name)) {

                    throw new DuplicateDepartmentException(
                            "Department '" + name + "' already exists."
                    );
                }
            }

            Department department = repository.save(name);

            System.out.println(
                    "Department added successfully: " + department
            );

        } catch (DuplicateDepartmentException e) {

            System.out.println(e.getMessage());

        } catch (IOException e) {

            System.out.println(
                    "Failed to save department data: "
                            + e.getMessage()
            );
        }
    }

    // Update Department
    public void updateDepartment(Scanner sc) {

        displayDepartments();

        System.out.print("\nEnter Department ID to update: ");

        if (!sc.hasNextInt()) {
            System.out.println("Invalid Department ID.");
            sc.nextLine();
            return;
        }

        int id = sc.nextInt();
        sc.nextLine();

        try {

            Department department = repository.findById(id);

            if (department == null) {

                throw new DepartmentNotFoundException(
                        "Department with ID " + id + " not found."
                );
            }

            System.out.print("Enter new Department Name: ");

            String newName = sc.nextLine().trim();

            if (!ValidationUtil.isValidDepartment(newName)) {
                System.out.println("Invalid Department Name.");
                return;
            }

            for (Department existing : repository.findAll()) {

                if (existing.getId() != id &&
                        existing.getName().equalsIgnoreCase(newName)) {

                    throw new DuplicateDepartmentException(
                            "Department '" + newName + "' already exists."
                    );
                }
            }

            repository.update(id, newName);

            System.out.println(
                    "Department updated successfully."
            );

        } catch (DepartmentNotFoundException e) {

            System.out.println(e.getMessage());

        } catch (DuplicateDepartmentException e) {

            System.out.println(e.getMessage());

        } catch (IOException e) {

            System.out.println(
                    "Failed to update department data: "
                            + e.getMessage()
            );
        }
    }

    // Delete Department
    public void deleteDepartment(Scanner sc) {

        displayDepartments();

        System.out.print("\nEnter Department ID to delete: ");

        if (!sc.hasNextInt()) {
            System.out.println("Invalid Department ID.");
            sc.nextLine();
            return;
        }

        int id = sc.nextInt();
        sc.nextLine();

        try {

            Department department = repository.findById(id);

            if (department == null) {

                throw new DepartmentNotFoundException(
                        "Department with ID " + id + " not found."
                );
            }

            repository.delete(id);

            System.out.println(
                    "Department '" +
                            department.getName() +
                            "' deleted successfully."
            );

        } catch (DepartmentNotFoundException e) {

            System.out.println(e.getMessage());

        } catch (IOException e) {

            System.out.println(
                    "Failed to delete department data: "
                            + e.getMessage()
            );
        }
    }
}
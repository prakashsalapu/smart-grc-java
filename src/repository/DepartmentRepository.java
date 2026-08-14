package repository;

import model.Department;

import java.io.*;
import java.util.*;

public class DepartmentRepository {

    private final Map<Integer, Department> departmentMap =
            new LinkedHashMap<>();

    private static final String FILE_PATH = "data/departments.txt";

    private int nextId = 1;

    // Constructor
    public DepartmentRepository() {
        loadFromFile();
    }

    // =========================================================
    // Find All Departments
    // =========================================================

    public List<Department> findAll() {
        return new ArrayList<>(departmentMap.values());
    }

    // =========================================================
    // Find Department By ID
    // =========================================================

    public Department findById(int id) {
        return departmentMap.get(id);
    }

    // =========================================================
    // Save Department
    // =========================================================

    public Department save(String name) throws IOException {

        int id = nextId++;

        Department newDept = new Department(id, name);

        departmentMap.put(id, newDept);

        saveToFile();

        return newDept;
    }

    // =========================================================
    // Update Department
    // =========================================================

    public boolean update(int id, String newName) throws IOException {

        Department dept = departmentMap.get(id);

        if (dept != null) {

            dept.setName(newName);

            saveToFile();

            return true;
        }

        return false;
    }

    // =========================================================
    // Delete Department
    // =========================================================

    public boolean delete(int id) throws IOException {

        Department removed = departmentMap.remove(id);

        if (removed != null) {
            saveToFile();
            return true;
        }

        return false;
    }

    // =========================================================
    // Load Departments From File
    // =========================================================

    private void loadFromFile() {

        File file = new File(FILE_PATH);

        // First run — file doesn't exist
        if (!file.exists()) {

            // Create default departments
            departmentMap.put(1, new Department(1, "IT"));
            departmentMap.put(2, new Department(2, "HR"));
            departmentMap.put(3, new Department(3, "Finance"));
            departmentMap.put(4, new Department(4, "Security"));
            departmentMap.put(5, new Department(5, "Compliance"));

            nextId = 6;

            try {
                saveToFile();
            } catch (IOException e) {
                System.out.println(
                        "Unable to create department file: "
                                + e.getMessage()
                );
            }

            return;
        }

        // Load existing departments
        try (BufferedReader reader =
                     new BufferedReader(new FileReader(file))) {

            String line;
            int maxId = 0;

            while ((line = reader.readLine()) != null) {

                if (line.isBlank()) {
                    continue;
                }

                String[] data = line.split("\\|", 2);

                int id = Integer.parseInt(data[0]);
                String name = data[1];

                Department department =
                        new Department(id, name);

                departmentMap.put(id, department);

                if (id > maxId) {
                    maxId = id;
                }
            }

            nextId = maxId + 1;

        } catch (IOException e) {

            System.out.println(
                    "Unable to load department data: "
                            + e.getMessage()
            );

        } catch (NumberFormatException e) {

            System.out.println(
                    "Invalid department data found in file."
            );
        }
    }

    // =========================================================
    // Save Departments To File
    // =========================================================

    private void saveToFile() throws IOException {

        File file = new File(FILE_PATH);

        // Create data directory if necessary
        File parentDirectory = file.getParentFile();

        if (parentDirectory != null &&
                !parentDirectory.exists()) {

            parentDirectory.mkdirs();
        }

        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(file))) {

            for (Department department : departmentMap.values()) {

                writer.write(
                        department.getId() + "|" +
                                department.getName()
                );

                writer.newLine();
            }
        }
    }
}
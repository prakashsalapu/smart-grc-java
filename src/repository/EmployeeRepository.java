package repository;

import model.Employee;

import java.io.*;
import java.util.HashMap;

public class EmployeeRepository {

    private final HashMap<Integer, Employee> employees = new HashMap<>();


    private static final String FILE_PATH="data/employees.txt";
    public EmployeeRepository(){
        loadFromFile();
    }


    public void save(Employee employee) throws IOException {
        employees.put(employee.getEmpID(), employee);
        saveToFile();
    }

    public Employee findById(int id) {
        return employees.get(id);
    }

    public Employee deleteById(int id) {
        return employees.remove(id);
    }

    public boolean existsById(int id) {
        return employees.containsKey(id);
    }

    public boolean isEmpty() {
        return employees.isEmpty();
    }

    public HashMap<Integer, Employee> findAll() {
        return employees;
    }

    public int count() {
        return employees.size();
    }

    // Load Employees Data using File Handling
    private void loadFromFile() {

        File file = new File(FILE_PATH);

        // First run: file doesn't exist
        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split("\\|");

                int empID = Integer.parseInt(data[0]);
                String name = data[1];
                String email = data[2];
                String department = data[3];
                String designation = data[4];
                double salary = Double.parseDouble(data[5]);
                Employee.Status status = Employee.Status.valueOf(data[6]);

                Employee employee = new Employee(
                        empID,
                        name,
                        email,
                        department,
                        designation,
                        salary,
                        status
                );

                employees.put(empID, employee);
            }

        } catch (IOException e) {

            System.out.println(
                    "Unable to load employee data: "
                            + e.getMessage()
            );

        } catch (NumberFormatException e) {

            System.out.println(
                    "Invalid employee data found in file."
            );
        }
    }

    // Save Employees To File
    private void saveToFile() throws IOException {

        File file = new File(FILE_PATH);

        // Create data directory if it doesn't exist
        File parentDirectory = file.getParentFile();

        if (parentDirectory != null && !parentDirectory.exists()) {
            parentDirectory.mkdirs();
        }

        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(file))) {

            for (Employee employee : employees.values()) {

                writer.write(
                        employee.getEmpID() + "|" +
                                employee.getEmpName() + "|" +
                                employee.getEmail() + "|" +
                                employee.getDepartment() + "|" +
                                employee.getDesignation() + "|" +
                                employee.getSalary() + "|" +
                                employee.getStatus()
                );

                writer.newLine();
            }
        }
    }
}
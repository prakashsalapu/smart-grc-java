package repository;

import model.Department;
import java.util.*;

public class DepartmentRepository {
    private final Map<Integer, Department> departmentMap = new LinkedHashMap<>();
    private int nextId = 6; // Next available ID for custom additions

    public DepartmentRepository() {
        // Pre-populate predefined departments
        departmentMap.put(1, new Department(1, "IT"));
        departmentMap.put(2, new Department(2, "HR"));
        departmentMap.put(3, new Department(3, "Finance"));
        departmentMap.put(4, new Department(4, "Security"));
        departmentMap.put(5, new Department(5, "Compliance"));
    }

    // List all departments
    public List<Department> findAll() {
        return new ArrayList<>(departmentMap.values());
    }

    // Find department by ID
    public Department findById(int id) {
        return departmentMap.get(id);
    }

    // Add new department
    public Department save(String name) {
        Department newDept = new Department(nextId, name);
        departmentMap.put(nextId, newDept);
        nextId++;
        return newDept;
    }

    // Update existing department
    public boolean update(int id, String newName) {
        Department dept = departmentMap.get(id);
        if (dept != null) {
            dept.setName(newName);
            return true;
        }
        return false;
    }

    // Delete department
    public boolean delete(int id) {
        return departmentMap.remove(id) != null;
    }
}
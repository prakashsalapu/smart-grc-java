package repository;

import model.Employee;
import java.util.HashMap;

public class EmployeeRepository {

    private HashMap<Integer, Employee> employees = new HashMap<>();

    public void save(Employee employee) {
        employees.put(employee.getEmpID(), employee);
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
}
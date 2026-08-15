package org.smartgrc.repository;

import org.smartgrc.model.Employee;
import org.smartgrc.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class EmployeeRepository {

    public void save(Employee employee) throws SQLException {

        String sql = """
            INSERT INTO employees
            (id, name, email, department_id, designation, salary, status)
            VALUES (?, ?, ?, ?, ?, ?, ?)
            """;

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(1, employee.getEmpID());
            statement.setString(2, employee.getEmpName());
            statement.setString(3, employee.getEmail());
            statement.setInt(4, employee.getDepartment());
            statement.setString(5, employee.getDesignation());
            statement.setDouble(6, employee.getSalary());
            statement.setString(7, employee.getStatus().name());

            statement.executeUpdate();
        }
    }
    public Employee findById(int id) throws SQLException {

        String sql = """
                SELECT *
                FROM employees
                WHERE id = ?
                """;

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    return mapResultSetToEmployee(resultSet);
                }
            }
        }

        return null;
    }

    public Employee deleteById(int id) throws SQLException {

        Employee employee = findById(id);

        if (employee == null) {
            return null;
        }

        String sql = """
                DELETE FROM employees
                WHERE id = ?
                """;

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(1, id);
            statement.executeUpdate();
        }

        return employee;
    }

    public boolean existsById(int id) throws SQLException {

        String sql = """
                SELECT 1
                FROM employees
                WHERE id = ?
                """;

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        }
    }

    public boolean isEmpty() throws SQLException {
        return count() == 0;
    }

    public HashMap<Integer, Employee> findAll() throws SQLException {

        HashMap<Integer, Employee> employees = new HashMap<>();

        String sql = "SELECT * FROM employees";

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()
        ) {

            while (resultSet.next()) {

                Employee employee =
                        mapResultSetToEmployee(resultSet);

                employees.put(
                        employee.getEmpID(),
                        employee
                );
            }
        }

        return employees;
    }

    public int count() throws SQLException {

        String sql = "SELECT COUNT(*) FROM employees";

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()
        ) {

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        }

        return 0;
    }

    public boolean update(Employee employee) throws SQLException {

        String sql = """
            UPDATE employees
            SET name = ?,
                email = ?,
                department_id = ?,
                designation = ?,
                salary = ?,
                status = ?
            WHERE id = ?
            """;

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, employee.getEmpName());
            statement.setString(2, employee.getEmail());
            statement.setInt(3, employee.getDepartment());
            statement.setString(4, employee.getDesignation());
            statement.setDouble(5, employee.getSalary());
            statement.setString(6, employee.getStatus().name());
            statement.setInt(7, employee.getEmpID());

            return statement.executeUpdate() > 0;
        }
    }
    private Employee mapResultSetToEmployee(
            ResultSet resultSet
    ) throws SQLException {

        Employee.Status status =
                Employee.Status.valueOf(
                        resultSet.getString("status")
                );

        return new Employee(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("email"),
                resultSet.getInt("department_id"),
                resultSet.getString("designation"),
                resultSet.getDouble("salary"),
                status
        );
    }
}
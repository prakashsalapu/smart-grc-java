package org.smartgrc.repository;

import org.smartgrc.model.Department;
import org.smartgrc.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentRepository {

    public List<Department> findAll() throws SQLException {

        List<Department> departments = new ArrayList<>();

        String sql = "SELECT * FROM departments";

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()
        ) {

            while (resultSet.next()) {

                Department department =
                        mapResultSetToDepartment(resultSet);

                departments.add(department);
            }
        }

        return departments;
    }

    public Department findById(int id) throws SQLException {

        String sql = """
                SELECT *
                FROM departments
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
                    return mapResultSetToDepartment(resultSet);
                }
            }
        }

        return null;
    }

    public Department save(String name) throws SQLException {

        String sql = """
                INSERT INTO departments (name)
                VALUES (?)
                """;

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(
                                sql,
                                PreparedStatement.RETURN_GENERATED_KEYS
                        )
        ) {

            statement.setString(1, name);

            statement.executeUpdate();

            try (ResultSet generatedKeys =
                         statement.getGeneratedKeys()) {

                if (generatedKeys.next()) {

                    int id = generatedKeys.getInt(1);

                    return new Department(id, name);
                }
            }
        }

        return null;
    }

    public boolean update(int id, String newName)
            throws SQLException {

        String sql = """
                UPDATE departments
                SET name = ?
                WHERE id = ?
                """;

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, newName);
            statement.setInt(2, id);

            return statement.executeUpdate() > 0;
        }
    }

    public boolean delete(int id) throws SQLException {

        String sql = """
                DELETE FROM departments
                WHERE id = ?
                """;

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(1, id);

            return statement.executeUpdate() > 0;
        }
    }

    private Department mapResultSetToDepartment(
            ResultSet resultSet
    ) throws SQLException {

        return new Department(
                resultSet.getInt("id"),
                resultSet.getString("name")
        );
    }
}
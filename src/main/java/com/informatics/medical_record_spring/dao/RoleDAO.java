package com.informatics.medical_record_spring.dao;

import com.informatics.medical_record_spring.model.Role;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RoleDAO {
    private final String jdbcUrl = "jdbc:mysql://localhost:3306/medical_record";
    private final String jdbcUsername = "root";
    private final String jdbcPassword = "root";

    public List<Role> getAllRoles() {
        List<Role> roles = new ArrayList<>();
        String query = "SELECT * FROM roles";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Role role = new Role();
                role.setRoleid(resultSet.getInt("role_id"));
                role.setRolename(resultSet.getString("role_name"));
                role.setPermissions(resultSet.getString("permissions"));
                roles.add(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roles;
    }

    public Role getRoleById(int id) {
        String query = "SELECT * FROM roles WHERE role_id = ?";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Role role = new Role();
                    role.setRoleid(resultSet.getInt("role_id"));
                    role.setRolename(resultSet.getString("role_name"));
                    role.setPermissions(resultSet.getString("permissions"));
                    return role;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void createRole(Role role) {
        String query = "INSERT INTO roles (role_name, permissions) VALUES (?, ?)";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, role.getRolename());
            preparedStatement.setString(2, role.getPermissions());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateRole(int id, Role role) {
        String query = "UPDATE roles SET role_name = ?, permissions = ? WHERE role_id = ?";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, role.getRolename());
            preparedStatement.setString(2, role.getPermissions());
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteRole(int id) {
        String query = "DELETE FROM roles WHERE role_id = ?";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

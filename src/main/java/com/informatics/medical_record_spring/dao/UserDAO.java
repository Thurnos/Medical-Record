package com.informatics.medical_record_spring.dao;

import com.informatics.medical_record_spring.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Repository
public class UserDAO {

    private final String jdbcUrl = "jdbc:mysql://localhost:3306/medical_record";
    private final String jdbcUsername = "root";
    private final String jdbcPassword = "root";
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Create user
    // Create user
    public int createUser(User user) {
        String sql = "INSERT INTO users (username, email, password_hash, role_id) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, user.getName(), user.getEmail(), user.getPasswordHash(), user.getRole().getRoleid());
    }


    // Update user
    public int updateUser(User user) {
        String sql = "UPDATE users SET username = ?, email = ?, password_hash = ?, role_id = ? WHERE user_id = ?";
        return jdbcTemplate.update(sql, user.getName(), user.getEmail(), user.getPasswordHash(), user.getRole().getRoleid(), user.getId());
    }

    // Delete user
    public int deleteUser(int id) {
        String sql = "DELETE FROM users WHERE user_id = ?";
        return jdbcTemplate.update(sql, id);
    }

}

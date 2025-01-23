package com.informatics.medical_record_spring.dao;

import com.informatics.medical_record_spring.model.Doctors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class DoctorDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private final String jdbcUrl = "jdbc:mysql://localhost:3306/medical_record";
    private final String jdbcUsername = "root";
    private final String jdbcPassword = "root";

    // Create
    public void createDoctor(Doctors doctor) {
        String sql = "INSERT INTO doctors (user_id, name, specialty, is_personal_doctor, specialty_id) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, doctor.getUser().getId(), doctor.getName(), doctor.getSpecialty(), doctor.getPersonalDoctor(), doctor.getSpecialty_id());
    }


    // Delete
    public void deleteDoctor(int doctorId) {
        String sql = "DELETE FROM doctors WHERE doctor_id = ?";
        jdbcTemplate.update(sql, doctorId);
    }

    // Get all doctors
//    public List<Doctors> getAllDoctors() {
//        String sql = "SELECT * FROM doctors";
//        return jdbcTemplate.query(sql, (rs, rowNum) -> {
//            Doctors doctor = new Doctors();
//            doctor.setId(rs.getInt("doctor_id"));
//            doctor.setUser((rs.getInt("user_id")));
//            doctor.setName(rs.getString("name"));
//            doctor.setSpecialty(rs.getString("specialty"));
//            doctor.setPersonalDoctor(rs.getBoolean("is_personal_doctor"));
//            return doctor;
//        });
//    }




    }


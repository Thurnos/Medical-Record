package com.informatics.medical_record_spring.dao;


import org.springframework.stereotype.Repository;

@Repository
public class PatientDAO {

    private final String jdbcUrl = "jdbc:mysql://localhost:3306/medical_record";
    private final String jdbcUsername = "root";
    private final String jdbcPassword = "root";

}

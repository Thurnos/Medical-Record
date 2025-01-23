package com.informatics.medical_record_spring.repository;

import com.informatics.medical_record_spring.model.Patients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientsRepository extends JpaRepository<Patients,Integer> {

    @Query("SELECT p.name AS patientName, d.name AS doctorName " +
            "FROM Patients p " +
            "JOIN Doctors d ON p.personalDoctor.id = d.id " +
            "WHERE d.name = :doctorName")
    List<Object[]> findPatientsByDoctorName(@Param("doctorName") String doctorName);

//    @Query("SELECT d.name AS doctorName, COUNT(p.id) AS patientCount " +
//            "FROM Doctors d LEFT JOIN Patients p ON d.id = p.personalDoctor.id " +
//            "GROUP BY d.name")
//    List<Object[]> findDoctorPatientCounts();


    // Query to check if the patient is insured
    @Query("SELECT p.isInsured FROM Patients p WHERE p.patientId = :patientId")
    Boolean isPatientInsured(Integer patientId);

}

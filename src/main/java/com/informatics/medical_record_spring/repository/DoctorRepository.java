package com.informatics.medical_record_spring.repository;
import com.informatics.medical_record_spring.model.Doctors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctors, Integer> {

    @Query("SELECT d.name AS doctorName, COUNT(p.patientId) AS numberOfPatients " +
            "FROM Doctors d LEFT JOIN Patients p ON d.id = p.personalDoctor.id " +
            "GROUP BY d.name")
    List<Object[]> findDoctorPatientCounts();

    @Query("SELECT d.name AS doctorName, COUNT(v.visitId) AS numberOfVisits " +
            "FROM Doctors d LEFT JOIN Visits v ON d.id = v.doctorId.id " + // Adjusted to use doctorId.id
            "GROUP BY d.name")
    List<Object[]> findDoctorVisitCounts();

    @Query(value = "INSERT INTO doctors (name, specialty, is_personal_doctor) VALUES (:name, :specialty, :isPersonalDoctor)", nativeQuery = true)
    void createDoctorNative(@Param("name") String name, @Param("specialty") String specialty, @Param("isPersonalDoctor") boolean isPersonalDoctor);
}


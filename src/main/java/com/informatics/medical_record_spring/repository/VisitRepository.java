package com.informatics.medical_record_spring.repository;

import com.informatics.medical_record_spring.model.Visits;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface VisitRepository extends JpaRepository<Visits,Integer> {

    @Query("SELECT p.name AS patientName, v.visitDate AS visitDate, d.name AS doctorName " +
            "FROM Visits v " +
            "JOIN v.patientId p " +
            "JOIN v.doctorId d " +
            "ORDER BY p.name, v.visitDate")
    List<Object[]> findVisitDetails();

    @Query(value = "SELECT " +
            "v.visit_date AS visit_date, " +
            "d.diagnosis_text AS diagnosis, " +
            "t.treatment_text AS treatment, " +
            "s.start_date AS sick_leave_start, " +
            "s.end_date AS sick_leave_end " +
            "FROM visits v " +
            "LEFT JOIN diagnoses d ON v.visit_id = d.visit_id " +
            "LEFT JOIN treatments t ON d.diagnosis_id = t.diagnosis_id " +
            "LEFT JOIN sick_leaves s ON d.diagnosis_id = s.diagnosis_id " +
            "WHERE v.patient_id = ?1", nativeQuery = true)
    List<Object[]> findPatientHistory(Integer patientId);

    @Query(value = "SELECT " +
            "v.patient_id AS patient_id, " +
            "p.name AS patient_name, " +
            "v.visit_date AS visit_date, " +
            "d.diagnosis_text AS diagnosis, " +
            "t.treatment_text AS treatment, " +
            "s.start_date AS sick_leave_start, " +
            "s.end_date AS sick_leave_end " +
            "FROM visits v " +
            "JOIN patients p ON v.patient_id = p.patient_id " +
            "LEFT JOIN diagnoses d ON v.visit_id = d.visit_id " +
            "LEFT JOIN treatments t ON d.diagnosis_id = t.diagnosis_id " +
            "LEFT JOIN sick_leaves s ON d.diagnosis_id = s.diagnosis_id", nativeQuery = true)
    List<Object[]> findAllPatientsHistory();



    @Query("SELECT v.visitDate AS visitDate, p.name AS patientName, d.name AS doctorName " +
            "FROM Visits v " +
            "JOIN v.patientId p " +
            "JOIN v.doctorId d " +
            "WHERE v.visitDate BETWEEN :startDate AND :endDate " +
            "ORDER BY v.visitDate, p.name")
    List<Object[]> findVisitsWithinDateRange(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query("SELECT v FROM Visits v WHERE v.visitDate BETWEEN :startDate AND :endDate")
    List<Visits> findVisitsWithinDate(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query("SELECT v.visitDate AS visitDate, p.name AS patientName " +
            "FROM Visits v " +
            "JOIN v.patientId p " +
            "WHERE v.doctorId.id = :doctorId " +
            "AND v.visitDate BETWEEN :startDate AND :endDate " +
            "ORDER BY v.visitDate")
    List<Object[]> findVisitsByDoctorAndDateRange(
            @Param("doctorId") Integer doctorId,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate);
}

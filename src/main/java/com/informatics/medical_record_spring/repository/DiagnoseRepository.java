package com.informatics.medical_record_spring.repository;

import com.informatics.medical_record_spring.model.Diagnoses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiagnoseRepository extends JpaRepository<Diagnoses,Integer> {
//    @Query("SELECT p.name AS patientName, d.diagnosisText FROM Patients p JOIN Diagnoses d ON p.patientId = d.visit.id WHERE d.diagnosisText = :diagnosisName")
//    List<Object[]> findPatientsWithDiagnosis(@Param("diagnosisName") String diagnosisName);


    @Query("SELECT p.patientId, p.name, d.diagnosisId, d.diagnosisText " +
            "FROM Patients p JOIN Diagnoses d ON p.patientId = d.visit.visitId " +
            "WHERE d.diagnosisText = :diagnosisText")
    List<Object[]> findPatientsAndDiagnosesByDiagnosisText(@Param("diagnosisText") String diagnosisText);

    @Query("SELECT d.diagnosisText, COUNT(d) AS frequency " +
            "FROM Diagnoses d " +
            "GROUP BY d.diagnosisText " +
            "ORDER BY frequency DESC")
    List<Object[]> findMostFrequentDiagnosis();



}

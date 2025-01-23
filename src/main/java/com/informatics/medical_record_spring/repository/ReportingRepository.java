package com.informatics.medical_record_spring.repository;

import com.informatics.medical_record_spring.model.Visits;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface ReportingRepository extends JpaRepository<Visits, Integer> {

    @Query(value = """
        SELECT 
            (SELECT COUNT(*) FROM visits) AS totalVisits,
            (SELECT COUNT(*) FROM diagnoses) AS totalDiagnoses,
            (SELECT COUNT(*) FROM treatments) AS totalTreatments,
            (SELECT COUNT(DISTINCT patient_id) FROM visits) AS uniquePatients,
            (SELECT COUNT(DISTINCT doctor_id) FROM visits) AS uniqueDoctors,
            (SELECT COUNT(*) FROM diagnoses WHERE LENGTH(diagnosis_text) > 100) AS longDiagnoses,
            (SELECT MIN(visit_date) FROM visits) AS earliestVisitDate,
            (SELECT MAX(visit_date) FROM visits) AS latestVisitDate
        """, nativeQuery = true)
    Map<String, Object> getAggregatedStatistics();


}

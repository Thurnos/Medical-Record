package com.informatics.medical_record_spring.repository;

import com.informatics.medical_record_spring.model.SickLeaves;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SickLeaveRepository extends JpaRepository<SickLeaves, Integer> {
    @Query("SELECT FUNCTION('MONTH', sl.startDate) AS month, COUNT(sl) AS sickLeaveCount " +
            "FROM SickLeaves sl " +
            "GROUP BY FUNCTION('MONTH', sl.startDate) " +
            "ORDER BY sickLeaveCount DESC")
    List<Object[]> findMonthWithHighestSickLeaves();

    @Query("SELECT d.name AS doctorName, COUNT(sl) AS sickLeaveCount " +
            "FROM SickLeaves sl " +
            "JOIN sl.diagnose diag " +
            "JOIN diag.visit v " +
            "JOIN v.doctorId d " +
            "GROUP BY d.name " +
            "ORDER BY sickLeaveCount DESC")
    List<Object[]> findDoctorSickLeaveCounts();


}

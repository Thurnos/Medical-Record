package com.informatics.medical_record_spring.service;

import com.informatics.medical_record_spring.model.Visits;
import com.informatics.medical_record_spring.repository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class VisitService {

    @Autowired
    private VisitRepository visitRepository;
    private final JdbcTemplate jdbcTemplate;

    public VisitService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Create a new visit
    public Visits createVisit(Visits visit) {
        String sql = "INSERT INTO visits (patient_id, doctor_id, visit_date) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, visit.getPatientId(), visit.getDoctorId(), visit.getVisitDate());
        return visit;
    }

    // Delete a visit

    public void deleteVisit(Integer id) {
        String sql = "DELETE FROM visits WHERE visit_id = ?";
        jdbcTemplate.update(sql, id);
    }

    public Visits updateVisit(Integer id, Visits visit) {
        String sql = "UPDATE visits SET patient_id = ?, doctor_id = ?, visit_date = ? WHERE visit_id = ?";
        jdbcTemplate.update(sql, visit.getPatientId(), visit.getDoctorId(), visit.getVisitDate(), id);
        visit.setVisitId(id);
        return visit;
    }

    // Get all visits
    public List<Visits> getAllVisits() {
        return visitRepository.findAll();
    }

    // Get a visit by ID
    public Optional<Visits> getVisitById(Integer id) {
        return visitRepository.findById(id);
    }

    public List<Object[]> getVisitsWithinDateRange(Date startDate, Date endDate) {
        return visitRepository.findVisitsWithinDateRange(startDate, endDate);

    }
    public List<Visits> getVisitsWithinDate(Date startDate, Date endDate) {
        return visitRepository.findVisitsWithinDate(startDate, endDate);
    }
    public List<Object[]> getVisitsByDoctorAndDateRange(Integer doctorId, Date startDate, Date endDate) {
        return visitRepository.findVisitsByDoctorAndDateRange(doctorId, startDate, endDate);
    }

    public List<Object[]> getVisitDetails() {
        return visitRepository.findVisitDetails();
    }

    // Delete a visit by ID
}

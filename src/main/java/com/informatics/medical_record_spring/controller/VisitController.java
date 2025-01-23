package com.informatics.medical_record_spring.controller;

import com.informatics.medical_record_spring.dto.DoctorsDTO;
import com.informatics.medical_record_spring.dto.PatientDTO;
import com.informatics.medical_record_spring.dto.VisitsDTO;
import com.informatics.medical_record_spring.model.Doctors;
import com.informatics.medical_record_spring.model.Patients;
import com.informatics.medical_record_spring.model.Visits;
import com.informatics.medical_record_spring.service.VisitService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/visits")
public class VisitController {

    @Autowired
    private VisitService visitService;

    private static final String URL = "jdbc:mysql://localhost:3306/medical_record";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";


    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Create a new visit
    @PostMapping
    public ResponseEntity<VisitsDTO> createVisit(@RequestBody VisitsDTO visitsDTO) {
        Visits visit = convertToEntity(visitsDTO);
        Visits savedVisit = visitService.createVisit(visit);
        return ResponseEntity.ok(convertToDTO(savedVisit));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVisit(@PathVariable Integer id) {
        visitService.deleteVisit(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Visits> updateVisit(@PathVariable Integer id, @RequestBody Visits visit) {
        return ResponseEntity.ok(visitService.updateVisit(id, visit));
    }

    // Get all visits
    @GetMapping
    public ResponseEntity<List<Visits>> getAllVisits() {
        List<Visits> visits = visitService.getAllVisits();
        return ResponseEntity.ok(visits);
    }

    // Endpoint to get visit details (patient name, visit date, doctor name)
    @GetMapping("/visit-details")
    public ResponseEntity<List<Object[]>> getVisitDetails() {
        List<Object[]> visitDetails = visitService.getVisitDetails();
        if (visitDetails.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(visitDetails);
    }

    @GetMapping("/by-doctor-date-range")
    public List<Object[]> getVisitsByDoctorAndDateRange(
            @RequestParam("doctorId") Integer doctorId,
            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        return visitService.getVisitsByDoctorAndDateRange(doctorId, startDate, endDate);
    }

    @GetMapping("/date-range")
    public List<Visits> getVisitsWithinDateRange(
            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        return visitService.getVisitsWithinDate(startDate, endDate);
    }
    // Get a visit by ID
    @GetMapping("/{id}")
    public ResponseEntity<Visits> getVisitById(@PathVariable Integer id) {
        Optional<Visits> visit = visitService.getVisitById(id);
        return visit.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    private VisitsDTO convertToDTO(Visits visit) {
        VisitsDTO visitsDTO = new VisitsDTO();
        visitsDTO.setVisitId(visit.getVisitId());
        visitsDTO.setVisitDate(visit.getVisitDate());

        // Populate PatientDTO from Patient entity
        if (visit.getPatientId() != null) {
            PatientDTO patientDTO = new PatientDTO();
            patientDTO.setId(visit.getPatientId().getPatientId());
            patientDTO.setName(visit.getPatientId().getName());
            visitsDTO.setPatient(patientDTO);
        }

        // Populate DoctorsDTO from Doctor entity
        if (visit.getDoctorId() != null) {
            DoctorsDTO doctorsDTO = new DoctorsDTO();
            doctorsDTO.setId(visit.getDoctorId().getId());
            doctorsDTO.setName(visit.getDoctorId().getName());
            visitsDTO.setDoctor(doctorsDTO);
        }

        return visitsDTO;
    }

    private Visits convertToEntity(VisitsDTO visitsDTO) {
        Visits visit = new Visits();
        visit.setVisitId(visitsDTO.getVisitId());
        visit.setVisitDate(visitsDTO.getVisitDate());

        // Populate Patient entity from PatientDTO
        if (visitsDTO.getPatient() != null) {
            Patients patient = new Patients();
            patient.setPatientId(visitsDTO.getPatient().getId());
            patient.setName(visitsDTO.getPatient().getName());
            visit.setPatientId(patient);
        }

        // Populate Doctor entity from DoctorsDTO
        if (visitsDTO.getDoctor() != null) {
            Doctors doctor = new Doctors();
            doctor.setId(visitsDTO.getDoctor().getId());
            doctor.setName(visitsDTO.getDoctor().getName());
            visit.setDoctorId(doctor);
        }

        return visit;
    }



}
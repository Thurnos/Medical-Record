package com.informatics.medical_record_spring.controller;

import ch.qos.logback.core.model.Model;
import com.informatics.medical_record_spring.model.Patients;
import com.informatics.medical_record_spring.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    private static final String URL = "jdbc:mysql://localhost:3306/medical_record";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Create a new patient
    @PostMapping
    public ResponseEntity<Patients> createPatient(@RequestBody Patients patient) {
        String sql = "INSERT INTO patients (user_id, name, egn, is_insured, personal_doctor_id) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, patient.getUserId(), patient.getName(), patient.getEgn(), patient.getInsured(), patient.getPersonalDoctorId());
        return ResponseEntity.ok(patient);
    }

    // Update an existing patient
    @PutMapping("/{id}")
    public ResponseEntity<Patients> updatePatient(@PathVariable Integer id, @RequestBody Patients updatedPatient) {
        String sql = "UPDATE patients SET user_id = ?, name = ?, egn = ?, is_insured = ?, personal_doctor_id = ? WHERE patient_id = ?";
        jdbcTemplate.update(sql, updatedPatient.getUserId(), updatedPatient.getName(), updatedPatient.getEgn(), updatedPatient.getInsured(), updatedPatient.getPersonalDoctorId(), id);
        updatedPatient.setPatientId(id);
        return ResponseEntity.ok(updatedPatient);
    }

    // Delete a patient
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Integer id) {
        String sql = "DELETE FROM patients WHERE patient_id = ?";
        jdbcTemplate.update(sql, id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Patients>> getAllPatients() {
        List<Patients> patients = patientService.getAllPatients();
        if (patients.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(patients);
    }

    // Check if the patient has paid
    @GetMapping("/{id}/has-paid-insurance")
    public boolean hasPaidInsurance(@PathVariable Integer id) {
        return patientService.hasPaidInsurance(id);
    }

    // Get own history for the logged-in patient
    @GetMapping("/{id}/own-history")
    public List<Object[]> getOwnHistory(@PathVariable Integer id) {
        return patientService.getOwnHistory(id);
    }
    @GetMapping("/all-patients-history")
    public ResponseEntity<List<Object[]>> getAllPatientsHistory() {
        List<Object[]> allPatientsHistory = patientService.getAllPatientsHistory();
        if (allPatientsHistory.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(allPatientsHistory);
    }

    @GetMapping("/api/patients/{patientId}/insurance-status")
    public boolean checkInsuranceStatus(@PathVariable Integer patientId) {
        return patientService.hasPaidInsurance(patientId);
    }
    // Get patient history by ID
    @GetMapping("/{id}/history")
    public List<Object[]> getPatientHistory(@PathVariable Integer id) {
        return patientService.getPatientHistory(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patients> getPatientById(@PathVariable Integer id) {
        Optional<Patients> patient = patientService.getPatientById(id);
        return patient.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


}

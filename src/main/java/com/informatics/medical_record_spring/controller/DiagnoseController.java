package com.informatics.medical_record_spring.controller;

import com.informatics.medical_record_spring.dto.DiagnosesDTO;
import com.informatics.medical_record_spring.exception.EntityAlreadyExistsException;
import com.informatics.medical_record_spring.model.Diagnoses;
import com.informatics.medical_record_spring.service.DiagnoseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/diagnoses")
public class DiagnoseController {


    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private DiagnoseService diagnoseService;

    private static final String URL = "jdbc:mysql://localhost:3306/medical_record";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    @PostMapping
    public String createDiagnose(@RequestParam int visitId, @RequestParam String diagnosisText) {
        String sql = "INSERT INTO diagnoses (visit_id, diagnosis_text) VALUES (?, ?)";

        try {
            jdbcTemplate.update(sql, visitId, diagnosisText);
            return "Diagnosis saved successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error saving diagnosis: " + e.getMessage();
        }
    }
    // Update an existing diagnosis
    @PutMapping("/{id}")
    public void updateDiagnose(@PathVariable int id, @RequestBody Map<String, Object> payload) {
        String sql = "UPDATE diagnoses SET visit_id = ?, diagnosis_text = ? WHERE diagnosis_id = ?";
        jdbcTemplate.update(sql, payload.get("visitId"), payload.get("diagnosisText"), id);
    }

    // Delete a diagnosis
    @DeleteMapping("/{id}")
    public void deleteDiagnose(@PathVariable int id) {
        String sql = "DELETE FROM diagnoses WHERE diagnosis_id = ?";
        jdbcTemplate.update(sql, id);
    }
    @GetMapping("/diagnoses-page")
    public String showDiagnosesPage() {
        return "diagnoses";
    }

    @GetMapping
    public ResponseEntity<List<DiagnosesDTO>> getAllDiagnoses() {
        List<DiagnosesDTO> diagnoses = diagnoseService.getAllDiagnoses();
        return ResponseEntity.ok(diagnoses);
    }
//    @GetMapping("/patients")
//    public ResponseEntity<List<PatientDiagnosisDTO>> getPatientsByDiagnosis(@RequestParam String diagnosisText) {
//        List<PatientDiagnosisDTO> patientsByDiagnosis = diagnoseService.getPatientsByDiagnosis(diagnosisText);
//        return ResponseEntity.ok(patientsByDiagnosis);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<DiagnosesDTO> getDiagnosesById(@PathVariable Integer id) {
        Optional<DiagnosesDTO> diagnose = diagnoseService.getDiagnoseById(id);
        return diagnose.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/most-frequent")
    public ResponseEntity<List<Object[]>> getMostFrequentDiagnosis() {
        List<Object[]> result = diagnoseService.getMostFrequentDiagnosis();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/patients-diagnoses")
    public List<Object[]> getPatientsAndDiagnoses(@RequestParam String diagnosisText) {
        return diagnoseService.getPatientsAndDiagnoses(diagnosisText);
    }


}

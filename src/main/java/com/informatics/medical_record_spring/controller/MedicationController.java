package com.informatics.medical_record_spring.controller;

import com.informatics.medical_record_spring.dto.MedicationsDTO;
import com.informatics.medical_record_spring.exception.ResourceNotFoundException;
import com.informatics.medical_record_spring.model.Medications;
import com.informatics.medical_record_spring.repository.MedicationRepository;
import com.informatics.medical_record_spring.service.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medications")
public class MedicationController {

    @Autowired
    private MedicationService medicationService;

    // Get all medications
    @GetMapping
    public List<MedicationsDTO> getAllMedications() {
        return medicationService.getAllMedications();
    }

    // Get medication by ID
    @GetMapping("/{id}")
    public ResponseEntity<MedicationsDTO> getMedicationById(@PathVariable Integer id) {
        return ResponseEntity.ok(medicationService.getMedicationById(id));
    }

    // Create a new medication
    @PostMapping
    public ResponseEntity<MedicationsDTO> createMedication(@RequestBody MedicationsDTO medicationDTO) {
        MedicationsDTO createdMedication = medicationService.createMedication(medicationDTO);
        return ResponseEntity.ok(createdMedication);
    }

    // Update an existing medication
    @PutMapping("/{id}")
    public ResponseEntity<MedicationsDTO> updateMedication(@PathVariable Integer id, @RequestBody MedicationsDTO medicationDTO) {
        MedicationsDTO updatedMedication = medicationService.updateMedication(id, medicationDTO);
        if (updatedMedication == null) {
            throw new ResourceNotFoundException("Medication not found with id: " + id);
        }
        return ResponseEntity.ok(updatedMedication);
    }

    // Delete a medication
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMedication(@PathVariable Integer id) throws Throwable {
        boolean isDeleted = medicationService.deleteMedication(id);
        if (!isDeleted) {
            throw new ResourceNotFoundException("Medication not found with id: " + id);
        }
        return ResponseEntity.ok("Medication deleted successfully!");
    }
}
package com.informatics.medical_record_spring.service;

import com.informatics.medical_record_spring.dto.TreatmentsDTO;
import com.informatics.medical_record_spring.model.Treatments;
import com.informatics.medical_record_spring.repository.TreatmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TreatmentsService {

    @Autowired
    private TreatmentsRepository treatmentsRepository;

    // Convert Treatment to DTO
    private TreatmentsDTO convertToDTO(Treatments treatment) {
        return new TreatmentsDTO(
                treatment.getTreatmentId(),
                treatment.getDiagnose() != null ? treatment.getDiagnose().getDiagnosisId() : null,
                treatment.getTreatmentText()
        );
    }

    // Create a new treatment
    public Treatments createTreatment(Treatments treatment) {
        return treatmentsRepository.save(treatment);
    }

    // Get all treatments
    public List<TreatmentsDTO> getAllTreatments() {
        List<Treatments> treatments = treatmentsRepository.findAll();
        return treatments.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Get a treatment by ID
    public Optional<TreatmentsDTO> getTreatmentById(Integer id) {
        return treatmentsRepository.findById(id)
                .map(this::convertToDTO);
    }

    // Update a treatment
    public Treatments updateTreatment(Integer id, Treatments updatedTreatment) {
        return treatmentsRepository.findById(id)
                .map(existingTreatment -> {
                    existingTreatment.setDiagnose(updatedTreatment.getDiagnose());
                    existingTreatment.setTreatmentText(updatedTreatment.getTreatmentText());
                    return treatmentsRepository.save(existingTreatment);
                })
                .orElseThrow(() -> new RuntimeException("Treatment with ID " + id + " not found."));
    }

    // Delete a treatment by ID
    public void deleteTreatment(Integer id) {
        treatmentsRepository.deleteById(id);
    }
}

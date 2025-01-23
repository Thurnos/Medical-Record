package com.informatics.medical_record_spring.service;

import com.informatics.medical_record_spring.dto.DiagnosesDTO;
import com.informatics.medical_record_spring.dto.SickLeavesDTO;
import com.informatics.medical_record_spring.model.Diagnoses;
import com.informatics.medical_record_spring.model.SickLeaves;
import com.informatics.medical_record_spring.repository.SickLeaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SickLeaveService {

    @Autowired
    private SickLeaveRepository sickLeaveRepository;

    // Create a new sick leave
    public SickLeaves createSickLeave(SickLeaves sickLeave) {
        return sickLeaveRepository.save(sickLeave);
    }

    // Get all sick leaves
    public List<SickLeaves> getAllSickLeaves() {
        return sickLeaveRepository.findAll();
    }

    // Get a sick leave by ID
    public Optional<SickLeaves> getSickLeaveById(Integer id) {
        return sickLeaveRepository.findById(id);
    }

    // Update a sick leave
    public SickLeaves updateSickLeave(Integer id, SickLeaves updatedSickLeave) {
        return sickLeaveRepository.findById(id)
                .map(existingSickLeave -> {
                    existingSickLeave.setDiagnose(updatedSickLeave.getDiagnose());
                    existingSickLeave.setStartDate(updatedSickLeave.getStartDate());
                    existingSickLeave.setEndDate(updatedSickLeave.getEndDate());
                    return sickLeaveRepository.save(existingSickLeave);
                })
                .orElseThrow(() -> new RuntimeException("Sick leave with ID " + id + " not found."));
    }
    public Object[] getMonthWithHighestSickLeaves() {
        List<Object[]> results = sickLeaveRepository.findMonthWithHighestSickLeaves();
        return results.isEmpty() ? null : results.get(0); // Fetch the top result
    }
    public List<Object[]> getDoctorSickLeaveCounts() {
        return sickLeaveRepository.findDoctorSickLeaveCounts();
    }
    public SickLeavesDTO convertToDTO(SickLeaves sickLeave) {
        // Convert Diagnoses to DiagnosesDTO
        DiagnosesDTO diagnosesDTO = null;
        if (sickLeave.getDiagnose() != null) {
            Diagnoses diagnose = sickLeave.getDiagnose();
            diagnosesDTO = new DiagnosesDTO();
            diagnosesDTO.setDiagnosisId(diagnose.getDiagnosisId());
            diagnosesDTO.setVisitId(diagnose.getVisit().getVisitId());
            diagnosesDTO.setDiagnosisText(diagnose.getDiagnosisText());
        }

        // Create and return SickLeavesDTO
        return new SickLeavesDTO(
                sickLeave.getSickLeavesId(),
                diagnosesDTO, // Use DiagnosesDTO here
                sickLeave.getStartDate(),
                sickLeave.getEndDate()
        );
    }

    // Delete a sick leave by ID
    public void deleteSickLeave(Integer id) {
        sickLeaveRepository.deleteById(id);
    }
}

package com.informatics.medical_record_spring.service;

import com.informatics.medical_record_spring.dto.DiagnosesDTO;
import com.informatics.medical_record_spring.dto.PatientDTO;
import com.informatics.medical_record_spring.exception.EntityAlreadyExistsException;
import com.informatics.medical_record_spring.exception.EntityNotFoundForDeletionException;
import com.informatics.medical_record_spring.model.Diagnoses;
import com.informatics.medical_record_spring.model.Visits;
import com.informatics.medical_record_spring.repository.DiagnoseRepository;
import com.informatics.medical_record_spring.repository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DiagnoseService {

    @Autowired
    private DiagnoseRepository diagnoseRepository;

    @Autowired
    private VisitRepository visitRepository;



    // Helper to map Diagnoses to DiagnoseDTO
    private DiagnosesDTO mapToDTO(Diagnoses diagnose) {
        DiagnosesDTO dto = new DiagnosesDTO();
        dto.setDiagnosisId(diagnose.getDiagnosisId());
        dto.setVisitId(diagnose.getVisit() != null ? diagnose.getVisit().getVisitId() : null);
        dto.setDiagnosisText(diagnose.getDiagnosisText());
        return dto;
    }
    public List<Object[]> getPatientsAndDiagnoses(String diagnosisText) {
        List<Object[]> results = diagnoseRepository.findPatientsAndDiagnosesByDiagnosisText(diagnosisText);
        List<Object[]> dtoList = new ArrayList<>();

        for (Object[] row : results) {
            PatientDTO patientDTO = new PatientDTO();
            DiagnosesDTO diagnosesDTO = new DiagnosesDTO();

            // Map Patient data
            patientDTO.setId((Integer) row[0]);
            patientDTO.setName((String) row[1]);

            diagnosesDTO.setDiagnosisId((Integer) row[2]);
            diagnosesDTO.setDiagnosisText((String) row[3]);

            dtoList.add(new Object[]{patientDTO, diagnosesDTO});
        }

        return dtoList;
    }
    public List<Object[]> getMostFrequentDiagnosis() {
        return diagnoseRepository.findMostFrequentDiagnosis();
    }

    private Diagnoses mapToEntity(DiagnosesDTO dto) {
        Diagnoses diagnose = new Diagnoses();
        diagnose.setDiagnosisId(dto.getDiagnosisId());
        diagnose.setDiagnosisText(dto.getDiagnosisText());
        if (dto.getVisitId() != null) {
            Optional<Visits> visit = visitRepository.findById(dto.getVisitId());
            visit.ifPresent(diagnose::setVisit);
        }
        return diagnose;
    }
//    public List<PatientDiagnosisDTO> getPatientsByDiagnosis(String diagnosisText) {
//        return diagnoseRepository.findPatientsByDiagnosis(diagnosisText);
//    }

    // Create a new diagnosis
    public Diagnoses createDiagnose(Diagnoses diagnose) {
        return diagnoseRepository.save(diagnose);
    }

    // Get all diagnoses
    public List<DiagnosesDTO> getAllDiagnoses() {
        return diagnoseRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }


    // Get a diagnosis by ID
    public Optional<DiagnosesDTO> getDiagnoseById(Integer id) {
        return diagnoseRepository.findById(id).map(this::mapToDTO);
    }

    // Delete a diagnosis by ID

    public void deleteDiagnose(Integer id) {
        if (!diagnoseRepository.existsById(id)) {
            throw new EntityNotFoundForDeletionException("Diagnosis with ID " + id + " not found for deletion.");
        }
        diagnoseRepository.deleteById(id);
    }
}

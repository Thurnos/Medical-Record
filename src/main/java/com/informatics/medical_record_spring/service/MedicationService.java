package com.informatics.medical_record_spring.service;


import com.informatics.medical_record_spring.dto.MedicationsDTO;
import com.informatics.medical_record_spring.exception.ResourceNotFoundException;
import com.informatics.medical_record_spring.model.Medications;
import com.informatics.medical_record_spring.repository.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicationService {

    @Autowired
    private MedicationRepository medicationsRepository;

    public List<MedicationsDTO> getAllMedications() {
        return medicationsRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public MedicationsDTO getMedicationById(Integer id) {
        Medications medication = medicationsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medication not found with id: " + id));
        return convertToDTO(medication);
    }

    public MedicationsDTO createMedication(MedicationsDTO medicationDTO) {
        Medications medication = convertToEntity(medicationDTO);
        Medications savedMedication = medicationsRepository.save(medication);
        return convertToDTO(savedMedication);
    }

    public MedicationsDTO updateMedication(Integer id, MedicationsDTO medicationDTO) {
        Medications medication = medicationsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medication not found with id: " + id));
        medication.setName(medicationDTO.getName());
        medication.setDrugClass(medicationDTO.getDrugClass());
        medication.setManufacturer(medicationDTO.getManufacturer());
        medication.setSideEffects(medicationDTO.getSideEffects());
        medication.setDosage_form(medicationDTO.getDosageForm());
        Medications updatedMedication = medicationsRepository.save(medication);
        return convertToDTO(updatedMedication);
    }

    public boolean deleteMedication(Integer id) {
        if (!medicationsRepository.existsById(id)) {
            throw new ResourceNotFoundException("Medication not found with id: " + id);
        }
        medicationsRepository.deleteById(id);
        return true;
    }

    private MedicationsDTO convertToDTO(Medications medication) {
        MedicationsDTO dto = new MedicationsDTO();
        dto.setId(medication.getId());
        dto.setName(medication.getName());
        dto.setDrugClass(medication.getDrugClass());
        dto.setManufacturer(medication.getManufacturer());
        dto.setSideEffects(medication.getSideEffects());
        dto.setDosageForm(medication.getDosage_form());
        return dto;
    }

    private Medications convertToEntity(MedicationsDTO medicationDTO) {
        Medications medication = new Medications();
        medication.setId(medicationDTO.getId());
        medication.setName(medicationDTO.getName());
        medication.setDrugClass(medicationDTO.getDrugClass());
        medication.setManufacturer(medicationDTO.getManufacturer());
        medication.setSideEffects(medicationDTO.getSideEffects());
        medication.setDosage_form(medicationDTO.getDosageForm());
        return medication;
    }
}

package com.informatics.medical_record_spring.service;

import com.informatics.medical_record_spring.model.Doctors;
import com.informatics.medical_record_spring.model.Patients;
import com.informatics.medical_record_spring.repository.PatientsRepository;
import com.informatics.medical_record_spring.repository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientsRepository patientsRepository;

    @Autowired
    private VisitRepository visitRepository;

    public Patients createPatient(Patients patient){
        return patientsRepository.save(patient);
    }

    public List<Patients>getAllPatients(){
        return patientsRepository.findAll();
    }
    public Patients findById(Integer id) {
        return patientsRepository.findById(id).orElse(null);
    }

    public Optional<Patients> getPatientById(Integer id){
        return patientsRepository.findById(id);
    }
    public Optional<Patients> updatePatient(Integer id, Patients updatedPatient) {
        return Optional.ofNullable(patientsRepository.findById(id)
                .map(existingPatient -> {
                    existingPatient.setName(updatedPatient.getName());
                    existingPatient.setUser(updatedPatient.getUser());
                    existingPatient.setEgn(updatedPatient.getEgn());
                    existingPatient.setInsured(updatedPatient.getInsured());
                    existingPatient.setPersonalDoctor(updatedPatient.getPersonalDoctor());
                    return patientsRepository.save(existingPatient);
                })
                .orElseThrow(() -> new RuntimeException("Patient not found with ID: " + id)));
    }

    public List<Object[]> getAllPatientsHistory() {
        return visitRepository.findAllPatientsHistory();  // Call the repository method to fetch all patient histories
    }

    public void deletePatient(Integer id){
         patientsRepository.deleteById(id);
    }


    // Get the patient history (diagnosis, treatment, and sick leaves)
    public List<Object[]> getPatientHistory(Integer patientId) {
        return visitRepository.findPatientHistory(patientId);
    }

    // Check if the patient has paid insurance in the last 6 months
    public boolean hasPaidInsurance(Integer patientId) {
        // Check if the patient is insured by querying the PatientsRepository
        Boolean isInsured = patientsRepository.isPatientInsured(patientId);

        return isInsured != null && isInsured;
    }


    // Get own history (for the logged-in patient)
    public List<Object[]> getOwnHistory(Integer patientId) {
        return visitRepository.findPatientHistory(patientId);
    }
}

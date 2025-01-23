package com.informatics.medical_record_spring.service;

import com.informatics.medical_record_spring.dto.DiagnosesDTO;
import com.informatics.medical_record_spring.dto.DoctorsDTO;
import com.informatics.medical_record_spring.dto.SickLeavesDTO;
import com.informatics.medical_record_spring.dto.TreatmentsDTO;
import com.informatics.medical_record_spring.model.Diagnoses;
import com.informatics.medical_record_spring.model.Doctors;
import com.informatics.medical_record_spring.model.SickLeaves;
import com.informatics.medical_record_spring.model.Visits;
import com.informatics.medical_record_spring.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientsRepository patientsRepository;

    @Autowired
    private DiagnoseRepository diagnoseRepository;


    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private SickLeaveRepository sickLeaveRepository;

    @Autowired
    private VisitRepository visitRepository;

    @Autowired
    private TreatmentsRepository treatmentsRepository;

    public Doctors createDoctor(Doctors doctor) {
        return doctorRepository.save(doctor);
    }
    public List<Doctors> getAllDoctors(){
        return doctorRepository.findAll();
    }

    public Optional<Doctors> getDoctorById(Integer id){
        return doctorRepository.findById(id);
    }

    public List<Object[]> getPatientsByDoctorName(String doctorName) {
        return patientsRepository.findPatientsByDoctorName(doctorName);
    }

    public List<Object[]> getDoctorPatientCounts() {
        // Query to fetch doctor name and number of assigned patients
        return doctorRepository.findDoctorPatientCounts();
    }
    public List<Object[]> getDoctorVisitCounts() {
        return doctorRepository.findDoctorVisitCounts();
    }

    public Optional<Doctors> updateDoctor(Integer id , Doctors updatedDoctor){
        return Optional.ofNullable(doctorRepository.findById(id)
                .map(existingDoctor -> {
                    existingDoctor.setName(updatedDoctor.getName());
                    existingDoctor.setUser(updatedDoctor.getUser());
                    existingDoctor.setSpecialty(updatedDoctor.getSpecialty());
                    existingDoctor.setQualifications(updatedDoctor.getQualifications());
                    existingDoctor.setPersonalDoctor(updatedDoctor.getPersonalDoctor());
                    return doctorRepository.save(existingDoctor);
                })
                .orElseThrow(() -> new RuntimeException("Doctor not found with ID: " + id)));
    }

    public List<Object[]> getVisitsByDoctorAndDateRange(int doctorId, Date startDate, Date endDate) {
        return visitRepository.findVisitsWithinDateRange(startDate, endDate);
    }

    public void deleteDoctor(Integer id){
        doctorRepository.deleteById(id);
    }

}

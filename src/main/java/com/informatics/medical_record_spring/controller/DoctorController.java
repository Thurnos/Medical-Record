package com.informatics.medical_record_spring.controller;

import com.informatics.medical_record_spring.dao.DoctorDAO;
import com.informatics.medical_record_spring.dto.DoctorsDTO;
import com.informatics.medical_record_spring.dto.QualificationsDTO;
import com.informatics.medical_record_spring.model.Doctors;
import com.informatics.medical_record_spring.model.Qualifications;
import com.informatics.medical_record_spring.model.User;
import com.informatics.medical_record_spring.repository.DoctorRepository;
import com.informatics.medical_record_spring.repository.UserRepository;
import com.informatics.medical_record_spring.service.DoctorService;
import com.informatics.medical_record_spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorsService;

    @Autowired
    private DoctorRepository doctorsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;


    @Autowired
    private DoctorDAO doctorDao;

    // Create a new doctor
//    @PostMapping("/api/doctors")
////    public ResponseEntity<Doctors> createDoctor(@RequestBody Doctors doctor) {
////        Doctors savedDoctor = doctorsService.createDoctor(doctor);
////        return ResponseEntity.status(HttpStatus.CREATED).body(savedDoctor);
////    }
    // Create a new doctor
    @PostMapping
    public ResponseEntity<?> createDoctor(@RequestBody Doctors doctor) {
        doctorDao.createDoctor(doctor);
        return ResponseEntity.ok(doctor);
    }


    // Get all doctors
    @GetMapping
    public ResponseEntity<List<DoctorsDTO>> getAllDoctors() {
        List<Doctors> doctors = doctorsService.getAllDoctors();
        if (doctors.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        // Convert each doctor to DoctorsDTO
        List<DoctorsDTO> doctorsDTOs = doctors.stream()
                .map(this::mapToDoctorsDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(doctorsDTOs);
    }
    // In DoctorController
//    @GetMapping("patient-counts")
//    public ResponseEntity<List<Object[]>> getAllDoctorPatientCounts() {
//        List<Object[]> patientCounts = doctorsService.getDoctorPatientCounts();
//
//        if (patientCounts.isEmpty()) {
//            return ResponseEntity.noContent().build();
//        }
//
//        return ResponseEntity.ok(patientCounts);
//    }
    @GetMapping("/patient-counts")
    public ResponseEntity<List<Object[]>> getAllDoctorPatientCounts() {
        List<Object[]> patientCounts = doctorsService.getDoctorPatientCounts();
        if (patientCounts.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(patientCounts);
    }

    // Get a doctor by ID
    @GetMapping("/{id}")
    public ResponseEntity<DoctorsDTO> getDoctorById(@PathVariable Integer id) {
        Optional<Doctors> doctor = doctorsService.getDoctorById(id);
        return doctor.map(d -> ResponseEntity.ok(mapToDoctorsDTO(d)))
                .orElse(ResponseEntity.notFound().build());
    }
    // Get individual doctor by ID
    @GetMapping("/doctor/{id}")
    public String getDoctorDetail(@PathVariable Integer id, Model model) {
        Optional<Doctors> doctor = doctorsService.getDoctorById(id);
        if (doctor.isPresent()) {
            model.addAttribute("doctor", doctor.get());
            return "doctorDetail";
        } else {
            return "error";
        }
    }


    // Get doctor names with their patient count
    @GetMapping("/view-patient-counts")
    public String getDoctorPatientCountsView(Model model) {
        List<Doctors> doctors = doctorsService.getAllDoctors();
        model.addAttribute("doctors", doctors);
        return "patients-by-doctor";
    }

    @GetMapping("/createdoctor")
    public String showCreateDoctorForm(Model model) {
        model.addAttribute("doctor", new DoctorsDTO());
        return "createDoctor";
    }

    @GetMapping("/patients-by-doctor")
    public ResponseEntity<List<Object[]>> getPatientsByDoctor(@RequestParam String doctorName) {
        List<Object[]> patients = doctorsService.getPatientsByDoctorName(doctorName);
        return ResponseEntity.ok(patients);
    }
    // Endpoint to get doctor visit counts
    @GetMapping("/visit-counts")
    public ResponseEntity<List<Object[]>> getDoctorVisitCounts() {
        List<Object[]> doctorVisitCounts = doctorsService.getDoctorVisitCounts();

        if (doctorVisitCounts.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(doctorVisitCounts);
    }



    // Update a doctor by ID
    @PutMapping("/{id}")
    public ResponseEntity<DoctorsDTO> updateDoctor(@PathVariable Integer id, @RequestBody Doctors updatedDoctor) {
        Optional<Doctors> doctor = doctorsService.updateDoctor(id, updatedDoctor);
        return doctor.map(d -> ResponseEntity.ok(mapToDoctorsDTO(d)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete a doctor by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Integer id) {
        doctorsService.deleteDoctor(id);
        return ResponseEntity.noContent().build();
    }

    // Mapping Method: Doctors -> DoctorsDTO
    private DoctorsDTO mapToDoctorsDTO(Doctors doctor) {
        DoctorsDTO dto = new DoctorsDTO();
        dto.setId(doctor.getId());
        dto.setName(doctor.getName());
        dto.setPersonalDoctor(doctor.getPersonalDoctor());
        dto.setQualifications(doctor.getQualifications().stream()
                .map(this::mapToQualificationsDTO)
                .collect(Collectors.toList())); // Map qualifications to DTOs
        return dto;
    }


    // Mapping Method: Qualifications -> QualificationsDTO
    private QualificationsDTO mapToQualificationsDTO(Qualifications qualification) {
        QualificationsDTO dto = new QualificationsDTO();
        dto.setId(qualification.getQualification_id());
        dto.setQualificationName(qualification.getQualificationName());
        dto.setDoctorId(qualification.getDoctor() != null ? qualification.getDoctor().getId() : null);
        return dto;
    }
}

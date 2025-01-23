package com.informatics.medical_record_spring.controller;

import com.informatics.medical_record_spring.model.Diagnoses;
import com.informatics.medical_record_spring.model.Doctors;
import com.informatics.medical_record_spring.model.Patients;
import com.informatics.medical_record_spring.service.DoctorService;
import com.informatics.medical_record_spring.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class PatientWebController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    @GetMapping("/patients")
    public String showPatients(Model model) {
        List<Patients> patients = patientService.getAllPatients();
        model.addAttribute("patients", patients);
        return "patients"; // Return the patients.html view
    }


    // This method handles fetching a specific patient by ID
    @GetMapping("/patients/{id}")
    public String getPatientDetails(@PathVariable("id") Integer id, Model model) {
        Patients patient = patientService.findById(id);
        model.addAttribute("patient", patient);
        return "patient-details";
    }

    //method to fetch and show patient history
    @GetMapping("/patient-history")
    public String getPatientHistoryPage(Model model) {
        List<Patients> patients = patientService.getAllPatients();
        model.addAttribute("patients", patients);
        return "patient-history";
    }

    @GetMapping("/patient-crud")
    public String showPatientCrudPage() {
        return "patient-crud";
    }
    @GetMapping("/patient/create")
    public String showCreatePatientPage() {
        return "patient-crud";
    }
//    @GetMapping("/patient-counts")
//    public String showPatientCounts(Model model) {
//        List<Doctors> doctors = doctorService.getAllDoctors();
//        model.addAttribute("doctors", doctors);
//        return "patient_counts"; // Thymeleaf template name
//    }



//    @GetMapping("/patients-by-doctor/results")
//    public String getPatientsByDoctorName(@RequestParam("doctorName") String doctorName, Model model) {
//        // Fetch the doctor’s patients using the DoctorService
//        List<Object[]> patients = doctorService.getPatientsByDoctorName(doctorName);
//
//        // Fetch all doctors to populate the dropdown in the form
//        List<Doctors> doctors = doctorService.getAllDoctors();
//
//        model.addAttribute("doctors", doctors);
//        model.addAttribute("patients", patients);
//        model.addAttribute("doctorName", doctorName);
//
//        return "patients-by-doctor";
//    }


    // This method fetches and displays all patients' medical history
    @GetMapping("/patients/history")
    public String getAllPatientHistory(Model model) {
        List<Object[]> allPatientHistory = patientService.getAllPatientsHistory();
        model.addAttribute("patientsHistory", allPatientHistory);
        return "all-patients-history";
    }
    // Show medical history for all patients
    @GetMapping("/all-patients-history")
    public String showAllPatientsHistory(Model model) {
        List<Object[]> allPatientsHistory = patientService.getAllPatientsHistory();
        model.addAttribute("patientsHistory", allPatientsHistory);
        return "all-patients-history";
    }
    // Show medical history for all patients

    // Display all patients in a single table
    @GetMapping("/all-patients")
    public String showAllPatients(Model model) {
        List<Patients> patients = patientService.getAllPatients();
        model.addAttribute("patients", patients);
        return "all-patients";
    }

    // Fetch the patient's history after selection
    @GetMapping("/patient-history/{id}")
    public String getPatientHistory(@PathVariable("id") Integer id, Model model) {
        List<Object[]> patientHistory = patientService.getPatientHistory(id);
        model.addAttribute("patientHistory", patientHistory);
        model.addAttribute("patientId", id);
        return "patient-history-details";
    }

    }



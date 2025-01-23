package com.informatics.medical_record_spring.controller;

import com.informatics.medical_record_spring.model.Diagnoses;
import com.informatics.medical_record_spring.model.Doctors;
import com.informatics.medical_record_spring.model.Patients;
import com.informatics.medical_record_spring.service.DiagnoseService;
import com.informatics.medical_record_spring.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DiagnoseWebController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private DiagnoseService diagnoseService;

    @GetMapping("/diagnoses/most-frequent")
    public String viewMostFrequentDiagnoses() {
        return "diagnoses";
    }


    // Show patients assigned to a specific doctor
    @GetMapping("/doctors/patients-by-doctor")
    public String showDoctorSearchForm() {
        return "doctorSearchForm";
    }

    // Get patients assigned to a specific doctor by name
    @GetMapping("/doctors/patients-by-doctor/results")
    public String getPatientsByDoctorName(@RequestParam String doctorName, Model model) {
        List<Object[]> patients = doctorService.getPatientsByDoctorName(doctorName);
        model.addAttribute("doctorName", doctorName);
        model.addAttribute("patients", patients);
        return "patientsByDoctor";
    }
    @GetMapping("/diagnoses/create")
    public String showCreateDiagnosePage(Model model) {
        model.addAttribute("diagnose", new Diagnoses());
        return "diagnose-crud";
    }
    @GetMapping("/search")
    public String searchDiagnosesPage() {
        return "search-diagnoses";
    }


    // Serve the page for creating a diagnose
    @GetMapping("/diagnose-crud")
    public String showDiagnoseCrudPage(Model model) {
        model.addAttribute("diagnose", new Diagnoses());

        model.addAttribute("diagnosesList", diagnoseService.getAllDiagnoses());
        return "diagnose-crud";
    }

    // Handle form submission to create a diagnose
    @PostMapping("/diagnose-crud")
    public String createDiagnose(@ModelAttribute Diagnoses diagnose, Model model) {
        diagnoseService.createDiagnose(diagnose);


        model.addAttribute("diagnose", new Diagnoses());
        model.addAttribute("diagnosesList", diagnoseService.getAllDiagnoses());
        model.addAttribute("successMessage", "Diagnose created successfully!");

        return "diagnose-crud";
    }
}

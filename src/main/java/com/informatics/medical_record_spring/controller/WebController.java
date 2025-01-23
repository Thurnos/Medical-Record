package com.informatics.medical_record_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    // Mapping for displaying the sick-leaves.html page
    @GetMapping("/sick-leaves")
    public String showSickLeavesPage(Model model) {
        return "sick-leaves";
    }

    // Mapping for displaying the visits page
    @GetMapping("/visits")
    public String showVisitsPage(Model model) {
        return "visits";
    }


    // Additional mappings for other pages can be added here
    @GetMapping("/patient-records")
    public String showPatientRecordsPage(Model model) {
        return "patient-records";
    }
}

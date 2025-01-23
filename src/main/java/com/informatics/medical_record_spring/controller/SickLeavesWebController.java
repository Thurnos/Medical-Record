package com.informatics.medical_record_spring.controller;

import com.informatics.medical_record_spring.model.Doctors;
import com.informatics.medical_record_spring.model.SickLeaves;
import com.informatics.medical_record_spring.service.DoctorService;
import com.informatics.medical_record_spring.service.SickLeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class SickLeavesWebController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private SickLeaveService sickLeaveService;


    @GetMapping("/sick-leaves-count")
    public String showSickLeaveCountPage(Model model) {
        // Fetch the list of doctors and add it to the model to be used in the view
        List<Doctors> doctors = doctorService.getAllDoctors();
        model.addAttribute("doctors", doctors);

        return "sick-leaves-count";
    }
    // Serve the sick-leaves-crud.html page and provide doctor and sick leave data
    @GetMapping("/sick-leaves-crud")
    public String showSickLeaveCrudPage(Model model) {
        // Fetch the list of doctors and add it to the model to be used in the view
        List<Doctors> doctors = doctorService.getAllDoctors();
        model.addAttribute("doctors", doctors);

        // Fetch the list of sick leaves and add it to the model
        List<SickLeaves> sickLeaves = sickLeaveService.getAllSickLeaves();
        model.addAttribute("sickLeaves", sickLeaves);

        return "sick-leaves-crud";
    }
}

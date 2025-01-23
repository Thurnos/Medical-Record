package com.informatics.medical_record_spring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TreatmentsWebController {
    @GetMapping("/treatments")
    public String getTreatmentsCrudPage(){
        return "treatments-crud";
    }
}

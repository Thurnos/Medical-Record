package com.informatics.medical_record_spring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reports")
public class ReportingWebController {
    @GetMapping("/statistics")
    public String getStatisticsPage(Model model) {
        return "statistics";
    }
}


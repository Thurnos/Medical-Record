package com.informatics.medical_record_spring.controller;

import com.informatics.medical_record_spring.model.Visits;
import com.informatics.medical_record_spring.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/visits")
public class VisitWebController {

    @Autowired
    private VisitService visitService;

    @GetMapping("/view-all")
    public String viewAllVisits(Model model) {
        List<Object[]> visitDetails = visitService.getVisitDetails();
        model.addAttribute("visitDetails", visitDetails);
        return "visits";
    }
    @GetMapping("/by-doctor-date-range")
    public String showVisitsByDoctorDateRangeForm() {
        return "visits-by-doctor-date-range";
    }
    @GetMapping("/visits-by-date")
    public String showVisitsByDatePage() {
        return "visits-by-date";
    }

//    @GetMapping("/doctor/{doctorId}/date-range")
//    public String getVisitsByDoctorAndDateRange(
//            @PathVariable("doctorId") Long doctorId,
//            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
//            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
//            Model model) {
//
//        // Fetch the visits using the VisitService
//        List<Visit> visits = visitService.findVisitsByDoctorAndDateRange(doctorId, startDate, endDate);
//
//        model.addAttribute("visits", visits);
//        model.addAttribute("doctorId", doctorId);
//        model.addAttribute("startDate", startDate);
//        model.addAttribute("endDate", endDate);
//
//        return "visits-by-doctor-date-range";
//    }

    // Access the CRUD page directly
    @GetMapping("/visit-crud")
    public String showVisitCrudPage(Model model) {
        // Fetch all visits from the service
        List<Visits> visits = visitService.getAllVisits();
        model.addAttribute("visits", visits);
        return "visit-crud";
    }

//    @GetMapping("/visits-by-date/filter")
//    public String getVisitsByDateRange(
//            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
//            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
//            Model model) {
//
//        List<Object[]> visits = visitService.getVisitsWithinDateRange(startDate, endDate);
//
//        model.addAttribute("visits", visits);
//
//        return "visits-by-date";
//    }
    @GetMapping("/visits-by-date/filter")
    public String getVisitsByDateRange(
            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            Model model) {

        // Fetch visits data based on the provided date range
        List<Visits> visits = visitService.getVisitsWithinDate(startDate, endDate);

        model.addAttribute("visits", visits);

        return "visits-by-date";
    }
}

package com.informatics.medical_record_spring.controller;

import com.informatics.medical_record_spring.dao.DoctorDAO;
import com.informatics.medical_record_spring.dto.DoctorsDTO;
import com.informatics.medical_record_spring.model.Doctors;
import com.informatics.medical_record_spring.model.Patients;
import com.informatics.medical_record_spring.model.Visits;
import com.informatics.medical_record_spring.service.DoctorService;
import com.informatics.medical_record_spring.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class DoctorWebController {

    @Autowired
    private DoctorService doctorService;
    @Autowired
    private PatientService patientService;
    @Autowired
    private DoctorDAO doctorDao;

    // Serve the "patients-by-doctor" page with a doctor selection form
    @GetMapping("/patients-by-doctor")
    public String showDoctorSearchForm(Model model) {
        List<Doctors> doctors = doctorService.getAllDoctors();
        model.addAttribute("doctors", doctors);  // Add doctors to the model
        return "patients-by-doctor";
    }
    @GetMapping("/patients-by-doctor-view")
    public String showPatientsByDoctorView() {
        return "patients-by-doctor";
    }
    @GetMapping("/doctors")
    public String viewAllDoctors(Model model) {
        List<Doctors> doctors = doctorService.getAllDoctors();
        model.addAttribute("doctors", doctors);
        return "doctors";
    }

    // This method prepares the doctor list and passes it to the template
    @GetMapping("/view-patient-counts")
    public String getDoctorPatientCountsView(Model model) {
        List<Object[]> doctorPatientCounts = doctorService.getDoctorPatientCounts();

        model.addAttribute("doctorPatientCounts", doctorPatientCounts);
        return "patient-counts";
    }

    // Endpoint to get doctor names with their patient count
    @GetMapping("/patient-counts")
    public String showDoctorPatientCounts(Model model) {
        List<Doctors> doctors = doctorService.getAllDoctors();
        model.addAttribute("doctors", doctors);
        return "patient-counts";
    }
    @GetMapping("/visit-counts")
    public String showDoctorVisitCountsPage(Model model) {
        List<Object[]> doctorVisitCounts = doctorService.getDoctorVisitCounts();
        model.addAttribute("doctorVisitCounts", doctorVisitCounts);

        return "visit-counts";
    }


    // Serve the "patients-by-doctor" page (form for entering the doctor's name)
    @GetMapping("/patients-by-doctor/results")
    public String getPatientsByDoctorName(@RequestParam("doctorName") String doctorName, Model model) {
        List<Object[]> patients = doctorService.getPatientsByDoctorName(doctorName);

        List<Doctors> doctors = doctorService.getAllDoctors();

        // Add data to the model
        model.addAttribute("doctors", doctors);
        model.addAttribute("patients", patients);
        model.addAttribute("doctorName", doctorName);

        return "patients-by-doctor";
    }
    @GetMapping("/doctor/create")
    public String createDoctorForm(Model model) {
        model.addAttribute("doctor", new Doctors());
        return "doctor_form";
    }

    @PostMapping("/doctor/create")
    public String createDoctor(@ModelAttribute Doctors doctor) {
        doctorDao.createDoctor(doctor);
        return "redirect:/doctors";
    }
    @GetMapping("/visits-by-date")
    public String showVisitsByDatePage(Model model) {
        List<Doctors> doctors = doctorService.getAllDoctors();
        model.addAttribute("doctors", doctors);
        return "visits-by-date";
    }

    @GetMapping("/visits-by-date-range")
    @ResponseBody
    public List<Visits> getVisitsByDateRange(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate) {

        List<Visits> visits = getVisitsByDateRange(startDate, endDate);
        return visits;
    }
    @GetMapping("/visits-by-doctor-date-range")
    public String getVisitsByDoctorAndDateRange(
            @RequestParam("doctorId") int doctorId,
            @RequestParam("startDate") String startDateString,
            @RequestParam("endDate") String endDateString,
            Model model) {

        // Convert the start and end dates from String to Date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = sdf.parse(startDateString);
            endDate = sdf.parse(endDateString);
        } catch (ParseException e) {
            e.printStackTrace(); // Handle the exception as needed
        }

        // Call the service or repository to fetch visits for the doctor within the date range
        List<Object[]> visits = doctorService.getVisitsByDoctorAndDateRange(doctorId, startDate, endDate);
        model.addAttribute("visits", visits);
        model.addAttribute("doctorId", doctorId);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);

        return "visits-by-date";
    }
}

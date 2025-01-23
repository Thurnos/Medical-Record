package com.informatics.medical_record_spring.controller;

import com.informatics.medical_record_spring.dto.SickLeavesDTO;
import com.informatics.medical_record_spring.model.SickLeaves;
import com.informatics.medical_record_spring.service.SickLeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/sick-leaves")
public class SickLeaveController {

    @Autowired
    private SickLeaveService sickLeaveService;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static final String URL = "jdbc:mysql://localhost:3306/medical_record";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    // Create a new sick leave
    @PostMapping("/createSickLeave")
    public ResponseEntity<String> createSickLeave(
            @RequestParam int diagnosisId,
            @RequestParam String startDate,
            @RequestParam String endDate) {

        String sql = "INSERT INTO sick_leaves (diagnosis_id, start_date, end_date) VALUES (?, ?, ?)";

        try {
            jdbcTemplate.update(sql, diagnosisId, startDate, endDate);
            return ResponseEntity.status(HttpStatus.CREATED).body("Sick leave saved successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving sick leave: " + e.getMessage());
        }
    }

    // Update an existing sick leave
    @PostMapping("/updateSickLeave")
    public ResponseEntity<String> updateSickLeave(
            @RequestParam int sickLeaveId,
            @RequestParam String newStartDate,
            @RequestParam String newEndDate) {

        String sql = "UPDATE sick_leaves SET start_date = ?, end_date = ? WHERE sick_leave_id = ?";

        try {
            int rowsAffected = jdbcTemplate.update(sql, newStartDate, newEndDate, sickLeaveId);
            if (rowsAffected > 0) {
                return ResponseEntity.ok("Sick leave updated successfully!");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sick leave not found!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating sick leave: " + e.getMessage());
        }
    }

    // Delete a sick leave
    @PostMapping("/deleteSickLeave")
    public ResponseEntity<String> deleteSickLeave(@RequestParam int sickLeaveId) {
        String sql = "DELETE FROM sick_leaves WHERE sick_leave_id = ?";

        try {
            int rowsAffected = jdbcTemplate.update(sql, sickLeaveId);
            if (rowsAffected > 0) {
                return ResponseEntity.ok("Sick leave deleted successfully!");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sick leave not found!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting sick leave: " + e.getMessage());
        }
    }

    // Get all sick leaves
    @GetMapping
    public ResponseEntity<List<SickLeavesDTO>> getAllSickLeaves() {
        List<SickLeaves> sickLeaves = sickLeaveService.getAllSickLeaves();
        List<SickLeavesDTO> sickLeavesDTO = sickLeaves.stream()
                .map(sickLeaveService::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(sickLeavesDTO);
    }
    @GetMapping("/doctor-sick-leave-counts")
    public List<Object[]> getDoctorSickLeaveCounts() {
        return sickLeaveService.getDoctorSickLeaveCounts();
    }

    // Get a sick leave by ID
    @GetMapping("/{id}")
    public ResponseEntity<SickLeavesDTO> getSickLeaveById(@PathVariable Integer id) {
        Optional<SickLeaves> sickLeave = sickLeaveService.getSickLeaveById(id);
        return sickLeave.map(sickLeaveService::convertToDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update a sick leave by ID
//    @PutMapping("/{id}")
//    public ResponseEntity<SickLeavesDTO> updateSickLeave(@PathVariable Integer id, @RequestBody SickLeaves updatedSickLeave) {
//        try {
//            SickLeaves sickLeave = sickLeaveService.updateSickLeave(id, updatedSickLeave);
//            SickLeavesDTO sickLeaveDTO = sickLeaveService.convertToDTO(sickLeave);
//            return ResponseEntity.ok(sickLeaveDTO);
//        } catch (RuntimeException e) {
//            return ResponseEntity.notFound().build();
//        }
//    }
    @GetMapping("/most-sick-leaves-month")
    public Object[] getMonthWithHighestSickLeaves() {
        return sickLeaveService.getMonthWithHighestSickLeaves();
    }

    // Delete a sick leave by ID


}

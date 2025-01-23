package com.informatics.medical_record_spring.controller;

import com.informatics.medical_record_spring.dto.TreatmentsDTO;
import com.informatics.medical_record_spring.model.Treatments;
import com.informatics.medical_record_spring.service.TreatmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.List;

@RestController
@RequestMapping("/api/treatments")
public class TreatmentsController {

    @Autowired
    private TreatmentsService treatmentsService;

    private final String url = "jdbc:mysql://localhost:3306/medical_records";
    private final String user = "root";
    private final String password = "root";

    // Create a new treatment
    public Treatments createTreatment(Treatments treatment) {
        String query = "INSERT INTO treatments (diagnosis_id, treatment_text) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, treatment.getDiagnose().getDiagnosisId());
            stmt.setString(2, treatment.getTreatmentText());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    treatment.setTreatmentId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error creating treatment", e);
        }
        return treatment;
    }

    // Get all treatments
    @GetMapping
    public ResponseEntity<List<TreatmentsDTO>> getAllTreatments() {
        List<TreatmentsDTO> treatments = treatmentsService.getAllTreatments();
        return ResponseEntity.ok(treatments);
    }

    // Get a treatment by ID
    @GetMapping("/{id}")
    public ResponseEntity<TreatmentsDTO> getTreatmentById(@PathVariable Integer id) {
        return treatmentsService.getTreatmentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update a treatment by ID
    public Treatments updateTreatment(int id, Treatments treatment) {
        String query = "UPDATE treatments SET diagnosis_id = ?, treatment_text = ? WHERE treatment_id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, treatment.getDiagnose().getDiagnosisId());
            stmt.setString(2, treatment.getTreatmentText());
            stmt.setInt(3, id);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new RuntimeException("Treatment not found");
            }
            treatment.setTreatmentId(id);
        } catch (SQLException e) {
            throw new RuntimeException("Error updating treatment", e);
        }
        return treatment;
    }

    public void deleteTreatment(int id) {
        String query = "DELETE FROM treatments WHERE treatment_id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting treatment", e);
        }
    }
}

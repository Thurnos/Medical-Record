package com.informatics.medical_record_spring.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.beans.ConstructorProperties;

@Entity
@Table(name = "treatments")
public class Treatments {

    @Id
    @Column(name = "treatment_id")
    private Integer treatmentId;

    @ManyToOne
    @JoinColumn(name = "diagnosis_id",nullable = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "diagnosisId")
    Diagnoses diagnose;

    @Column(name = "treatment_text",nullable = false, length = 255)
    private String treatmentText;

    public Treatments(Integer treatmentId, Integer diagnoseId, String treatmentText) {
    }

    public Treatments() {
    }

    public Integer getTreatmentId() {
        return treatmentId;
    }

    public void setTreatmentId(Integer treatmentId) {
        this.treatmentId = treatmentId;
    }

    public Diagnoses getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(Diagnoses diagnose) {
        this.diagnose = diagnose;
    }

    public String getTreatmentText() {
        return treatmentText;
    }

    public void setTreatmentText(String treatmentText) {
        this.treatmentText = treatmentText;
    }
}

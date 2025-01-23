package com.informatics.medical_record_spring.dto;

import java.util.Date;

public class VisitsDTO {
    private Integer visitId;
    private PatientDTO patient;
    private DoctorsDTO doctor;
    private Date visitDate;

    // Getters and Setters
    public Integer getVisitId() {
        return visitId;
    }

    public void setVisitId(Integer visitId) {
        this.visitId = visitId;
    }

    public PatientDTO getPatient() {
        return patient;
    }

    public void setPatient(PatientDTO patient) {
        this.patient = patient;
    }

    public DoctorsDTO getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorsDTO doctor) {
        this.doctor = doctor;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }


}

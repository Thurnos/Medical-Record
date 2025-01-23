package com.informatics.medical_record_spring.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="visits")
public class Visits {


    @Id
    @Column(name = "visit_id",nullable = false,unique = true)
    private Integer visitId;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = true)
    @JsonBackReference
    private Patients patientId;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = true)
    @JsonBackReference
    private Doctors doctorId;


    @Column(name = "visit_date",nullable = false, length = 255)
    private Date visitDate;


    public Integer getVisitId() {
        return visitId;
    }

    public void setVisitId(Integer visitId) {
        this.visitId = visitId;
    }

    public Patients getPatientId() {
        return patientId;
    }

    public void setPatientId(Patients patientId) {
        this.patientId = patientId;
    }

    public Doctors getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Doctors doctorId) {
        this.doctorId = doctorId;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }


}

package com.informatics.medical_record_spring.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name="qualifications")
public class Qualifications {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qualification_id")
    private Integer qualification_id;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    @JsonBackReference
    private Doctors doctor;

    @Column(name = "qualification_name", nullable = false, length = 255)
    private String qualificationName;

    public Integer getQualification_id() {
        return qualification_id;
    }

    public void setQualification_id(Integer qualification_id) {
        this.qualification_id = qualification_id;
    }

    public Doctors getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctors doctor) {
        this.doctor = doctor;
    }

    public String getQualificationName() {
        return qualificationName;
    }

    public void setQualificationName(String qualificationName) {
        this.qualificationName = qualificationName;
    }
}

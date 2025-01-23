package com.informatics.medical_record_spring.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name="patients")
public class Patients {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id")
    private Integer patientId;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    @Column(name = "name", nullable = false, length = 255, unique = true)
    private String name;

    @Column(name = "egn", nullable = false, length = 10, unique = true) // Length adjusted to match DB schema
    private String egn;

    @Column(name = "is_insured", nullable = false)
    private Boolean isInsured;

    @ManyToOne
    @JoinColumn(name = "personal_doctor_id", nullable = true) // FK to Doctors table
    @JsonIgnore
    private Doctors personalDoctor;


    public Patients(Integer id, String name) {
    }

    public Patients() {

    }


    public Integer getPatientId() {
        return patientId;
    }

    @JsonProperty("userId")
    public Integer getUserId() {
        return user != null ? user.getId() : null;
    }

    @JsonProperty("personalDoctorId")
    public Integer getPersonalDoctorId() {
        return personalDoctor != null ? personalDoctor.getId() : null;
    }
    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEgn() {
        return egn;
    }

    public void setEgn(String egn) {
        this.egn = egn;
    }

    public Boolean getInsured() {
        return isInsured;
    }

    public void setInsured(Boolean insured) {
        isInsured = insured;
    }

    public Doctors getPersonalDoctor() {
        return personalDoctor;
    }

    public void setPersonalDoctor(Doctors personalDoctor) {
        this.personalDoctor = personalDoctor;
    }
}




package com.informatics.medical_record_spring.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "doctors")
public class Doctors {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // FK to Users table
    @JsonIgnore
    private User user;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "specialty", nullable = false, length = 255)
    private String specialty;


    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Qualifications> qualifications;

    @ManyToOne
    @JoinColumn(name = "specialty_id")
    @JsonManagedReference
    private Qualifications specialty_id;

    @OneToMany(mappedBy = "personalDoctor", cascade = CascadeType.ALL)
    @JsonIgnore  // Prevent recursion from Patients to Doctors
    private List<Patients> patients;

    @Column(name = "is_personal_doctor", nullable = false)
    private Boolean isPersonalDoctor = false;

    public Doctors(Integer id, String name) {
    }

    public Doctors() {

    }

    public List<Patients> getPatients() {
        return patients;
    }

    public void setPatients(List<Patients> patients) {
        this.patients = patients;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<Qualifications> getQualifications() {
        return qualifications;
    }

    public void setQualifications(List<Qualifications> qualifications) {
        this.qualifications = qualifications;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public Qualifications getSpecialty_id() {
        return specialty_id;
    }

    public void setSpecialty_id(Qualifications specialty_id) {
        this.specialty_id = specialty_id;
    }

    public Boolean getPersonalDoctor() {
        return isPersonalDoctor;
    }

    public void setPersonalDoctor(Boolean personalDoctor) {
        isPersonalDoctor = personalDoctor;
    }
}
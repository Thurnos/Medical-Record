package com.informatics.medical_record_spring.model;


import jakarta.persistence.*;

@Entity
@Table(name = "medications")
public class Medications {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medication_id")
    private Integer id;

    @Column(name = "name",nullable = false ,length = 255, unique = true)
    private String name;

    @Column(name = "drug_class",nullable = false,length =  255,unique = false)
    private String drugClass;

    @Column(name = "manufacturer",nullable = false, length = 255)
    private String manufacturer;

    @Column(name = "side_effects",nullable = false, length = 255)
    private String sideEffects;

    @Column(name = "dosage_form",nullable = false, length = 255)
    private String dosage_form;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDrugClass() {
        return drugClass;
    }

    public void setDrugClass(String drug_class) {
        this.drugClass = drug_class;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getSideEffects() {
        return sideEffects;
    }

    public void setSideEffects(String side_effects) {
        this.sideEffects = side_effects;
    }

    public String getDosage_form() {
        return dosage_form;
    }

    public void setDosage_form(String dossage_form) {
        this.dosage_form = dossage_form;
    }
}

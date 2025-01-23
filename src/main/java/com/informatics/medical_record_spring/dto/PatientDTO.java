package com.informatics.medical_record_spring.dto;

public class PatientDTO {

    private Integer id;
    private Integer userId;
    private String name;
    private String egn;
    private Boolean isInsured;
    private Integer personalDoctorId;

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public Boolean getIsInsured() {
        return isInsured;
    }

    public void setIsInsured(Boolean isInsured) {
        this.isInsured = isInsured;
    }

    public Integer getPersonalDoctorId() {
        return personalDoctorId;
    }

    public void setPersonalDoctorId(Integer personalDoctorId) {
        this.personalDoctorId = personalDoctorId;
    }
}

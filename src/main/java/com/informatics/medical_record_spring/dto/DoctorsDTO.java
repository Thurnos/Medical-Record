package com.informatics.medical_record_spring.dto;

import java.util.List;

public class DoctorsDTO {

    private Integer id;
    private UserDTO user;
    private String name;
    private List<QualificationsDTO> qualifications; // List of qualifications
    private QualificationsDTO specialty; // Reference to the specialty
    private Boolean isPersonalDoctor;

    public DoctorsDTO() {}

    public DoctorsDTO(Integer id, UserDTO user, String name, List<QualificationsDTO> qualifications, QualificationsDTO specialty, Boolean isPersonalDoctor) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.qualifications = qualifications;
        this.specialty = specialty;
        this.isPersonalDoctor = isPersonalDoctor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<QualificationsDTO> getQualifications() {
        return qualifications;
    }

    public void setQualifications(List<QualificationsDTO> qualifications) {
        this.qualifications = qualifications;
    }

    public QualificationsDTO getSpecialty() {
        return specialty;
    }

    public void setSpecialty(QualificationsDTO specialty) {
        this.specialty = specialty;
    }

    public Boolean getPersonalDoctor() {
        return isPersonalDoctor;
    }

    public void setPersonalDoctor(Boolean personalDoctor) {
        isPersonalDoctor = personalDoctor;
    }
}

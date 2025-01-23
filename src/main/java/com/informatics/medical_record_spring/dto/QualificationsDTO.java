package com.informatics.medical_record_spring.dto;

public class QualificationsDTO {

    private Integer id;
    private String qualificationName;
    private Integer doctorId;

    public QualificationsDTO() {
    }

    public QualificationsDTO(Integer id, String qualificationName, Integer doctorId) {
        this.id = id;
        this.qualificationName = qualificationName;
        this.doctorId = doctorId;
    }


    public QualificationsDTO(Integer qualificationId, String qualificationName) {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQualificationName() {
        return qualificationName;
    }

    public void setQualificationName(String qualificationName) {
        this.qualificationName = qualificationName;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }
}

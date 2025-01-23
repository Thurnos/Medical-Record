package com.informatics.medical_record_spring.dto;

import java.util.Date;

public class SickLeavesDTO {
    private Integer sickLeavesId;
    private DiagnosesDTO diagnose;
    private Date startDate;
    private Date endDate;

    public Integer getSickLeavesId() {
        return sickLeavesId;
    }

    public void setSickLeavesId(Integer sickLeavesId) {
        this.sickLeavesId = sickLeavesId;
    }

    public DiagnosesDTO getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(DiagnosesDTO diagnose) {
        this.diagnose = diagnose;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public SickLeavesDTO(Integer sickLeavesId, DiagnosesDTO diagnose, Date startDate, Date endDate) {
        this.sickLeavesId = sickLeavesId;
        this.diagnose = diagnose;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}

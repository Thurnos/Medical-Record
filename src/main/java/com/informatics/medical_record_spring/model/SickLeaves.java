package com.informatics.medical_record_spring.model;


import jakarta.persistence.*;
import jdk.jshell.Diag;

import java.util.Date;

@Entity
@Table(name = "sick_leaves")
public class SickLeaves {

    @Id
    @Column(name = "sick_leave_id" , nullable = false,unique = true)
    private Integer sickLeavesId;

    @ManyToOne
    @JoinColumn(name = "diagnosis_id", nullable = true)
    private Diagnoses diagnose;

    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Column(name = "end_date", nullable = false)
    private Date endDate;

    public SickLeaves() {

    }


    public Integer getSickLeavesId() {
        return sickLeavesId;
    }

    public void setSickLeavesId(Integer sickLeavesId) {
        this.sickLeavesId = sickLeavesId;
    }

    public Diagnoses getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(Diagnoses diagnose) {
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

    public SickLeaves(Integer sickLeavesId, Diagnoses diagnose, Date startDate, Date endDate) {
        this.sickLeavesId = sickLeavesId;
        this.diagnose = diagnose;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}

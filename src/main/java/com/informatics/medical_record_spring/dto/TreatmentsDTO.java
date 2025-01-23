package com.informatics.medical_record_spring.dto;

public class TreatmentsDTO {

    private Integer treatmentId;
    private Integer diagnoseId;
    private String treatmentText;

    public TreatmentsDTO() {}

    public TreatmentsDTO(Integer treatmentId, Integer diagnoseId, String treatmentText) {
        this.treatmentId = treatmentId;
        this.diagnoseId = diagnoseId;
        this.treatmentText = treatmentText;
    }

    public Integer getTreatmentId() {
        return treatmentId;
    }

    public void setTreatmentId(Integer treatmentId) {
        this.treatmentId = treatmentId;
    }

    public Integer getDiagnoseId() {
        return diagnoseId;
    }

    public void setDiagnoseId(Integer diagnoseId) {
        this.diagnoseId = diagnoseId;
    }

    public String getTreatmentText() {
        return treatmentText;
    }

    public void setTreatmentText(String treatmentText) {
        this.treatmentText = treatmentText;
    }
}

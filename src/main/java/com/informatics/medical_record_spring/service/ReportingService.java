package com.informatics.medical_record_spring.service;

import com.informatics.medical_record_spring.repository.ReportingRepository;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ReportingService {

    private final ReportingRepository reportingRepository;

    public ReportingService(ReportingRepository reportingRepository) {
        this.reportingRepository = reportingRepository;
    }

    public Map<String, Object> getStatistics() {
        return reportingRepository.getAggregatedStatistics();
    }
}
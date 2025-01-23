package com.informatics.medical_record_spring.repository;

import com.informatics.medical_record_spring.model.Medications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicationRepository extends JpaRepository<Medications, Integer> {

}

package com.informatics.medical_record_spring;

import com.informatics.medical_record_spring.model.Doctors;
import com.informatics.medical_record_spring.model.Qualifications;
import com.informatics.medical_record_spring.service.DoctorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DoctorServiceTests {

    @Autowired
    private DoctorService doctorService;


    @Test
    public void testGetAllDoctors() {
        List<Doctors> doctors = doctorService.getAllDoctors();
        assertFalse(doctors.isEmpty());
    }
}


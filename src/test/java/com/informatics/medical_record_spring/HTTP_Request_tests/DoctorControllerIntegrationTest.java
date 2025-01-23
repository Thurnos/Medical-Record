package com.informatics.medical_record_spring.HTTP_Request_tests;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DoctorControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testGetPatientsByDoctor() {
        String doctorName = "Maria Ivanova";

        // When
        ResponseEntity<List> response = restTemplate.getForEntity(
                "http://localhost:8080/api/doctors/patients-by-doctor?doctorName=" + doctorName,
                List.class
        );

        // Then
        assertThat(response.getStatusCodeValue()).isEqualTo(200);  //status code is 200 (OK)
        assertThat(response.getBody()).isNotEmpty();  //check if body is not empty
        List<List<Object>> patients = response.getBody();

        // Check if the first patient  contains patientId, name, etc.
        List<Object> firstPatient = patients.get(0);
        assertThat(firstPatient).isInstanceOf(List.class);
        assertThat(firstPatient.size()).isGreaterThan(0); //the record contains data
    }

    @Test
    void testGetPatientCounts() {
        ResponseEntity<List> response = restTemplate.getForEntity(
                "http://localhost:8080/api/doctors/patient-counts",
                List.class
        );

        assertThat(response.getStatusCodeValue()).isEqualTo(200);  //status code is 200 (OK)
        assertThat(response.getBody()).isNotEmpty();  //body is not empty
        List<List<Object>> counts = response.getBody();

        // Check if the first count record contains doctorName and patientCount
        List<Object> firstCount = counts.get(0);
        assertThat(firstCount).isInstanceOf(List.class);
        assertThat(firstCount.size()).isGreaterThan(0);  //the record contains data
    }

    @Test
    void testGetVisitCounts() {
        // When
        ResponseEntity<List> response = restTemplate.getForEntity(
                "http://localhost:8080/api/doctors/visit-counts",
                List.class
        );

        // Then
        assertThat(response.getStatusCodeValue()).isEqualTo(200);  //code is 200 (OK)
        assertThat(response.getBody()).isNotEmpty();
        List<List<Object>> counts = response.getBody();

        // Check if the first visit count record contains doctorName and visitCount
        List<Object> firstVisitCount = counts.get(0);
        assertThat(firstVisitCount).isInstanceOf(List.class);
        assertThat(firstVisitCount.size()).isGreaterThan(0);  //the record contains data
    }
}

package com.informatics.medical_record_spring.HTTP_Request_tests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PatientControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testGetAllPatientsHistory() {
        ResponseEntity<Object[]> response = restTemplate.getForEntity("/api/patients/all-patients-history", Object[].class);

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().length).isGreaterThan(0); // Assuming data exists
    }

    @Test
    void testGetSpecificPatientHistory() {
        int patientId = 1;
        String url = "/api/patients/" + patientId + "/history";

        ResponseEntity<Object> response = restTemplate.getForEntity(url, Object.class);

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).isNotNull();
    }
}

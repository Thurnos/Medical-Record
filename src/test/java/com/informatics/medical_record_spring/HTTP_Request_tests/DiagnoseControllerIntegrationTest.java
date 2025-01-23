package com.informatics.medical_record_spring.HTTP_Request_tests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DiagnoseControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testGetPatientsAndDiagnoses() {
        String diagnosisText = "Asthma";

        ResponseEntity<List> response = restTemplate.getForEntity(
                "http://localhost:8080/api/diagnoses/patients-diagnoses?diagnosisText=" + diagnosisText,
                List.class
        );

        // Then
        assertThat(response.getStatusCodeValue()).isEqualTo(200); //code 200 ->OK response
        assertThat(response.getBody()).isNotEmpty();

    }
    @Test
    void testGetMostFrequentDiagnosis() {
        // When
        ResponseEntity<List> response = restTemplate.getForEntity(
                "http://localhost:8080/api/diagnoses/most-frequent",
                List.class
        );

        // Then
        assertThat(response.getStatusCodeValue()).isEqualTo(200);  //code is 200 (OK)
        assertThat(response.getBody()).isNotEmpty();  //response body is not empty

        // Check the structure of each item in the response body
        List<Object> responseBody = response.getBody();
        Object firstRecord = responseBody.get(0);

        // Check if it's a List (of diagnosis and frequency)
        assertThat(firstRecord).isInstanceOf(List.class);
        List<Object> record = (List<Object>) firstRecord;

        // Assuming first element is the diagnosis text (String) and second is frequency (Number)
        assertThat(record.get(0)).isInstanceOf(String.class);  // Diagnosis text should be a String
        assertThat(record.get(1)).isInstanceOf(Number.class);  // Frequency should be a Number
    }
}

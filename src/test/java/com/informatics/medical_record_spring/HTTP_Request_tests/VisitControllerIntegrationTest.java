package com.informatics.medical_record_spring.HTTP_Request_tests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VisitControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testGetAllVisits() {

        ResponseEntity<Object[]> response = restTemplate.getForEntity("/api/visits", Object[].class);


        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().length).isGreaterThan(0); // Assuming data exists
    }

    @Test
    void testGetVisitsByDateRange() {
        // Given
        String startDate = "2024-12-01";
        String endDate = "2024-12-31";
        String url = "/api/visits/date-range?startDate=" + startDate + "&endDate=" + endDate;

        ResponseEntity<Object[]> response = restTemplate.getForEntity(url, Object[].class);

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().length).isGreaterThan(0);
    }

    @Test
    void testGetVisitsByDoctorAndDateRange() {
        int doctorId = 1;
        String startDate = "2024-01-01";
        String endDate = "2024-12-31";
        String url = "/api/visits/by-doctor-date-range?doctorId=" + doctorId + "&startDate=" + startDate + "&endDate=" + endDate;

        ResponseEntity<Object[]> response = restTemplate.getForEntity(url, Object[].class);

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().length).isGreaterThan(0); // Assuming data exists for this doctor and date range
    }
}

package com.informatics.medical_record_spring;


import com.informatics.medical_record_spring.controller.DiagnoseController;
import com.informatics.medical_record_spring.controller.DiagnoseWebController;
import com.informatics.medical_record_spring.dto.DiagnosesDTO;
import com.informatics.medical_record_spring.model.Diagnoses;
import com.informatics.medical_record_spring.service.DiagnoseService;
import com.informatics.medical_record_spring.service.DoctorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DiagnoseWebControllerTest {

    private MockMvc mockMvc;

    @Mock
    private DiagnoseService diagnoseService;

    @InjectMocks
    private DiagnoseController diagnoseController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(diagnoseController).build();
    }

    @Test
    void testGetDiagnosesById_NotFound() throws Exception {
        when(diagnoseService.getDiagnoseById(999)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/diagnoses/999"))
                .andExpect(status().isNotFound());
    }


}


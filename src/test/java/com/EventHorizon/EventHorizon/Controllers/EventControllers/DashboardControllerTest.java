package com.EventHorizon.EventHorizon.Controllers.EventControllers;

import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;

import com.EventHorizon.EventHorizon.Controllers.Event.DashboardController;
import com.EventHorizon.EventHorizon.DTOs.FilterDto;
import com.EventHorizon.EventHorizon.Services.Event.DashboardService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {DashboardController.class})
@ExtendWith(SpringExtension.class)
class DashboardControllerTest {

    @Autowired
    private DashboardController dashboardController;
    @MockBean
    private DashboardService dashboardService;


    @Test
    void testGetEventHeaders() throws Exception {
        // Arrange
        when(dashboardService.getFilteredPage(anyInt(), anyInt(),
                Mockito.any(), Mockito.any())).thenReturn(new ArrayList<>());

        FilterDto filterDto = new FilterDto();
        filterDto.setFilters(new ArrayList<>());
        String content = (new ObjectMapper()).writeValueAsString(filterDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/filter/dashboard/{pageIndex}/{pageSize}", 1, 3)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(dashboardController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

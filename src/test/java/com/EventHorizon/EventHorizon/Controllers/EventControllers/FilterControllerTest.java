package com.EventHorizon.EventHorizon.Controllers.EventControllers;

import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;

import com.EventHorizon.EventHorizon.DTOs.FilterDto;
import com.EventHorizon.EventHorizon.Filter.Enums.FilterRelation;
import com.EventHorizon.EventHorizon.Filter.Enums.FilterTypes;
import com.EventHorizon.EventHorizon.Filter.FilterRelationList;
import com.EventHorizon.EventHorizon.Services.EventServices.EventService;
import com.EventHorizon.EventHorizon.Services.EventServices.FilterService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

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

@ContextConfiguration(classes = {FilterController.class})
@ExtendWith(SpringExtension.class)
class FilterControllerTest {
    @MockBean
    private EventService eventService;

    @Autowired
    private FilterController filterController;

    @MockBean
    private FilterService filterService;


    @Test
    void testGetEventHeaders() throws Exception {
        // Arrange
        when(filterService.getFilteredEventHeadersList(anyInt(), anyInt(),
                Mockito.<List<FilterRelationList<FilterTypes, FilterRelation, Object>>>any())).thenReturn(new ArrayList<>());

        FilterDto filterDto = new FilterDto();
        filterDto.setFilters(new ArrayList<>());
        String content = (new ObjectMapper()).writeValueAsString(filterDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/filter/dashboard/{pageIndex}/{pageSize}", 1, 3)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(filterController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetEventHeadersForPending() throws Exception {
        // Arrange
        when(filterService.getFilteredEventHeadersList(anyInt(), anyInt(),
                Mockito.<List<FilterRelationList<FilterTypes, FilterRelation, Object>>>any())).thenReturn(new ArrayList<>());

        FilterDto filterDto = new FilterDto();
        filterDto.setFilters(new ArrayList<>());
        String content = (new ObjectMapper()).writeValueAsString(filterDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/filter/drafted/{pageIndex}/{pageSize}", 1, 3)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(filterController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetEventHeadersForPendingForOrganizer() throws Exception {
        // Arrange
        when(filterService.getFilteredEventHeadersList(anyInt(), anyInt(),
                Mockito.<List<FilterRelationList<FilterTypes, FilterRelation, Object>>>any())).thenReturn(new ArrayList<>());

        FilterDto filterDto = new FilterDto();
        filterDto.setFilters(new ArrayList<>());
        String content = (new ObjectMapper()).writeValueAsString(filterDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/filter/draftedForOrganizer/{pageIndex}/{pageSize}/{organizerId}", 1, 3, 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(filterController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

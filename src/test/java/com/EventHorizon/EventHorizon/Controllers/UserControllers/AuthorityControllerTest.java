package com.EventHorizon.EventHorizon.Controllers.UserControllers;

import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.EventHorizon.EventHorizon.Entities.UserEntities.Client;
import com.EventHorizon.EventHorizon.Entities.enums.Gender;
import com.EventHorizon.EventHorizon.Services.UserServices.AuthorityService;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {AuthorityController.class})
@ExtendWith(SpringExtension.class)
class AuthorityControllerTest {
  @Autowired
  private AuthorityController authorityController;

  @MockBean
  private AuthorityService authorityService;


  @Test
  void testDeleteUser() throws Exception {
    // Arrange
    doNothing().when(authorityService).deleteUser(anyInt());
    MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/authority/deleteUser");
    MockHttpServletRequestBuilder requestBuilder = deleteResult.param("idOfUser", String.valueOf(1));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(authorityController)
            .build()
            .perform(requestBuilder)
            .andExpect(MockMvcResultMatchers.status().isOk());
  }


  @Test
  void testDeleteEvent() throws Exception {
    // Arrange
    doNothing().when(authorityService).deleteEvent(anyInt());
    MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/authority/deleteEvent");
    MockHttpServletRequestBuilder requestBuilder = deleteResult.param("idOfEvent", String.valueOf(1));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(authorityController)
            .build()
            .perform(requestBuilder)
            .andExpect(MockMvcResultMatchers.status().isOk());
  }


  @Test
  void testGetAllUsersByFirstName() throws Exception {
    // Arrange
    when(authorityService.getAllUsersByFirstName(Mockito.<String>any())).thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/authority/getAllUsersByFirstName")
            .param("firstName", "foo");

    // Act and Assert
    MockMvcBuilders.standaloneSetup(authorityController)
            .build()
            .perform(requestBuilder)
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            .andExpect(MockMvcResultMatchers.content().string("[]"));
  }


  @Test
  void testGetAllUsersByLastName() throws Exception {
    // Arrange
    when(authorityService.getAllUsersByLastName(Mockito.<String>any())).thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/authority/getAllUsersByLastName")
            .param("lastName", "foo");

    // Act and Assert
    MockMvcBuilders.standaloneSetup(authorityController)
            .build()
            .perform(requestBuilder)
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            .andExpect(MockMvcResultMatchers.content().string("[]"));
  }

  @Test
  void testGetAllUsersByEmail() throws Exception {
    // Arrange
    when(authorityService.getUserByEmail(Mockito.<String>any())).thenReturn(new Client());
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/authority/getUserByEmail")
            .param("email", "foo");

    // Act and Assert
    MockMvcBuilders.standaloneSetup(authorityController)
            .build()
            .perform(requestBuilder)
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            .andExpect(MockMvcResultMatchers.content().string("{\"id\":0,\"information\":null}"));
  }


  @Test
  void testGetAllUsersByRole() throws Exception {
    // Arrange
    when(authorityService.getAllUsersByGender(Mockito.<Gender>any())).thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/authority/getAllUsersByGender");
    MockHttpServletRequestBuilder requestBuilder = getResult.param("gender", String.valueOf(Gender.MALE));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(authorityController)
            .build()
            .perform(requestBuilder)
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            .andExpect(MockMvcResultMatchers.content().string("[]"));
  }




}

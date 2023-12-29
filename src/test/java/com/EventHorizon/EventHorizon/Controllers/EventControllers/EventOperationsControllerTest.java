package com.EventHorizon.EventHorizon.Controllers.EventControllers;

import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;

import com.EventHorizon.EventHorizon.DTOs.EventDto.DetailedDraftedEventDto;
import com.EventHorizon.EventHorizon.DTOs.EventDto.DetailedEventDto;
import com.EventHorizon.EventHorizon.DTOs.EventDto.EventHeaderDto;
import com.EventHorizon.EventHorizon.DTOs.EventDto.EventRelated.AdsOptionDto;
import com.EventHorizon.EventHorizon.DTOs.UserDto.OrganizerHeaderDto;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Location;
import com.EventHorizon.EventHorizon.Entities.enums.EventType;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventRepositoryServices.LaunchedEventRepositoryService;
import com.EventHorizon.EventHorizon.Services.EventServices.EventService;
import com.EventHorizon.EventHorizon.Services.UserServices.UserTokenInformationService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {EventOperationsController.class})
@ExtendWith(SpringExtension.class)
class EventOperationsControllerTest {
  @Autowired
  private EventOperationsController eventOperationsController;

  @MockBean
  private EventService eventService;

  @MockBean
  private LaunchedEventRepositoryService launchedEventRepositoryService;

  @MockBean
  private UserTokenInformationService userTokenInformationService;

  @Test
  void testGetEventForUser() throws Exception {
    // Arrange
    when(eventService.getEventForUser(anyInt())).thenReturn(new DetailedEventDto());
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/event/eventForUser/{eventId}", 1);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(eventOperationsController)
            .build()
            .perform(requestBuilder)
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            .andExpect(MockMvcResultMatchers.content()
                    .string(
                            "{\"id\":0,\"name\":null,\"description\":null,\"eventCategory\":null,\"eventDate\":null,\"eventType\":null,\"eventAds"
                                    + "\":null,\"eventLocation\":null,\"eventOrganizer\":null,\"seatTypes\":null}"));
  }

  @Test
  void testUpdateEvent() throws Exception {
    // Arrange
    Location eventLocation = new Location();
    eventLocation.setAddress("42 Main St");
    eventLocation.setCity("Oxford");
    eventLocation.setCountry("GB");
    eventLocation.setId(1);

    DetailedDraftedEventDto detailedDraftedEventDto = new DetailedDraftedEventDto();
    detailedDraftedEventDto.setDescription("The characteristics of someone or something");
    AdsOptionDto eventAds = AdsOptionDto.builder().id(1).name("Name").build();
    detailedDraftedEventDto.setEventAds(eventAds);
    detailedDraftedEventDto.setEventCategory("Event Category");
    detailedDraftedEventDto
            .setEventDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    detailedDraftedEventDto.setEventLocation(eventLocation);
    OrganizerHeaderDto eventOrganizer = OrganizerHeaderDto.builder().id(1).name("Name").build();
    detailedDraftedEventDto.setEventOrganizer(eventOrganizer);
    detailedDraftedEventDto.setEventType(EventType.DRAFTEDEVENT);
    detailedDraftedEventDto.setId(1);
    detailedDraftedEventDto.setName("Name");
    detailedDraftedEventDto.setSeatTypes(new ArrayList<>());
    when(eventService.updateEvent(anyInt(), Mockito.<DetailedEventDto>any())).thenReturn(detailedDraftedEventDto);

    Location eventLocation2 = new Location();
    eventLocation2.setAddress("42 Main St");
    eventLocation2.setCity("Oxford");
    eventLocation2.setCountry("GB");
    eventLocation2.setId(1);

    DetailedDraftedEventDto detailedDraftedEventDto2 = new DetailedDraftedEventDto();
    detailedDraftedEventDto2.setDescription("The characteristics of someone or something");
    AdsOptionDto eventAds2 = AdsOptionDto.builder().id(1).name("Name").build();
    detailedDraftedEventDto2.setEventAds(eventAds2);
    detailedDraftedEventDto2.setEventCategory("Event Category");
    detailedDraftedEventDto2
            .setEventDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    detailedDraftedEventDto2.setEventLocation(eventLocation2);
    OrganizerHeaderDto eventOrganizer2 = OrganizerHeaderDto.builder().id(1).name("Name").build();
    detailedDraftedEventDto2.setEventOrganizer(eventOrganizer2);
    detailedDraftedEventDto2.setEventType(EventType.DRAFTEDEVENT);
    detailedDraftedEventDto2.setId(1);
    detailedDraftedEventDto2.setName("Name");
    detailedDraftedEventDto2.setSeatTypes(new ArrayList<>());
    String content = (new ObjectMapper()).writeValueAsString(detailedDraftedEventDto2);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/event/updateEvent/{organizerId}", 1)
            .contentType(MediaType.APPLICATION_JSON)
            .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(eventOperationsController)
            .build()
            .perform(requestBuilder)
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            .andExpect(MockMvcResultMatchers.content()
                    .string(
                            "{\"id\":1,\"name\":\"Name\",\"description\":\"The characteristics of someone or something\",\"eventCategory\":\"Event"
                                    + " Category\",\"eventDate\":0,\"eventType\":\"DRAFTEDEVENT\",\"eventAds\":{\"id\":1,\"name\":\"Name\"},\"eventLocation"
                                    + "\":{\"id\":1,\"country\":\"GB\",\"city\":\"Oxford\",\"address\":\"42 Main St\"},\"eventOrganizer\":{\"id\":1,\"name\":\"Name"
                                    + "\"},\"seatTypes\":[]}"));
  }

  @Test
  void testLaunchEvent() throws Exception {
    // Arrange
    when(eventService.launchEvent(anyInt(), anyInt())).thenReturn(new DetailedEventDto());
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .put("/event/launchEvent/{organizerId}/{eventId}", 1, 1);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(eventOperationsController)
            .build()
            .perform(requestBuilder)
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            .andExpect(MockMvcResultMatchers.content()
                    .string(
                            "{\"id\":0,\"name\":null,\"description\":null,\"eventCategory\":null,\"eventDate\":null,\"eventType\":null,\"eventAds"
                                    + "\":null,\"eventLocation\":null,\"eventOrganizer\":null,\"seatTypes\":null}"));
  }

  @Test
  void testGetEventHeaders() throws Exception {
    // Arrange
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/event/getSeatType/42");

    // Act
    ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(eventOperationsController)
            .build()
            .perform(requestBuilder);

    // Assert
    actualPerformResult.andExpect(MockMvcResultMatchers.status().is(500));
  }


  @Test
  void testGetEventHeaders2() throws Exception {
    // Arrange
    when(eventService.getEventHeadersList(anyInt(), anyInt())).thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/event/dashboard/{pageIndex}/{pageSize}",
            1, 3);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(eventOperationsController)
            .build()
            .perform(requestBuilder)
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            .andExpect(MockMvcResultMatchers.content().string("[]"));
  }

  @Test
  void testGetEventHeaders3() throws Exception {
    // Arrange
    Location eventLocation = new Location();
    eventLocation.setAddress("42 Main St");
    eventLocation.setCity("Oxford");
    eventLocation.setCountry("GB");
    eventLocation.setId(1);
    EventHeaderDto.EventHeaderDtoBuilder eventCategoryResult = EventHeaderDto.builder().eventCategory("Event Category");
    EventHeaderDto.EventHeaderDtoBuilder eventLocationResult = eventCategoryResult
            .eventDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()))
            .eventLocation(eventLocation);
    OrganizerHeaderDto eventOrganizer = OrganizerHeaderDto.builder().id(1).name("Name").build();
    EventHeaderDto buildResult = eventLocationResult.eventOrganizer(eventOrganizer).id(1).name("Name").build();

    ArrayList<EventHeaderDto> eventHeaderDtoList = new ArrayList<>();
    eventHeaderDtoList.add(buildResult);
    when(eventService.getEventHeadersList(anyInt(), anyInt())).thenReturn(eventHeaderDtoList);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/event/dashboard/{pageIndex}/{pageSize}",
            1, 3);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(eventOperationsController)
            .build()
            .perform(requestBuilder)
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            .andExpect(MockMvcResultMatchers.content()
                    .string(
                            "[{\"id\":1,\"name\":\"Name\",\"eventCategory\":\"Event Category\",\"eventDate\":0,\"eventLocation\":{\"id\":1,\"country"
                                    + "\":\"GB\",\"city\":\"Oxford\",\"address\":\"42 Main St\"},\"eventOrganizer\":{\"id\":1,\"name\":\"Name\"}}]"));
  }
}

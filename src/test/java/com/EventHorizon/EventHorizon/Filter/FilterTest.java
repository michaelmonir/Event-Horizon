package com.EventHorizon.EventHorizon.Filter;

import com.EventHorizon.EventHorizon.Entities.EventEntities.AdsOption;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Location;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Organizer;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.Filter.Enums.FilterRelation;
import com.EventHorizon.EventHorizon.Filter.Enums.FilterTypes;
import com.EventHorizon.EventHorizon.Repository.AdsOptionRepositry;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.InformationComponent.InformationRepositoryService;
import com.EventHorizon.EventHorizon.Services.EventService;
import com.EventHorizon.EventHorizon.Services.FilterService;
import com.EventHorizon.EventHorizon.entity.InformationCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class FilterTest {

    @Autowired
    private FilterService filterService;
    @Autowired
    private InformationCreator informationCreator;
    @Autowired
    private EventService eventService;
    @Autowired
    private EventRepositoryService eventRepositoryService;
    @Autowired
    private InformationRepositoryService informationRepositoryService;
    @Autowired
    private AdsOptionRepositry adsOptionRepositry;
    private Event testEvent;
    private Organizer testOrganizer;

    private void initializeTestData() {
        Information testInformation = informationCreator.getInformation(Role.ORGANIZER);
        informationRepositoryService.add(testInformation);
        this.testOrganizer = this.eventService.getOrganizerFromInformationId(testInformation.getId());
        Location testLocation = Location.builder().city("filterCity").country("filterCountry").address("filterAddress").build();
        AdsOption testAdsOption = AdsOption.builder().priority(1).name("FREE").build();
        adsOptionRepositry.save(testAdsOption);
        this.testEvent = Event.builder().name("filterEvent").eventCategory("filterEventCategory").eventAds(testAdsOption).eventLocation(testLocation).
                eventDate(new Date(System.currentTimeMillis())).eventOrganizer(testOrganizer).build();
        this.testEvent.setEventOrganizer(this.testOrganizer);
        eventRepositoryService.saveEventWhenCreatingAndHandleAlreadyExisting(testEvent);
    }

    private void updateList(List<FilterRelationList<FilterTypes, FilterRelation, Object>> list, FilterRelation relation) {
        list.add(new FilterRelationList<>(FilterTypes.NAME, relation, "filterEvent"));
        list.add(new FilterRelationList<>(FilterTypes.CITY, relation, "filterCity"));
        list.add(new FilterRelationList<>(FilterTypes.COUNTRY, relation, "filterCountry"));
        list.add(new FilterRelationList<>(FilterTypes.DATE, relation, new Date(System.currentTimeMillis())));
        list.add(new FilterRelationList<>(FilterTypes.ORGANIZER, relation, testOrganizer.getInformation().getUsername()));
        list.add(new FilterRelationList<>(FilterTypes.CATEGORY, relation, "filterEventCategory"));
        list.add(new FilterRelationList<>(FilterTypes.ADDRESS, relation, "filterAddress"));
    }

    @Test
    public void testANDFilterEvents() {
        this.initializeTestData();
        Event event = this.testEvent;
        List<FilterRelationList<FilterTypes, FilterRelation, Object>> list = new ArrayList<>();
        updateList(list, FilterRelation.AND);
        List<Event> events = filterService.getFilteredEvents(list);
        Assertions.assertEquals(events.get(events.size() - 1).getId(), event.getId());
    }

    @Test
    public void testORFilterEvents() {
        this.initializeTestData();
        Event event = this.testEvent;
        List<FilterRelationList<FilterTypes, FilterRelation, Object>> list = new ArrayList<>();
        updateList(list, FilterRelation.OR);
        List<Event> events = filterService.getFilteredEvents(list);
        Assertions.assertEquals(events.get(events.size() - 1).getId(), event.getId());
    }

    @Test
    public void testFilterWithNoResults() {
        this.initializeTestData();
        List<FilterRelationList<FilterTypes, FilterRelation, Object>> list = new ArrayList<>();
        list.add(new FilterRelationList<>(FilterTypes.NAME, FilterRelation.AND, "filterEvent2"));
        List<Event> events = filterService.getFilteredEvents(list);
        Assertions.assertEquals(events.size(), 0);
    }

    @Test
    void testNoFilter() {
        this.initializeTestData();
        List<FilterRelationList<FilterTypes, FilterRelation, Object>> list = new ArrayList<>();
        List<Event> events = filterService.getFilteredEvents(list);
        List<Event> allEvents = eventRepositoryService.getAllEvents();
        Assertions.assertEquals(events, allEvents);
    }
    @Test
    void testAddressFilter(){
        this.initializeTestData();
        List<FilterRelationList<FilterTypes, FilterRelation, Object>> list = new ArrayList<>();
        list.add(new FilterRelationList<>(FilterTypes.ADDRESS, FilterRelation.AND, "filterAddress"));
        List<Event> events = filterService.getFilteredEvents(list);
        Assertions.assertEquals(events.get(events.size() - 1).getId(), this.testEvent.getId());
    }
    @Test
    void testCategoryFilter(){
        this.initializeTestData();
        List<FilterRelationList<FilterTypes, FilterRelation, Object>> list = new ArrayList<>();
        list.add(new FilterRelationList<>(FilterTypes.CATEGORY, FilterRelation.AND, "filterEventCategory"));
        List<Event> events = filterService.getFilteredEvents(list);
        Assertions.assertEquals(events.get(events.size() - 1).getId(), this.testEvent.getId());
    }
    @Test
    void testCityFilter(){
        this.initializeTestData();
        List<FilterRelationList<FilterTypes, FilterRelation, Object>> list = new ArrayList<>();
        list.add(new FilterRelationList<>(FilterTypes.CITY, FilterRelation.AND, "filterCity"));
        List<Event> events = filterService.getFilteredEvents(list);
        Assertions.assertEquals(events.get(events.size() - 1).getId(), this.testEvent.getId());
    }
    @Test
    void testCountryFilter(){
        this.initializeTestData();
        List<FilterRelationList<FilterTypes, FilterRelation, Object>> list = new ArrayList<>();
        list.add(new FilterRelationList<>(FilterTypes.COUNTRY, FilterRelation.AND, "filterCountry"));
        List<Event> events = filterService.getFilteredEvents(list);
        Assertions.assertEquals(events.get(events.size() - 1).getId(), this.testEvent.getId());
    }
    @Test
    void testDateFilter(){
        this.initializeTestData();
        List<FilterRelationList<FilterTypes, FilterRelation, Object>> list = new ArrayList<>();
        list.add(new FilterRelationList<>(FilterTypes.DATE, FilterRelation.AND, new Date(System.currentTimeMillis())));
        List<Event> events = filterService.getFilteredEvents(list);
        Assertions.assertEquals(events.get(events.size() - 1).getId(), this.testEvent.getId());
    }
    @Test
    void testEventNameFilter(){
        this.initializeTestData();
        List<FilterRelationList<FilterTypes, FilterRelation, Object>> list = new ArrayList<>();
        list.add(new FilterRelationList<>(FilterTypes.NAME, FilterRelation.AND, "filterEvent"));
        List<Event> events = filterService.getFilteredEvents(list);
        Assertions.assertEquals(events.get(events.size() - 1).getId(), this.testEvent.getId());
    }
    @Test
    void testOrganizerFilter(){
        this.initializeTestData();
        List<FilterRelationList<FilterTypes, FilterRelation, Object>> list = new ArrayList<>();
        list.add(new FilterRelationList<>(FilterTypes.ORGANIZER, FilterRelation.AND, this.testOrganizer.getInformation().getUsername()));
        List<Event> events = filterService.getFilteredEvents(list);
        Assertions.assertEquals(events.get(events.size() - 1).getId(), this.testEvent.getId());
    }

}

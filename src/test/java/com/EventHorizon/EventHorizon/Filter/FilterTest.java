package com.EventHorizon.EventHorizon.Filter;

import com.EventHorizon.EventHorizon.Entities.EventEntities.AdsOption;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.EventEntities.LaunchedEvent;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Location;
import com.EventHorizon.EventHorizon.Entities.UpdateUsers.Organizer;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.EntityCustomCreators.UserCustomCreator;
import com.EventHorizon.EventHorizon.Filter.Enums.FilterRelation;
import com.EventHorizon.EventHorizon.Filter.Enums.FilterTypes;
import com.EventHorizon.EventHorizon.Repository.EventRepositories.AdsOptionRepository;
import com.EventHorizon.EventHorizon.Repository.EventRepositories.EventRepository;
import com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices.LaunchedEventRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.User.UserRepositoryService;
import com.EventHorizon.EventHorizon.Services.EventServices.EventService;
import com.EventHorizon.EventHorizon.Services.EventServices.FilterService;
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
    private UserCustomCreator userCustomCreator;
    @Autowired
    private EventService eventService;
    @Autowired
    private LaunchedEventRepositoryService eventRepositoryService;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private UserRepositoryService userRepositoryService;
    @Autowired
    private AdsOptionRepository adsOptionRepository;
    private LaunchedEvent testEvent;
    private Organizer testOrganizer;
    private AdsOption testAdsOption;

    private void createLaunchedEvent() {
        Location testLocation = Location.builder().city("filterCity").country("filterCountry").address("filterAddress").build();
        this.testEvent = LaunchedEvent.builder()
                .name("filterEvent")
                .eventCategory("filterEventCategory").
                eventAds(testAdsOption).
                eventLocation(testLocation).
                eventDate(new Date(System.currentTimeMillis() + 1000L * 1000L * 1000L))
                .seatTypes(new ArrayList<>())
                .eventOrganizer(testOrganizer).build();
    }

    private void initializeTestData() {
        this.testOrganizer = (Organizer) userCustomCreator.getUser(Role.ORGANIZER);
        userRepositoryService.create(testOrganizer);
        testAdsOption = AdsOption.builder().priority(1).name("FREE").build();
        adsOptionRepository.save(testAdsOption);
        createLaunchedEvent();
        this.testEvent.setEventOrganizer(this.testOrganizer);
        eventRepositoryService.saveWhenCreating(testEvent);
    }

    private void updateList(List<FilterRelationList<FilterTypes, FilterRelation, Object>> list, FilterRelation relation) {
        list.add(new FilterRelationList<>(FilterTypes.NAME, relation, "filterEvent"));
        list.add(new FilterRelationList<>(FilterTypes.CITY, relation, "filterCity"));
        list.add(new FilterRelationList<>(FilterTypes.COUNTRY, relation, "filterCountry"));
        list.add(new FilterRelationList<>(FilterTypes.DATE, relation, new Date(System.currentTimeMillis() + 1000L * 1000L * 1000L)));
        list.add(new FilterRelationList<>(FilterTypes.ORGANIZER, relation, testOrganizer.userName));
        list.add(new FilterRelationList<>(FilterTypes.CATEGORY, relation, "filterEventCategory"));
        list.add(new FilterRelationList<>(FilterTypes.ADDRESS, relation, "filterAddress"));
    }

    @Test
    public void testANDFilterEvents() {
        this.initializeTestData();
        Event event = this.testEvent;
        List<FilterRelationList<FilterTypes, FilterRelation, Object>> list = new ArrayList<>();
        updateList(list, FilterRelation.AND);
        List<? extends Event> events = filterService.getFilteredEvents(list);
        Assertions.assertEquals(events.get(events.size() - 1).getId(), event.getId());
    }

    @Test
    public void testORFilterEvents() {
        this.initializeTestData();
        Event event = this.testEvent;
        List<FilterRelationList<FilterTypes, FilterRelation, Object>> list = new ArrayList<>();
        updateList(list, FilterRelation.OR);
        List<? extends Event> events = filterService.getFilteredEvents(list);
        Assertions.assertEquals(events.get(events.size() - 1).getId(), event.getId());
    }

    @Test
    public void testFilterWithNoResults() {
        this.initializeTestData();
        List<FilterRelationList<FilterTypes, FilterRelation, Object>> list = new ArrayList<>();
        list.add(new FilterRelationList<>(FilterTypes.NAME, FilterRelation.AND, "filterEvent2"));
        List<? extends Event> events = filterService.getFilteredEvents(list);
        Assertions.assertEquals(events.size(), 0);
    }

    @Test
    void testNoFilter() {
        this.initializeTestData();
        List<FilterRelationList<FilterTypes, FilterRelation, Object>> list = new ArrayList<>();
        List<? extends Event> events = filterService.getFilteredEvents(list);
        List<? extends Event> allEvents = eventRepository.findAll();
        Assertions.assertEquals(events, allEvents);
    }

    @Test
    void testAddressFilter() {
        this.initializeTestData();
        List<FilterRelationList<FilterTypes, FilterRelation, Object>> list = new ArrayList<>();
        list.add(new FilterRelationList<>(FilterTypes.ADDRESS, FilterRelation.AND, "filterAddress"));
        List<? extends Event> events = filterService.getFilteredEvents(list);
        Assertions.assertEquals(events.get(events.size() - 1).getId(), this.testEvent.getId());
    }

    @Test
    void testCategoryFilter() {
        this.initializeTestData();
        List<FilterRelationList<FilterTypes, FilterRelation, Object>> list = new ArrayList<>();
        list.add(new FilterRelationList<>(FilterTypes.CATEGORY, FilterRelation.AND, "filterEventCategory"));
        List<? extends Event> events = filterService.getFilteredEvents(list);
        Assertions.assertEquals(events.get(events.size() - 1).getId(), this.testEvent.getId());
    }

    @Test
    void testCityFilter() {
        this.initializeTestData();
        List<FilterRelationList<FilterTypes, FilterRelation, Object>> list = new ArrayList<>();
        list.add(new FilterRelationList<>(FilterTypes.CITY, FilterRelation.AND, "filterCity"));
        List<? extends Event> events = filterService.getFilteredEvents(list);
        Assertions.assertEquals(events.get(events.size() - 1).getId(), this.testEvent.getId());
    }

    @Test
    void testCountryFilter() {
        this.initializeTestData();
        List<FilterRelationList<FilterTypes, FilterRelation, Object>> list = new ArrayList<>();
        list.add(new FilterRelationList<>(FilterTypes.COUNTRY, FilterRelation.AND, "filterCountry"));
        List<? extends Event> events = filterService.getFilteredEvents(list);
        Assertions.assertEquals(events.get(events.size() - 1).getId(), this.testEvent.getId());
    }

    @Test
    void testDateFilter() {
        this.initializeTestData();
        List<FilterRelationList<FilterTypes, FilterRelation, Object>> list = new ArrayList<>();
        list.add(new FilterRelationList<>(FilterTypes.DATE, FilterRelation.AND, new Date(System.currentTimeMillis() + 1000L * 1000L * 1000L)));
        List<? extends Event> events = filterService.getFilteredEvents(list);
        Assertions.assertEquals(events.get(events.size() - 1).getId(), this.testEvent.getId());
    }

    @Test
    void testEventNameFilter() {
        this.initializeTestData();
        List<FilterRelationList<FilterTypes, FilterRelation, Object>> list = new ArrayList<>();
        list.add(new FilterRelationList<>(FilterTypes.NAME, FilterRelation.AND, "filterEvent"));
        List<? extends Event> events = filterService.getFilteredEvents(list);
        Assertions.assertEquals(events.get(events.size() - 1).getId(), this.testEvent.getId());
    }

    @Test
    void testOrganizerFilter() {
        this.initializeTestData();
        List<FilterRelationList<FilterTypes, FilterRelation, Object>> list = new ArrayList<>();
        list.add(new FilterRelationList<>(FilterTypes.ORGANIZER, FilterRelation.AND, this.testOrganizer.userName));
        List<? extends Event> events = filterService.getFilteredEvents(list);
        Assertions.assertEquals(events.get(events.size() - 1).getId(), this.testEvent.getId());
    }
}

package com.EventHorizon.EventHorizon.Service.Event;

import com.EventHorizon.EventHorizon.DTOs.EventDto.EventHeaderDto;
import com.EventHorizon.EventHorizon.Entities.Event.Event;
import com.EventHorizon.EventHorizon.Entities.Event.LaunchedEvent;
import com.EventHorizon.EventHorizon.Entities.User.Organizer;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.EntityCustomCreators.User.UserCustomCreator;
import com.EventHorizon.EventHorizon.Exceptions.PagingExceptions.InvalidPageIndexException;
import com.EventHorizon.EventHorizon.Exceptions.PagingExceptions.InvalidPageSizeException;
import com.EventHorizon.EventHorizon.Filter.Enums.FilterRelation;
import com.EventHorizon.EventHorizon.Filter.Enums.FilterTypes;
import com.EventHorizon.EventHorizon.Filter.FilterRelationList;
import com.EventHorizon.EventHorizon.RepositoryServices.Event.Filter.EventViewType;
import com.EventHorizon.EventHorizon.RepositoryServices.Event.Filter.FilterRepositoryService;
import com.EventHorizon.EventHorizon.Services.Event.DashboardService;
import com.EventHorizon.EventHorizon.RepositoryServices.User.UserRepositoryService;
import com.EventHorizon.EventHorizon.Services.Event.FilterService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootTest
class DashboardServiceTest {

    @InjectMocks
    private DashboardService dashboard;
    @Mock
    private FilterRepositoryService filterRepositoryService;
    @Mock
    private FilterService filterService;
    @Autowired
    private UserCustomCreator customCreator;
    @Autowired
    private UserRepositoryService userRepositoryService;

    private LaunchedEvent launchedEvent1;
    private LaunchedEvent launchedEvent2;
    List<FilterRelationList<FilterTypes, FilterRelation, Object>> specifications = new ArrayList<>();

    @Test
    public void testGetPageThrowsExceptionForInvalidPageIndex() {
        int invalidPageIndex = -1;

        Assertions.assertThrows(InvalidPageIndexException.class, () -> {
            dashboard.getFilteredPage(invalidPageIndex, 10, specifications, EventViewType.LAUNCHED);
        });
    }

    @Test
    public void testGetPageReturnsCorrectPages() {
        initialize();
        List<EventHeaderDto> mockEventHeaderDtos = Arrays.asList(
                new EventHeaderDto(launchedEvent1),
                new EventHeaderDto(launchedEvent2)
        );
        int pageIndex = 0;
        int pageSize = 10;
//        Mockito.when(filterRepositoryService.getFilteredEventsHeaderDto(Mockito.any(PageRequest.class), Mockito.any(), Mockito.any(EventViewType.class)))
//                .thenReturn(mockEventHeaderDtos);

        Mockito.when(filterRepositoryService.getFilteredEventsHeaderDto(
                        Mockito.any(), Mockito.any(), Mockito.any()))
                .thenReturn(mockEventHeaderDtos);

        List<EventHeaderDto> result = dashboard.getFilteredPage(pageIndex, pageSize, specifications, EventViewType.LAUNCHED);

        Mockito.verify(filterRepositoryService).getFilteredEventsHeaderDto(
                Mockito.eq(PageRequest.of(pageIndex, pageSize, Sort.by(Sort.Direction.DESC, "eventDate"))), Mockito.any(),
                Mockito.any()
        );
        Assertions.assertEquals(mockEventHeaderDtos, result);
    }

    @Test
    public void testGetPageReturnsEmptyListWhenServiceReturnsEmptyList() {
        int pageIndex = 0;
        int pageSize = 10;
        Mockito.when(filterRepositoryService.getFilteredEventsHeaderDto(
                Mockito.any(PageRequest.class), Mockito.any(), Mockito.any()))
                .thenReturn(Collections.emptyList());
        List<EventHeaderDto> result = dashboard.getFilteredPage(pageIndex, pageSize, specifications, EventViewType.LAUNCHED);
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void testGetPageReturnsErrorForPageSizeZero() {
        int pageIndex = 0;
        int pageSize = 0;
        Assertions.assertThrows(InvalidPageSizeException.class, () -> {
            dashboard.getFilteredPage(pageIndex, pageSize, specifications, EventViewType.LAUNCHED);
        });
    }
    @Test
    public void testGetPageNotReturnError() {
        int pageIndex = 10;
        int pageSize = 25;
        Assertions.assertDoesNotThrow(() -> {
            dashboard.getFilteredPage(pageIndex, pageSize, specifications, EventViewType.LAUNCHED);
        });
    }

    private void initialize(){
        Organizer organizer = (Organizer) customCreator.getUser(Role.ORGANIZER);
        userRepositoryService.create(organizer);
        launchedEvent1 = new LaunchedEvent();
        launchedEvent1.setId(1);
        launchedEvent1.setEventOrganizer(organizer);
        launchedEvent2 = new LaunchedEvent();
        launchedEvent2.setEventOrganizer(organizer);
        launchedEvent2.setId(2);
        Mockito.when(filterService.getSpecifications(Mockito.any(), Mockito.any())).thenReturn(this.getCustomSpecifications());
    }

    private Specification<Event> getCustomSpecifications() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get("id"), -1);
    }
}
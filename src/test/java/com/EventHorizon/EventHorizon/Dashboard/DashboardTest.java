package com.EventHorizon.EventHorizon.Dashboard;

import com.EventHorizon.EventHorizon.DTOs.EventDto.EventHeaderDto;
import com.EventHorizon.EventHorizon.Entities.Event.Event;
import com.EventHorizon.EventHorizon.Entities.Event.LaunchedEvent;
import com.EventHorizon.EventHorizon.Entities.User.Organizer;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.EntityCustomCreators.User.UserCustomCreator;
import com.EventHorizon.EventHorizon.Exceptions.PagingExceptions.InvalidPageIndexException;
import com.EventHorizon.EventHorizon.Exceptions.PagingExceptions.InvalidPageSizeException;
import com.EventHorizon.EventHorizon.RepositoryServices.Event.Filter.FilterRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.Event.Utility.DashboardRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.User.UserRepositoryService;
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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootTest
class DashboardTest {

    @InjectMocks
    private DashboardRepositoryService dashboard;
    @Mock
    private FilterRepositoryService filterRepositoryService;
    @Autowired
    private UserCustomCreator customCreator;
    @Autowired
    private UserRepositoryService userRepositoryService;

    private LaunchedEvent launchedEvent1;
    private LaunchedEvent launchedEvent2;
    private Specification<Event> specifications;

    @Test
    public void testGetPageThrowsExceptionForInvalidPageIndex() {
        int invalidPageIndex = -1;

        Assertions.assertThrows(InvalidPageIndexException.class, () -> {
            dashboard.getFilteredPage(invalidPageIndex, 10, specifications);
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
        Mockito.when(filterRepositoryService.getFilteredEventsHeaderDto(Mockito.any(PageRequest.class), Mockito.any(Specification.class), Mockito.any()))
                .thenReturn(mockEventHeaderDtos);

        List<EventHeaderDto> result = dashboard.getFilteredPage(pageIndex, pageSize, specifications);

        Mockito.verify(filterRepositoryService).getFilteredEventsHeaderDto(
                Mockito.eq(PageRequest.of(pageIndex, pageSize, Sort.by(Sort.Direction.DESC, "eventDate"))),
                Mockito.eq(specifications),
                Mockito.any()
        );
        Assertions.assertEquals(mockEventHeaderDtos, result);
    }

    @Test
    public void testGetPageReturnsEmptyListWhenServiceReturnsEmptyList() {
        int pageIndex = 0;
        int pageSize = 10;
        Mockito.when(filterRepositoryService.getFilteredEventsHeaderDto(
                Mockito.any(PageRequest.class), Mockito.any(Specification.class), Mockito.any()))
                .thenReturn(Collections.emptyList());
        List<EventHeaderDto> result = dashboard.getFilteredPage(pageIndex, pageSize, specifications);
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void testGetPageReturnsErrorForPageSizeZero() {
        int pageIndex = 0;
        int pageSize = 0;
        Assertions.assertThrows(InvalidPageSizeException.class, () -> {
            dashboard.getFilteredPage(pageIndex, pageSize, specifications);
        });
    }
    @Test
    public void testGetPageNotReturnError() {
        int pageIndex = 10;
        int pageSize = 25;
        Assertions.assertDoesNotThrow(() -> {
            dashboard.getFilteredPage(pageIndex, pageSize, specifications);
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
        this.specifications = this.getSpecificationForAll();
    }

    private Specification<Event> getSpecificationForAll() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get("id"), -1);
    }
}
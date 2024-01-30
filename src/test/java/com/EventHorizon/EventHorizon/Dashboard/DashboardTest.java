package com.EventHorizon.EventHorizon.Dashboard;

import com.EventHorizon.EventHorizon.DTOs.EventDto.EventHeaderDto;
import com.EventHorizon.EventHorizon.Entities.EventEntities.LaunchedEvent;
import com.EventHorizon.EventHorizon.Entities.UpdateUsers.Organizer;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.EntityCustomCreators.UserCustomCreator;
import com.EventHorizon.EventHorizon.Exceptions.PagingExceptions.InvalidPageIndexException;
import com.EventHorizon.EventHorizon.Exceptions.PagingExceptions.InvalidPageSizeException;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.DashboardRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventRepositoryServices.LaunchedEventRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.UpdatedUserComponenet.UserRepositoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootTest
class DashboardTest {
    @InjectMocks
    private DashboardRepositoryService dashboard;
    @Mock
    private LaunchedEventRepositoryService launchedEventRepositoryService;
    @Autowired
    private UserCustomCreator customCreator;
    @Autowired
    private UserRepositoryService userRepositoryService;

    private LaunchedEvent launchedEvent1;
    private LaunchedEvent launchedEvent2;

    @Test
    public void testGetPageThrowsExceptionForInvalidPageIndex() {
        int invalidPageIndex = -1;

        Assertions.assertThrows(InvalidPageIndexException.class, () -> {
            dashboard.getPage(invalidPageIndex, 10);
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
        Mockito.when(launchedEventRepositoryService.getAllEventsHeaderDto(Mockito.any(PageRequest.class)))
                .thenReturn(mockEventHeaderDtos);

        List<EventHeaderDto> result = dashboard.getPage(pageIndex, pageSize);
        // Verify that the service method was called with the correct parameters
        Mockito.verify(launchedEventRepositoryService).getAllEventsHeaderDto(
                Mockito.eq(PageRequest.of(pageIndex, pageSize, Sort.by(Sort.Direction.DESC, "eventDate")))
        );
        Assertions.assertEquals(mockEventHeaderDtos, result);
    }

    @Test
    public void testGetPageReturnsEmptyListWhenServiceReturnsEmptyList() {
        int pageIndex = 0;
        int pageSize = 10;
        Mockito.when(launchedEventRepositoryService.getAllEventsHeaderDto(Mockito.any(PageRequest.class)))
                .thenReturn(Collections.emptyList());
        List<EventHeaderDto> result = dashboard.getPage(pageIndex, pageSize);
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void testGetPageReturnsErrorForPageSizeZero() {
        int pageIndex = 0;
        int pageSize = 0;
        Assertions.assertThrows(InvalidPageSizeException.class, () -> {
            dashboard.getPage(pageIndex, pageSize);
        });
    }
    @Test
    public void testGetPageNotReturnError() {
        int pageIndex = 10;
        int pageSize = 25;
        Assertions.assertDoesNotThrow(() -> {
            dashboard.getPage(pageIndex, pageSize);
        });
    }
    private void initialize(){
        Organizer organizer = (Organizer) customCreator.getUser(Role.ORGANIZER);
        userRepositoryService.add(organizer);
        launchedEvent1 = new LaunchedEvent();
        launchedEvent1.setId(1);
        launchedEvent1.setEventOrganizer(organizer);
        launchedEvent2 = new LaunchedEvent();
        launchedEvent2.setEventOrganizer(organizer);
        launchedEvent2.setId(2);
    }
}
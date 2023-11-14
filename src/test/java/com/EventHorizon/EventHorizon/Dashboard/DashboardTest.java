package com.EventHorizon.EventHorizon.Dashboard;

import com.EventHorizon.EventHorizon.EventCreation.Event;
import com.EventHorizon.EventHorizon.EventCreation.EventDto.EventHeaderDto;
import com.EventHorizon.EventHorizon.EventCreation.EventService.EventRepositoryService;
import com.EventHorizon.EventHorizon.Exceptions.InvalidPageIndex;
import com.EventHorizon.EventHorizon.Exceptions.InvalidPageSize;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@DataJpaTest
class DashboardTest {
    @InjectMocks
    private Dashboard dashboard;

    @Mock
    private EventRepositoryService eventRepositoryService;

    @Test
    public void testGetPageThrowsExceptionForInvalidPageIndex() {
        int invalidPageIndex = -1;

        Assertions.assertThrows(InvalidPageIndex.class, () -> {
            dashboard.getPage(invalidPageIndex, 10);
        });
    }

    @Test
    public void testGetPageReturnsCorrectPages() {
        Event event1 = new Event();
        event1.setId(1);
        Event event2 = new Event();
        event1.setId(2);
        List<EventHeaderDto> mockEventHeaderDtos = Arrays.asList(
                new EventHeaderDto(event1),
                new EventHeaderDto(event2)
        );

        int pageIndex = 0;
        int pageSize = 10;
        Mockito.when(eventRepositoryService.getAllEventsHeaderDto(Mockito.any(PageRequest.class)))
                .thenReturn(mockEventHeaderDtos);

        List<EventHeaderDto> result = dashboard.getPage(pageIndex, pageSize);
        // Verify that the service method was called with the correct parameters
        Mockito.verify(eventRepositoryService).getAllEventsHeaderDto(
                Mockito.eq(PageRequest.of(pageIndex, pageSize, Sort.by(Sort.Direction.DESC, "eventDate")))
        );
        Assertions.assertEquals(mockEventHeaderDtos, result);
    }

    @Test
    public void testGetPageReturnsEmptyListWhenServiceReturnsEmptyList() {
        int pageIndex = 0;
        int pageSize = 10;
        Mockito.when(eventRepositoryService.getAllEventsHeaderDto(Mockito.any(PageRequest.class)))
                .thenReturn(Collections.emptyList());
        List<EventHeaderDto> result = dashboard.getPage(pageIndex, pageSize);
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void testGetPageReturnsErrorForPageSizeZero() {
        int pageIndex = 0;
        int pageSize = 0;
        Assertions.assertThrows(InvalidPageSize.class, () -> {
            dashboard.getPage(pageIndex, pageSize);
        });
    }
}
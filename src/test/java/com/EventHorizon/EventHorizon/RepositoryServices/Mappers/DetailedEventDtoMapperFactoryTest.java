package com.EventHorizon.EventHorizon.RepositoryServices.Mappers;

import com.EventHorizon.EventHorizon.Entities.enums.EventType;
import com.EventHorizon.EventHorizon.Mappers.DetailedEventDtos.DetailedDraftedEventDtoMapper;
import com.EventHorizon.EventHorizon.Mappers.DetailedEventDtos.DetailedEventDtoMapper;
import com.EventHorizon.EventHorizon.Mappers.DetailedEventDtos.DetailedEventDtoMapperFactory;
import com.EventHorizon.EventHorizon.Mappers.DetailedEventDtos.DetailedLaunchedEventDtoMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verifyNoInteractions;
@SpringBootTest
class DetailedEventDtoMapperFactoryTest {
    @Mock
    private DetailedDraftedEventDtoMapper detailedDraftedEventDtoMapper;

    @Mock
    private DetailedLaunchedEventDtoMapper detailedLaunchedEventDtoMapper;

    @InjectMocks
    private DetailedEventDtoMapperFactory detailedEventDtoMapperFactory;

    @Test
    void getEventRepositoryServiceByEventType_LaunchedEvent_ReturnsDetailedLaunchedEventDtoMapper() {
        EventType eventType = EventType.LAUNCHEDEVENT;

        DetailedEventDtoMapper result = detailedEventDtoMapperFactory.getEventDtoMapperByEventType(eventType);

        assertEquals(detailedLaunchedEventDtoMapper, result);
        verifyNoInteractions(detailedDraftedEventDtoMapper);
    }

    @Test
    void getEventRepositoryServiceByEventType_DraftedEvent_ReturnsDetailedDraftedEventDtoMapper() {
        EventType eventType = EventType.DRAFTEDEVENT;

        DetailedEventDtoMapper result = detailedEventDtoMapperFactory.getEventDtoMapperByEventType(eventType);

        assertEquals(detailedDraftedEventDtoMapper, result);
        verifyNoInteractions(detailedLaunchedEventDtoMapper);
    }

}
package com.EventHorizon.EventHorizon.RepositoryServices.SeatArchives;

import com.EventHorizon.EventHorizon.Entities.SeatArchive.OrganizerSeatArchive;
import com.EventHorizon.EventHorizon.Entities.SeatArchive.SeatType;
import com.EventHorizon.EventHorizon.Exceptions.SeatArchive.OrganizerSeatArchiveNotFoundException;
import com.EventHorizon.EventHorizon.Exceptions.SeatArchive.OrganizerSeatArchiveShouldHaveIdWhileCreatingException;
import com.EventHorizon.EventHorizon.Repository.SeatArchive.OrganizerSeatArchiveRepository;
import com.EventHorizon.EventHorizon.RepositoryServices.SeatArchive.OrganizerSeatArchiveRepositoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class OrganizerSeatArchiveRepositoryServiceTest
{
    @InjectMocks
    OrganizerSeatArchiveRepositoryService organizerSeatArchiveRepositoryService;
    @Mock
    OrganizerSeatArchiveRepository organizerSeatArchiveRepository;

    private OrganizerSeatArchive customOrganizerSeatArchive;
    private SeatType customSeatType;

    @Test
    public void getByClientIdAndSeatTypeIdFound() {
        this.initializeCustomObjectsAndMocks();
        Mockito.when(this.organizerSeatArchiveRepository.findBySeatTypeId(Mockito.any(Integer.class)))
                .thenReturn(Optional.ofNullable(this.customOrganizerSeatArchive));
        OrganizerSeatArchive result
                = this.organizerSeatArchiveRepositoryService.getOrganizerArchiveByClientIdAndSeatTypeId(this.customSeatType.getId());
        Assertions.assertEquals(this.customOrganizerSeatArchive, result);
    }

    @Test
    public void getByClientIdAndSeatTypeIdNotFound() {
        this.initializeCustomObjectsAndMocks();
        Mockito.when(this.organizerSeatArchiveRepository.findBySeatTypeId(Mockito.any(Integer.class)))
                .thenReturn(Optional.empty());
        Assertions.assertThrows(OrganizerSeatArchiveNotFoundException.class, () ->
                this.organizerSeatArchiveRepositoryService.getOrganizerArchiveByClientIdAndSeatTypeId(this.customSeatType.getId()));
    }

    @Test
    public void saveOrganizerSeatArchiveSuccess() {
        this.initializeCustomObjectsAndMocks();
        Assertions.assertDoesNotThrow(() ->
                this.organizerSeatArchiveRepositoryService
                        .saveOrganizerSeatArchiveWWhenCreating(this.customOrganizerSeatArchive));
    }

    @Test
    public void saveOrganizerSeatArchiveWithIdNotZero() {
        this.initializeCustomObjectsAndMocks();
        this.customOrganizerSeatArchive.setSeatTypeId(5);
        Assertions.assertThrows(OrganizerSeatArchiveShouldHaveIdWhileCreatingException.class,() ->
                this.organizerSeatArchiveRepositoryService
                        .saveOrganizerSeatArchiveWWhenCreating(this.customOrganizerSeatArchive));
    }

    @Test
    public void updateOrganizerSeatArchive() {
        this.initializeCustomObjectsAndMocks();
        Assertions.assertDoesNotThrow(() ->
                this.organizerSeatArchiveRepositoryService
                        .updateOrganizerSeatArchive(this.customOrganizerSeatArchive));
    }

    private void initializeCustomObjectsAndMocks()
    {
        this.customSeatType = new SeatType("seatType", 1);
        this.customOrganizerSeatArchive = new OrganizerSeatArchive(this.customSeatType, 1, 1);

        Mockito.when(this.organizerSeatArchiveRepository.findBySeatTypeId(Mockito.any(Integer.class)))
                .thenReturn(Optional.ofNullable(this.customOrganizerSeatArchive));
    }
}

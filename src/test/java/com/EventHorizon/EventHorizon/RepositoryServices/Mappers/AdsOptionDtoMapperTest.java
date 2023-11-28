package com.EventHorizon.EventHorizon.RepositoryServices.Mappers;

import com.EventHorizon.EventHorizon.DTOs.EventDto.AdsOptionDto;
import com.EventHorizon.EventHorizon.Entities.EventEntities.AdsOption;
import com.EventHorizon.EventHorizon.Exceptions.AdsOptionExceptions.AdsOptionNotFoundException;
import com.EventHorizon.EventHorizon.Repository.AdsOptionRepositry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AdsOptionDtoMapperTest {
    @Mock
    private AdsOptionRepositry adsOptionRepositry;

    @InjectMocks
    private AdsOptionDtoMapper adsOptionDtoMapper;


    @Test
    public void testGetAdsOptionFromDTO_WhenAdsOptionExists_ReturnsAdsOption() {

        AdsOptionDto adsOptionDto = new AdsOptionDto();
        adsOptionDto.setId(1);
        adsOptionDto.setName("free");
        AdsOption adsOption = new AdsOption();
        Mockito.when(adsOptionRepositry.findById(adsOptionDto.getId())).thenReturn(Optional.of(adsOption));

        AdsOption result = adsOptionDtoMapper.getAdsOptionFromDTO(adsOptionDto);

        Assertions.assertEquals(adsOption, result);
    }

    @Test
    public void testGetAdsOptionFromDTO_WhenAdsOptionDoesNotExist_ThrowsAdsOptionNotFoundException() {
        AdsOptionDto adsOptionDto = new AdsOptionDto();
        adsOptionDto.setId(1);
        Mockito.when(adsOptionRepositry.findById(adsOptionDto.getId())).thenReturn(Optional.empty());

        Assertions.assertThrows(AdsOptionNotFoundException.class, () -> {
            adsOptionDtoMapper.getAdsOptionFromDTO(adsOptionDto);
        });
    }

}
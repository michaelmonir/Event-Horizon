package com.EventHorizon.EventHorizon.Mappers.EventMappers;

import com.EventHorizon.EventHorizon.DTOs.SeatArchiveDtos.SeatTypeDto;
import com.EventHorizon.EventHorizon.Entities.SeatArchive.SeatType;
import com.EventHorizon.EventHorizon.Mappers.SeatTypeListMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SeatTypeListMapperTest {

    @Autowired
    private SeatTypeListMapper seatTypeListMapper;

    @Test
    public void testGetSeatTypeListFromDTO() {
        List<SeatTypeDto> seatTypes = List.of(
                new SeatTypeDto("Test Seat Type 1", 10, 1),
                new SeatTypeDto("Test Seat Type 2", 20, 1)
        );
        List<SeatType> result = seatTypeListMapper.getSeatTypeListFromSeatTypeListDTO(seatTypes);
        for (int i = 0; i < seatTypes.size(); i++) {
            SeatTypeDto seatTypeDto = seatTypes.get(i);
            SeatType seatType = result.get(i);
            assert seatTypeDto.getName().equals(seatType.getName());
            assert seatTypeDto.getPrice() == seatType.getPrice();
        }
    }
}

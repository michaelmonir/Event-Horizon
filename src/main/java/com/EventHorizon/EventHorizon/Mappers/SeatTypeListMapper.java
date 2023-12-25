package com.EventHorizon.EventHorizon.Mappers;

import com.EventHorizon.EventHorizon.DTOs.EventDto.SeatTypeDto;
import com.EventHorizon.EventHorizon.Entities.SeatArchive.SeatType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeatTypeListMapper {

    public List<SeatType> getSeatTypeListFromSeatTypeListDTO(List<SeatTypeDto> dtoList) {
        List<SeatType> seatTypeList = new ArrayList<>();
        for (SeatTypeDto dto : dtoList) {
            SeatType seatType = new SeatType(dto.getName(), dto.getPrice());
            seatTypeList.add(seatType);
        }
        return seatTypeList;
    }

    public List<SeatTypeDto> getSeatTypeDtoListFromSeatTypeList(List<SeatType> list) {
        List<SeatTypeDto> dtoList = new ArrayList<>();
        for (SeatType seatType : list) {
            SeatTypeDto dto = new SeatTypeDto(seatType.getName(), seatType.getPrice());
            dtoList.add(dto);
        }
        return dtoList;
    }
}

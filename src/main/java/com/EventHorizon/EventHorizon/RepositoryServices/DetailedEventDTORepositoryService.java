package com.EventHorizon.EventHorizon.RepositoryServices;


import com.EventHorizon.EventHorizon.DTOs.DetailedEventDTO;
import com.EventHorizon.EventHorizon.DTOs.LocationDTO;
import com.EventHorizon.EventHorizon.Entities.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetailedEventDTORepositoryService
{
    @Autowired
    private AdsOptionRepositoryService adsOptionRepositoryService;
}

package com.EventHorizon.EventHorizon.DTOs;

import com.EventHorizon.EventHorizon.Entities.AdsOption;
import com.EventHorizon.EventHorizon.Exceptions.AdsOptionNotFoundException;
import com.EventHorizon.EventHorizon.Repository.AdsOptionRepositry;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@EqualsAndHashCode
public class AdsOptionDTO
{
    @Autowired
    private AdsOptionRepositry adsOptionRepositry;

    public int id;
    public String name;

    public AdsOptionDTO()
    {
    }
}

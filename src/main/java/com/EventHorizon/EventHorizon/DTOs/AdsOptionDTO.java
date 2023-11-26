package com.EventHorizon.EventHorizon.DTOs;

import com.EventHorizon.EventHorizon.Repository.AdsOptionRepositry;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;

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

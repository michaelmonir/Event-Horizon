package com.EventHorizon.EventHorizon.Entities.SeatArchive;

import com.EventHorizon.EventHorizon.Entities.UserEntities.Sponsor;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class SponsorArchiveCompositeKey implements Serializable
{
    private SeatType seatType;
    private Sponsor sponsor;
}

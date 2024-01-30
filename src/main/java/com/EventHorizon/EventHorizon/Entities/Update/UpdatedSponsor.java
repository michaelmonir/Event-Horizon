package com.EventHorizon.EventHorizon.Entities.Update;

import com.EventHorizon.EventHorizon.Entities.SeatArchive.SponsorSeatArchive;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@Table(name = "sponsor")
public class UpdatedSponsor extends UpdatedUser{
//    @OneToMany(mappedBy = "sponsor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private List<SponsorSeatArchive> sponsorSeatArchiveList;

}

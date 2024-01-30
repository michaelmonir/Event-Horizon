package com.EventHorizon.EventHorizon.Entities.UpdateUsers;

import com.EventHorizon.EventHorizon.Entities.SeatArchive.SponsorSeatArchive;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@Table(name = "sponsor")
@EqualsAndHashCode(callSuper = true)
public class Sponsor extends User {
    @OneToMany(mappedBy = "sponsor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<SponsorSeatArchive> sponsorSeatArchiveList;

}

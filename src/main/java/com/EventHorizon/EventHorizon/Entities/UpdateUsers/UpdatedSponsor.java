package com.EventHorizon.EventHorizon.Entities.UpdateUsers;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@Table(name = "sponsor")
public class UpdatedSponsor extends UpdatedUser{
//    @OneToMany(mappedBy = "sponsor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private List<SponsorSeatArchive> sponsorSeatArchiveList;

}

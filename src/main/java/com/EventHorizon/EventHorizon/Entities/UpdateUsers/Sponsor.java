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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Sponsor sponsor = (Sponsor) o;

        List<SponsorSeatArchive> list = sponsor.getSponsorSeatArchiveList();

        if (this.sponsorSeatArchiveList.size() != list.size())
            return false;
        for (int i = 0; i < this.sponsorSeatArchiveList.size(); i++) {
            if (!this.sponsorSeatArchiveList.get(i).equals(list.get(i)))
                return false;
        }
        return true;
    }
}

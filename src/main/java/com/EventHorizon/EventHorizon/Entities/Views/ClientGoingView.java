package com.EventHorizon.EventHorizon.Entities.Views;

import com.EventHorizon.EventHorizon.DTOs.EventDto.EventHeaderDto;
import com.EventHorizon.EventHorizon.Entities.Event.Event;
import com.EventHorizon.EventHorizon.Entities.User.Client;
import com.EventHorizon.EventHorizon.RepositoryServices.Event.Filter.HeaderInterface;
import jakarta.persistence.*;
import lombok.Data;
import net.jcip.annotations.Immutable;

@Data
@Entity(name = "client_going_view")
@Immutable
@IdClass(ClientGoingViewCompositeId.class)
public class ClientGoingView implements HeaderInterface {

    @Id
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    @Id
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    private Event event;

    public EventHeaderDto getHeader() {
        return new EventHeaderDto(event);
    }
}

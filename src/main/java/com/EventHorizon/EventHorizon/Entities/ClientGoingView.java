package com.EventHorizon.EventHorizon.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import net.jcip.annotations.Immutable;

@Entity(name = "client_going_view")
@Immutable
public class ClientGoingView {

    @Id
    private int client_id;
}

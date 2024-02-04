package com.EventHorizon.EventHorizon.Repository;

import com.EventHorizon.EventHorizon.Entities.ClientGoingView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientGoingViewRepository  extends JpaRepository<ClientGoingView, Integer> {

}

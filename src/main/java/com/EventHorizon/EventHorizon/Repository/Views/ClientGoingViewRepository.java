package com.EventHorizon.EventHorizon.Repository.Views;

import com.EventHorizon.EventHorizon.Entities.Event.DraftedEvent;
import com.EventHorizon.EventHorizon.Entities.Views.ClientGoingView;
import com.EventHorizon.EventHorizon.RepositoryServices.Event.Filter.ViewEventInterface;
import jakarta.annotation.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientGoingViewRepository
        extends JpaRepository<ClientGoingView, Integer>
        , JpaSpecificationExecutor<ClientGoingView>
        , ViewEventInterface<ClientGoingView> {
    @Override
    List<ClientGoingView> findAll(@Nullable Specification<ClientGoingView> specification);

    @Override
    Page<ClientGoingView> findAll(Specification<ClientGoingView> spec, Pageable pageable);


}

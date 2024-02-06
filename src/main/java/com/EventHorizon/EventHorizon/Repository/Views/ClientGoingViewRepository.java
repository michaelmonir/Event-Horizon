package com.EventHorizon.EventHorizon.Repository.Views;

import com.EventHorizon.EventHorizon.Entities.Views.ClientGoingView;
import jakarta.annotation.Nullable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientGoingViewRepository  extends JpaRepository<ClientGoingView, Integer> , JpaSpecificationExecutor<ClientGoingView> {
    @Override
    List<ClientGoingView> findAll(@Nullable Specification<ClientGoingView> specification);
}

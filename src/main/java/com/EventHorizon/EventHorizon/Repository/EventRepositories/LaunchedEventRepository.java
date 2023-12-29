package com.EventHorizon.EventHorizon.Repository.EventRepositories;

import com.EventHorizon.EventHorizon.Entities.EventEntities.LaunchedEvent;
import jakarta.annotation.Nullable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface LaunchedEventRepository extends JpaRepository<LaunchedEvent, Integer>, JpaSpecificationExecutor<LaunchedEvent> {
    List<LaunchedEvent> findAll(@Nullable Specification<LaunchedEvent> specification);

//    List<LaunchedEvent> findAll(@Nullable Specification<LaunchedEvent> specification, Pageable pageable);

}

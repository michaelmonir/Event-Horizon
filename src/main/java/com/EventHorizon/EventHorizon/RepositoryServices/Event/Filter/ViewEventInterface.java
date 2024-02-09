package com.EventHorizon.EventHorizon.RepositoryServices.Event.Filter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface ViewEventInterface<T> {
    Page<T> findAll(Specification<T> spec, Pageable pageable);
}

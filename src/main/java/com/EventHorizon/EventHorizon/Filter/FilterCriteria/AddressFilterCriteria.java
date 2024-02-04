package com.EventHorizon.EventHorizon.Filter.FilterCriteria;

import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Filter.Enums.FilterEntityType;
import com.EventHorizon.EventHorizon.Filter.Factories.RootFactory;
import com.EventHorizon.EventHorizon.Filter.FilterCriteriaInterface;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@AllArgsConstructor
public class AddressFilterCriteria implements FilterCriteriaInterface {

    private String address;

    @Override
    public Specification meetCriteria(FilterEntityType entityType) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(RootFactory.getRoot(entityType,root).get("eventLocation").get("address"), "%" + address + "%");
//        return (root, query, criteriaBuilder) ->
//                criteriaBuilder.like(root.get("event").get("eventLocation").get("address"), "%" + address + "%");
    }
}

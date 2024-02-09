package com.EventHorizon.EventHorizon.Filter.FilterCriteria;

import com.EventHorizon.EventHorizon.Entities.Event.Event;
import com.EventHorizon.EventHorizon.Entities.Views.ClientGoingView;
import com.EventHorizon.EventHorizon.Exceptions.Filter.NotGoingViewTypeException;
import com.EventHorizon.EventHorizon.Filter.Enums.FilterEntityType;
import com.EventHorizon.EventHorizon.Filter.FilterCriteriaInterface;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@AllArgsConstructor
public class ClientIdFilterCriteria implements FilterCriteriaInterface {

    private int id;
    @Override
    public Specification<ClientGoingView> meetCriteria(FilterEntityType entityType) {
        if(entityType != FilterEntityType.CLIENT_GOING_VIEW){
            throw new NotGoingViewTypeException();
        }
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("client").get("id")
                        , this.id);
    }
}

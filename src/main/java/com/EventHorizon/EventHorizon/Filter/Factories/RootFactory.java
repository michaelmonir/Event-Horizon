package com.EventHorizon.EventHorizon.Filter.Factories;

import com.EventHorizon.EventHorizon.Filter.Enums.FilterEntityType;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Service;

public class RootFactory {

    public static Root getRoot(FilterEntityType entityClass, Root root) {
        switch (entityClass) {
            case EVENT -> {
                return root;
            }
            case CLIENT_GOING_VIEW -> {
                return (Root) root.get("event");
            }
            default -> {
                return root;
            }
        }
    }
}

package com.EventHorizon.EventHorizon.Entities.enums;

public enum EventType {

    LAUNCHEDEVENT("LAUNCHED_EVENT"),
    DRAFTEDEVENT("DRAFTED_EVENT");

    private String eventType;

    EventType(String eventType) {
        this.eventType = eventType;
    }

    public static EventType fromString(String g) {
        for (EventType r : EventType.values()) {
            if (r.eventType.equals(g)) {
                return r;
            }
        }
        throw new IllegalArgumentException("No enum constant with role: " + g);
    }

    @Override
    public String toString() {
        return eventType;
    }
}

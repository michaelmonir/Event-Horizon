package com.EventHorizon.EventHorizon.security.config;

import lombok.Data;

@Data
public class CommonConfigs {
    static final String[] AUTH_WHITELIST = {
            "/proxy/**",
            "/information/**",
            "/event/eventForUser/**",
            "/event/dashboard/**",
    };
    static final String[] AUTH_ORGANIZER = {
            "/event/EventForOrganizer/**",
            "/event/createEvent/**",
            "/event/updateEvent/**",
            "/event/deleteEvent/**",
    };
}

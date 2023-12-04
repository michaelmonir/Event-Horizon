package com.EventHorizon.EventHorizon.Entities.UserEntities;

public enum Role {
    CLIENT("ROLE_CLIENT"),
    ADMIN("ROLE_ADMIN"),
    MODERATOR(  "ROLE_MODERATOR"),
    SPONSOR("ROLE_SPONSOR"),
    ORGANIZER("ROLE_ORGANIZER");
    private String role;

    Role(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return role;
    }
}

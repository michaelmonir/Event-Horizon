package com.EventHorizon.EventHorizon.Entities.enums;

public enum Role {
    CLIENT("ROLE_CLIENT"),
    ADMIN("ROLE_ADMIN"),
    MODERATOR("ROLE_MODERATOR"),
    SPONSOR("ROLE_SPONSOR"),
    ORGANIZER("ROLE_ORGANIZER");
    private String role;

    Role(String role) {
        this.role = role;
    }
    public static Role fromString(String role) {
        for (Role r : Role.values()) {
            if (r.role.equals(role)) {
                return r;
            }
        }
        throw new IllegalArgumentException("No enum constant with role: " + role);
    }
    @Override
    public String toString() {
        return role;
    }
}

package com.EventHorizon.EventHorizon.Entities.enums;

public enum Gender {
    MALE("MALE"),
    FEMALE("FEMALE"),
    NONE( "NONE");
    private String gender;

    Gender(String gender) {
        this.gender = gender;
    }
    public static Gender fromString(String g) {
        for (Gender r : Gender.values()) {
            if (r.gender.equals(g)) {
                return r;
            }
        }
        throw new IllegalArgumentException("No enum constant with role: " + g);
    }
    @Override
    public String toString() {
        return gender;
    }
}

package ru.kupibilet.search.dto;

public enum TravelClass {
    ECONOMY("Эконом"),
    BUSINESS("Бизнес");

    private final String displayName;

    TravelClass(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

package models;

import java.util.Objects;

public enum PIR_TYPES {
    PLAIN_TEXT("plain-text"),
    TASK("task"),
    EVENT("event"),
    CONTACT("contact");

    private final String value;

    PIR_TYPES(String value) {
        this.value = value;
    }

    public Boolean isEqual(String commandType) {
        return Objects.equals(this.value, commandType);
    }
}

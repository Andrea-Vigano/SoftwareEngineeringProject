package search;

import java.util.Objects;

public enum SEARCH_KEYWORDS {
    AND("AND"),
    OR("OR"),
    NOT("NOT"),
    CONTAINS("CONTAINS"),
    EQUALS("EQUALS"),
    AFTER("GT"),
    BEFORE("LT");

    private final String value;

    SEARCH_KEYWORDS(String value) {
        this.value = value;
    }

    public Boolean isEqual(String commandType) {
        return Objects.equals(this.value, commandType);
    }
}

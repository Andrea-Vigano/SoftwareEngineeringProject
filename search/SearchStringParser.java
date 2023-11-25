package search;

import models.PIR;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public abstract class SearchStringParser<T extends PIR> {
    protected final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
    public SearchCondition<T> parse(String condition) {
        String[] arguments;
        if (this.isAnd(condition)) {
            arguments = this.getArguments(condition);
            SearchCondition<T> conditionA = this.parse(arguments[0]);
            SearchCondition<T> conditionB = this.parse(arguments[1]);
            return conditionA.and(conditionB);
        } else if (this.isOr(condition)) {
            arguments = this.getArguments(condition);
            SearchCondition<T> conditionA = this.parse(arguments[0]);
            SearchCondition<T> conditionB = this.parse(arguments[1]);
            return conditionA.or(conditionB);
        } else if (this.isNot(condition)) {
            SearchCondition<T> conditionA = this.parse(condition.substring(3, condition.length() - 1));
            return conditionA.not();
        } else {
            return this.parseAtomicCondition(condition);
        }
    }

    private Boolean isAnd(String condition) {
        return condition.startsWith(SEARCH_KEYWORDS.AND.toString());
    }

    private Boolean isOr(String condition) {
        return condition.startsWith(SEARCH_KEYWORDS.OR.toString());
    }

    private Boolean isNot(String condition) {
        return condition.startsWith(SEARCH_KEYWORDS.NOT.toString());
    }

    enum StringParsingStates {
        START,
        FOUND_FIRST_BRACKET,
        FOUND_SEPARATION_COMMA,
        FOUND_LAST_BRACKET
    }

    private String[] getArguments(String compositeCondition) {
        StringBuilder argumentA = new StringBuilder(), argumentB = new StringBuilder();
        int indentLevel = 0;
        StringParsingStates state = StringParsingStates.START;
        for (int i = 0; i < compositeCondition.length(); i++) {
            switch (state) {
                case START -> {
                    if (compositeCondition.charAt(i) == '(')
                        state = StringParsingStates.FOUND_FIRST_BRACKET;
                }
                case FOUND_FIRST_BRACKET -> {
                    if (compositeCondition.charAt(i) == '(') {
                        argumentA.append(compositeCondition.charAt(i));
                        indentLevel++;
                    } else if (compositeCondition.charAt(i) == ')') {
                        argumentA.append(compositeCondition.charAt(i));
                        indentLevel--;
                    } else if (compositeCondition.charAt(i) == ',' && indentLevel == 0) {
                        state = StringParsingStates.FOUND_SEPARATION_COMMA;
                    } else {
                        argumentA.append(compositeCondition.charAt(i));
                    }
                }
                case FOUND_SEPARATION_COMMA -> {
                    if (i == compositeCondition.length() - 1) {
                        state = StringParsingStates.FOUND_LAST_BRACKET;
                    } else {
                        argumentB.append(compositeCondition.charAt(i));
                    }
                }
                default -> { }
            }
        }
        return new String[]{ argumentA.toString(), argumentB.toString() };
    }

    protected abstract SearchCondition<T> parseAtomicCondition(String condition);

    protected Boolean isContains(String condition) {
        return condition.startsWith(SEARCH_KEYWORDS.CONTAINS.toString());
    }

    protected Boolean isEquals(String condition) {
        return condition.startsWith(SEARCH_KEYWORDS.EQUALS.toString());
    }

    protected Boolean isAfter(String condition) {
        return condition.startsWith(SEARCH_KEYWORDS.AFTER.toString());
    }

    protected Boolean isBefore(String condition) {
        return condition.startsWith(SEARCH_KEYWORDS.BEFORE.toString());
    }

    protected Boolean isAlarmEqual(String condition) {
        return condition.startsWith(SEARCH_KEYWORDS.ALARMEQUAL.toString());
    }

    protected Boolean isAlarmAfter(String condition) {
        return condition.startsWith(SEARCH_KEYWORDS.ALARMAFTER.toString());
    }

    protected Boolean isAlarmBefore(String condition) {
        return condition.startsWith(SEARCH_KEYWORDS.ALARMBEFORE.toString());
    }

    protected String getContainsString(String condition) {
        // CONTAINS("...")
        return condition.substring(10, condition.length() - 2);
    }

    protected LocalDateTime getEqualsDateTime(String condition) {
        // EQUALS("...")
        String conditionString = condition.substring(7, condition.length() - 1);
        try {
            return LocalDateTime.parse(conditionString, formatter);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    protected LocalDateTime getAfterDateTime(String condition) {
        // AFTER("...")
        String conditionString = condition.substring(6, condition.length() - 1);
        try {
            return LocalDateTime.parse(conditionString, formatter);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    protected LocalDateTime getBeforeDateTime(String condition) {
        // BEFORE("...")
        String conditionString = condition.substring(7, condition.length() - 1);
        try {
            return LocalDateTime.parse(conditionString, formatter);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}

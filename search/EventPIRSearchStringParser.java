package search;

import models.EventPIR;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.function.Function;

public class EventPIRSearchStringParser extends SearchStringParser<EventPIR> {
    @Override
    protected SearchCondition<EventPIR> parseAtomicCondition(String condition) {
        if (this.isContains(condition)) {
            String containsString = this.getContainsString(condition);
            return new SearchCondition<>(pir -> pir.getDescription().contains(containsString));
        } else if (this.isEquals(condition)) {
            LocalDateTime equalsTimeStamp = this.getEqualsDateTime(condition);
            if (equalsTimeStamp == null) return null;
            return new SearchCondition<>(pir -> pir.getStartingTime().isEqual(equalsTimeStamp));
        } else if (this.isAfter(condition)) {
            LocalDateTime equalsTimeStamp = this.getAfterDateTime(condition);
            if (equalsTimeStamp == null) return null;
            return new SearchCondition<>(pir -> pir.getStartingTime().isAfter(equalsTimeStamp));
        } else if (this.isBefore(condition)) {
            LocalDateTime equalsTimeStamp = this.getBeforeDateTime(condition);
            if (equalsTimeStamp == null) return null;
            return new SearchCondition<>(pir -> pir.getStartingTime().isBefore(equalsTimeStamp));
        } else if (this.isAlarmEqual(condition)) {
            LocalDateTime equalsTimeStamp = this.getAlarmEqualsDateTime(condition);
            if (equalsTimeStamp == null) return null;
            return new SearchCondition<>(pir -> {
                for (LocalDateTime alarm : pir.getAlarms()) {
                    if (alarm.isEqual(equalsTimeStamp)) return true;
                }
                return false;
            });
        } else if (this.isAlarmAfter(condition)) {
            LocalDateTime equalsTimeStamp = this.getAlarmAfterDateTime(condition);
            if (equalsTimeStamp == null) return null;
            return new SearchCondition<>(pir -> {
                for (LocalDateTime alarm : pir.getAlarms()) {
                    if (alarm.isAfter(equalsTimeStamp)) return true;
                }
                return false;
            });
        } else if (this.isAlarmBefore(condition)) {
            LocalDateTime equalsTimeStamp = this.getAlarmBeforeDateTime(condition);
            if (equalsTimeStamp == null) return null;
            return new SearchCondition<>(pir -> {
                for (LocalDateTime alarm : pir.getAlarms()) {
                    if (alarm.isBefore(equalsTimeStamp)) return true;
                }
                return false;
            });
        }
        return null;
    }

    private LocalDateTime getAlarmBeforeDateTime(String condition) {
        // ALARMBEFORE("...")
        String conditionString = condition.substring(12, condition.length() - 1);
        try {
            return LocalDateTime.parse(conditionString, formatter);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    private LocalDateTime getAlarmAfterDateTime(String condition) {
        // ALARMBAFTER("...")
        String conditionString = condition.substring(11, condition.length() - 1);
        try {
            return LocalDateTime.parse(conditionString, formatter);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    private LocalDateTime getAlarmEqualsDateTime(String condition) {
        // ALARMEQUALS("...")
        String conditionString = condition.substring(12, condition.length() - 1);
        try {
            return LocalDateTime.parse(conditionString, formatter);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}

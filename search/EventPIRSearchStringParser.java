package search;

import models.EventPIR;

import java.time.LocalDateTime;

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
        }
        return null;
    }
}

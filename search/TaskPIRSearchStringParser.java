package search;

import models.TaskPIR;

import java.time.LocalDateTime;

public class TaskPIRSearchStringParser extends SearchStringParser<TaskPIR> {
    @Override
    protected SearchCondition<TaskPIR> parseAtomicCondition(String condition) {
        if (this.isContains(condition)) {
            String containsString = this.getContainsString(condition);
            return new SearchCondition<>(pir -> pir.getDescription().contains(containsString));
        } else if (this.isEquals(condition)) {
            LocalDateTime equalsTimeStamp = this.getEqualsDateTime(condition);
            if (equalsTimeStamp == null) return null;
            return new SearchCondition<>(pir -> pir.getDeadline().isEqual(equalsTimeStamp));
        } else if (this.isAfter(condition)) {
            LocalDateTime equalsTimeStamp = this.getAfterDateTime(condition);
            if (equalsTimeStamp == null) return null;
            return new SearchCondition<>(pir -> pir.getDeadline().isAfter(equalsTimeStamp));
        } else if (this.isBefore(condition)) {
            LocalDateTime equalsTimeStamp = this.getBeforeDateTime(condition);
            if (equalsTimeStamp == null) return null;
            return new SearchCondition<>(pir -> pir.getDeadline().isBefore(equalsTimeStamp));
        }
        return null;
    }
}

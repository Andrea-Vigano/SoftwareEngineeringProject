package models;

import java.time.LocalDateTime;

public class EventPIR extends PIR {
    private final String description;
    private final LocalDateTime startingTime;
    private final LocalDateTime[] alarms;
    public EventPIR(Integer id, String description, LocalDateTime startingTime, LocalDateTime[] alarms) {
        super(id);
        this.description = description;
        this.startingTime = startingTime;
        this.alarms = alarms;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getStartingTime() {
        return startingTime;
    }

    public LocalDateTime[] getAlarms() {
        return alarms;
    }
}

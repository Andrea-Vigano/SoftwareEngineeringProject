package models;

import java.time.LocalDateTime;

public class TaskPIR extends PIR {
    private final String description;
    private final LocalDateTime deadline;
    public TaskPIR(Integer id, String description, LocalDateTime deadline) {
        super(id);
        this.description = description;
        this.deadline = deadline;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }
}

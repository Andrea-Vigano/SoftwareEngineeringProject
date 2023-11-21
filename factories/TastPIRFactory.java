package factories;

import models.TaskPIR;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TastPIRFactory extends PIRFactory<TaskPIR> {
    @Override
    protected TaskPIR createPIR(Integer id) {
        this.printStream.println("Type the description of the task you wish to store: ");
        String text = this.scanner.nextLine();
        this.printStream.println("Type the deadline of the task you wish to store (YYYY/MM/DD HH:MM [AM or PM]): ");
        String rawTimeStamp = this.scanner.nextLine();
        LocalDateTime deadline;
        try {
            deadline = LocalDateTime.parse(rawTimeStamp, formatter);
        } catch (DateTimeParseException e) {
            this.printStream.println("Unable to parse deadline timestamp: " + rawTimeStamp);
            return null;
        }
        return new TaskPIR(id, text, deadline);
    }
}

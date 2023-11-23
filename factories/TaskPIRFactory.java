package factories;

import models.TaskPIR;

import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.Scanner;

public class TaskPIRFactory extends PIRFactory<TaskPIR> {
    public TaskPIRFactory(Scanner scanner, PrintStream printStream) {
        super(scanner, printStream);
    }

    @Override
    protected TaskPIR createPIR(Integer id) {
        String description = this.getText("Type the description of the task you wish to store: ");
        LocalDateTime deadline = this.getDateTime("Type the deadline of the task you wish to store (YYYY/MM/DD HH:mm): ");
        if (deadline == null) {
            return null;
        }
        return new TaskPIR(id, description, deadline);
    }
}
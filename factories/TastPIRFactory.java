package factories;

import models.TaskPIR;

import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.Scanner;

public class TastPIRFactory extends PIRFactory<TaskPIR> {
    public TastPIRFactory(Scanner scanner, PrintStream printStream) {
        super(scanner, printStream);
    }

    @Override
    protected TaskPIR createPIR(Integer id) {
        String text = this.getText("Type the description of the task you wish to store: ");
        LocalDateTime deadline = this.getTimeStamp("Type the deadline of the task you wish to store (YYYY/MM/DD HH:MM [AM or PM]): ");
        if (deadline == null) return null;
        return new TaskPIR(id, text, deadline);
    }
}

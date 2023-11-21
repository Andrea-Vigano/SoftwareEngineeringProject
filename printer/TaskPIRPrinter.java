package printer;

import models.TaskPIR;

import java.io.PrintStream;

public class TaskPIRPrinter extends PIRPrinter<TaskPIR> {
    public TaskPIRPrinter(PrintStream outputStream) {
        super(outputStream);
    }

    @Override
    public void print(TaskPIR pir) {
        this.outputStream.println(pir.getId() + ": " + pir.getDescription() + "\n\tDue to: " + pir.getDeadline());
    }
}

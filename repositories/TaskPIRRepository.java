package repositories;

import factories.TastPIRFactory;
import models.TaskPIR;
import printer.TaskPIRPrinter;

public class TaskPIRRepository extends PIRRepository<TaskPIR> {
    public TaskPIRRepository() {
        super(new TaskPIRPrinter(System.out), new TastPIRFactory());
    }
}

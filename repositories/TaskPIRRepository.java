package repositories;

import factories.TastPIRFactory;
import models.TaskPIR;
import printer.TaskPIRPrinter;
import search.TaskPIRSearchStringParser;

import java.io.PrintStream;
import java.util.Scanner;

public class TaskPIRRepository extends PIRRepository<TaskPIR> {
    public TaskPIRRepository(Scanner scanner, PrintStream printStream) {
        super(
                new TaskPIRPrinter(printStream),
                new TastPIRFactory(scanner, printStream),
                new TaskPIRSearchStringParser());
    }
}

package repositories;

import factories.EventPIRFactory;
import models.EventPIR;
import printer.EventPIRPrinter;

import java.io.PrintStream;
import java.util.Scanner;

public class EventPIRRepository extends PIRRepository<EventPIR> {
    public EventPIRRepository(Scanner scanner, PrintStream printStream) {
        super(
                new EventPIRPrinter(printStream),
                new EventPIRFactory(scanner, printStream)
        );
    }
}

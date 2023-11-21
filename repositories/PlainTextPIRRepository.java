package repositories;

import factories.PlainTextPIRFactory;
import models.PlainTextPIR;
import printer.PlainTextPIRPrinter;
import search.PlainTextPIRSearchStringParser;

import java.io.PrintStream;
import java.util.Scanner;

public class PlainTextPIRRepository extends PIRRepository<PlainTextPIR> {
    public PlainTextPIRRepository(Scanner scanner, PrintStream printStream) {
        super(
                new PlainTextPIRPrinter(printStream),
                new PlainTextPIRFactory(scanner, printStream),
                new PlainTextPIRSearchStringParser());
    }
}

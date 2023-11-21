package repositories;

import factories.ContactPIRFactory;
import models.ContactPIR;
import printer.ContactPIRPrinter;

import java.io.PrintStream;
import java.util.Scanner;

public class ContactPIRRepository extends PIRRepository<ContactPIR> {
    public ContactPIRRepository(Scanner scanner, PrintStream printStream) {
        super(
                new ContactPIRPrinter(printStream),
                new ContactPIRFactory(scanner, printStream)
        );
    }
}

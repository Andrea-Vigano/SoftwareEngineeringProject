package repositories;

import factories.PlainTextPIRFactory;
import models.PlainTextPIR;
import printer.PlainTextPIRPrinter;
import search.PlainTextPIRSearchStringParser;

import java.io.PrintStream;
import java.util.Objects;
import java.util.Scanner;

public class PlainTextPIRRepository extends PIRRepository<PlainTextPIR> {
    public PlainTextPIRRepository(Scanner scanner, PrintStream printStream) {
        super(
                new PlainTextPIRPrinter(printStream),
                new PlainTextPIRFactory(scanner, printStream),
                new PlainTextPIRSearchStringParser());
    }

    @Override
    protected void loadPlainText(Scanner scanner) {
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();

            // Check for the end marker
            if (line.equals("# --- End of PlainText repository ---")) {
                break;
            }

            String[] parts = line.split(": ", 2);
            if (parts.length == 2) {
                Integer id = Integer.parseInt(parts[0]);
                String text = parts[1];
                PlainTextPIR pir = new PlainTextPIR(id, text);
                replaceOrAdd(pir);
            }
        }
    }

    // New method to replace or add an item with the same ID
    protected void replaceOrAdd(PlainTextPIR newPir) {
        for (PlainTextPIR existingPir : data) {
            if (Objects.equals(existingPir.getId(), newPir.getId())) {
                data.remove(existingPir);
                data.add(newPir);
                return;
            }
        }
        data.add(newPir);
    }
    @Override
    protected void loadTask(Scanner scanner) {}
    @Override
    protected void loadEvent(Scanner scanner) {}
    @Override
    protected void loadContact(Scanner scanner) {}
    @Override
    public void load(Scanner scanner) {}
}
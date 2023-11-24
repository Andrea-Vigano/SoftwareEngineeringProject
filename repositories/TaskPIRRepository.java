package repositories;

import factories.TaskPIRFactory;
import models.TaskPIR;
import printer.TaskPIRPrinter;
import search.TaskPIRSearchStringParser;

import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class TaskPIRRepository extends PIRRepository<TaskPIR> {
    public TaskPIRRepository(Scanner scanner, PrintStream printStream) {
        super(
                new TaskPIRPrinter(printStream),
                new TaskPIRFactory(scanner, printStream),
                new TaskPIRSearchStringParser());
    }

    @Override
    public void load(Scanner scanner) {}

    @Override
    protected void loadPlainText(Scanner scanner) {}

    @Override
    public void loadTask(Scanner scanner) {
        Integer id = null;
        String description = null;
        LocalDateTime deadline = null;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.equals("# --- End of Task repository ---")) {
                break;
            }

            String[] parts = line.split(": ", 2);
            if (parts.length == 2) {
                if (parts[0].matches("\\d+")) {
                    id = Integer.parseInt(parts[0]);
                    description = parts[1];
                }
            }
            if (parts[0].equals("Due to")) {
                String dueToLine = parts[1];

                deadline = parseDeadline(dueToLine);

                if (id != null && description != null) {
                    TaskPIR pir = new TaskPIR(id, description, deadline);
                    replaceOrAdd(pir);

                    // Reset values for the next TaskPIR
                    id = null;
                    description = null;
                    deadline = null;
                }
            }
        }
    }

    @Override
    protected void loadEvent(Scanner scanner) {}

    @Override
    protected void loadContact(Scanner scanner) {}

    private LocalDateTime parseDeadline(String dueToLine) {
        return LocalDateTime.parse(dueToLine, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
    }
}

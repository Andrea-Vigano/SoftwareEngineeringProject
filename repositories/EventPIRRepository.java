package repositories;

import factories.EventPIRFactory;
import models.EventPIR;
import printer.EventPIRPrinter;
import search.EventPIRSearchStringParser;

import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class EventPIRRepository extends PIRRepository<EventPIR> {
    public EventPIRRepository(Scanner scanner, PrintStream printStream) {
        super(
                new EventPIRPrinter(printStream),
                new EventPIRFactory(scanner, printStream),
                new EventPIRSearchStringParser());
    }

    @Override
    public void load(Scanner scanner) {}
    @Override
    protected void loadPlainText(Scanner scanner) {}
    @Override
    protected void loadTask(Scanner scanner) {}

    @Override
    public void loadEvent(Scanner scanner) {
        Integer id = null;
        String description = null;
        LocalDateTime startingTime = null;
        LocalDateTime[] alarms = null;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.equals("# --- End of Event repository ---")) {
                break;
            }

            String[] parts = line.split(": ", 2);
            if (parts.length == 2) {
                if (parts[0].matches("\\d+")) {
                    id = Integer.parseInt(parts[0]);
                    description = parts[1];
                } else if (parts[0].equals("Starting at")) {
                    String startingTimeLine = parts[1];
                    startingTime = parseStartingTime(startingTimeLine);
                } else if (parts[0].equals("With alarms")) {
                    String alarmsLine = parts[1];
                    alarms = parseAlarms(alarmsLine);
                }
            }

            if (id != null && description != null && startingTime != null && alarms != null) {
                EventPIR pir = new EventPIR(id, description, startingTime, alarms);
                replaceOrAdd(pir);

                // Reset values for the next EventPIR
                id = null;
                description = null;
                startingTime = null;
                alarms = null;
            }
        }
    }

    // Helper method to parse alarms
    private LocalDateTime[] parseAlarms(String alarmsLine) {
        // Extract the part between square brackets
        String alarmsPart = alarmsLine.substring(alarmsLine.indexOf('[') + 1, alarmsLine.indexOf(']'));

        // Split the alarms using ", "
        String[] alarmsArray = alarmsPart.split(", ");

        LocalDateTime[] alarms = new LocalDateTime[alarmsArray.length];
        for (int i = 0; i < alarmsArray.length; i++) {
            alarms[i] = LocalDateTime.parse(alarmsArray[i], DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
        }
        return alarms;
    }

    // Helper method to parse starting time
    private LocalDateTime parseStartingTime(String startingTimeLine) {
        return LocalDateTime.parse(startingTimeLine, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
    }

    @Override
    protected void loadContact(Scanner scanner) {}
}

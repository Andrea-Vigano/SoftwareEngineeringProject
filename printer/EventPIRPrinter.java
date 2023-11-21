package printer;

import models.EventPIR;

import java.io.PrintStream;
import java.util.Arrays;

public class EventPIRPrinter extends PIRPrinter<EventPIR> {
    public EventPIRPrinter(PrintStream outputStream) {
        super(outputStream);
    }

    @Override
    public void print(EventPIR pir) {
        this.outputStream.println(pir.getId() + ": " + pir.getDescription() + "\n\tStarting at: " + pir.getStartingTime() + "\n\tWith alarms: " + Arrays.toString(pir.getAlarms()));
    }
}

package factories;

import models.EventPIR;

import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.Scanner;

public class EventPIRFactory extends PIRFactory<EventPIR> {
    public EventPIRFactory(Scanner scanner, PrintStream printStream) {
        super(scanner, printStream);
    }

    @Override
    protected EventPIR createPIR(Integer id) {
        String text = this.getText("Type the description of the event you wish to store: ");
        LocalDateTime startingTime = this.getDateTime("Type the starting time of the event you wish to store (YYYY/MM/DD HH:mm): ");
        if (startingTime == null) return null;

        int alarmsNumber = this.getInteger("How many alarms do you wish to set: ");
        LocalDateTime[] alarms = new LocalDateTime[alarmsNumber];

        for (int i = 0; i < alarmsNumber; i++) {
            LocalDateTime currentAlarm = this.getDateTime("Insert the alarm number " + (i + 1) + " (YYYY/MM/DD HH:mm): ");
            if (currentAlarm == null) return null;
            alarms[i] = currentAlarm;
        }

        return new EventPIR(id, text, startingTime, alarms);
    }
}

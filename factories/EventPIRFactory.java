package factories;

import models.EventPIR;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class EventPIRFactory extends PIRFactory<EventPIR> {
    @Override
    protected EventPIR createPIR(Integer id) {
        this.printStream.println("Type the description of the event you wish to store: ");
        String text = this.scanner.nextLine();
        this.printStream.println("Type the starting time of the event you wish to store (YYYY/MM/DD HH:MM [AM or PM]): ");
        String rawTimeStamp = this.scanner.nextLine();
        LocalDateTime startingTime;
        try {
            startingTime = LocalDateTime.parse(rawTimeStamp, formatter);
        } catch (DateTimeParseException e) {
            this.printStream.println("Unable to parse starting time timestamp: " + rawTimeStamp);
            return null;
        }
        this.printStream.println("How many alarms do you wish to set: ");
        int alarmsNumber = this.scanner.nextInt();
        this.scanner.nextLine();
        LocalDateTime[] alarms = new LocalDateTime[alarmsNumber];
        LocalDateTime currentAlarm;
        String rawAlarm;
        for (int i = 0; i < alarmsNumber; i++) {
            this.printStream.println("Insert the alarm number " + (i + 1) + " (YYYY/MM/DD HH:MM [AM or PM]): ");
            rawAlarm = this.scanner.nextLine();
            try {
                currentAlarm = LocalDateTime.parse(rawAlarm, formatter);
                alarms[i] = currentAlarm;
            } catch (DateTimeParseException e) {
                this.printStream.println("Unable to parse alarm timestamp: " + rawAlarm);
                return null;
            }
        }
        return new EventPIR(id, text, startingTime, alarms);
    }
}

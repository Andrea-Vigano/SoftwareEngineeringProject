package factories;

import models.PIR;

import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public abstract class PIRFactory<T extends PIR> {
    protected Scanner scanner;
    protected PrintStream printStream;
    protected final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a");

    public PIRFactory() {
        this.scanner = new Scanner(System.in);
        this.printStream = System.out;
    }

    public T createPIR() {
        int id = this.getId();
        return this.createPIR(id);
    }

    protected abstract T createPIR(Integer id);

    private Integer getId() {
        printStream.println("Insert the id: ");
        int id = this.scanner.nextInt();
        this.scanner.nextLine();
        return id;
    }

    protected LocalDateTime getTimeStamp(String message) {
        this.printStream.println(message);
        String rawTimeStamp = this.scanner.nextLine();
        try {
            return LocalDateTime.parse(rawTimeStamp, formatter);
        } catch (DateTimeParseException e) {
            this.printStream.println("Unable to parse timestamp: " + rawTimeStamp);
            return null;
        }
    }

    protected String getText(String message) {
        this.printStream.println(message);
        return this.scanner.nextLine();
    }

    protected int getInteger(String message) {
        this.printStream.println(message);
        int value = this.scanner.nextInt();
        this.scanner.nextLine();
        return value;
    }
}

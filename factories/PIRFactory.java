package factories;

import models.PIR;

import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class PIRFactory<T extends PIR> {
    protected Scanner scanner;
    protected PrintStream printStream;

    public PIRFactory(Scanner scanner, PrintStream printStream) {
        this.scanner = scanner;
        this.printStream = printStream;
    }

    public T createPIR() {
        Integer id = this.getId();
        if(id != null)
            return this.createPIR(id);
        return null;
    }

    protected abstract T createPIR(Integer id);

    private Integer getId() {
        printStream.println("Insert the id: ");
        try {
            int id = this.scanner.nextInt();
            this.scanner.nextLine();
            return id;
        } catch (InputMismatchException e) {
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

    protected LocalDateTime getDateTime(String prompt) {
        try {
            System.out.print(prompt);
            String input = this.scanner.nextLine();
            return LocalDateTime.parse(input, DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
        } catch (DateTimeParseException e) {
            System.out.println("Unable to parse timestamp. Please use the format YYYY/MM/DD HH:mm.");
            return null;
        }
    }
}

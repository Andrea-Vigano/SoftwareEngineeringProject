package factories;

import models.PIR;

import java.io.PrintStream;
import java.util.Scanner;

public abstract class PIRFactory<T extends PIR> {
    protected Scanner scanner;
    protected PrintStream printStream;

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
}

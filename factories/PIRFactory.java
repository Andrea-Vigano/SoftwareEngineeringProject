package factories;

import models.PIR;

import java.io.PrintStream;
import java.util.Scanner;

public abstract class PIRFactory {
    protected Scanner scanner;
    protected PrintStream printStream;

    public PIRFactory() {
        this.scanner = new Scanner(System.in);
        this.printStream = System.out;
    }

    public PIR createPIRForAdd() {
        int id = this.getId();
        return this.createPIRForAdd(id);
    }

    protected abstract PIR createPIRForAdd(Integer id);
    public PIR createPIRForEdit() {
        int id = this.getId();
        return this.createPIRForEdit(id);
    }
    protected abstract PIR createPIRForEdit(Integer id);

    private Integer getId() {
        printStream.println("Insert the id: ");
        int id = this.scanner.nextInt();
        this.scanner.nextLine();
        return id;
    }
}

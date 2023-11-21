package printer;

import models.IPIR;

import java.io.PrintStream;

public abstract class PIRPrinter {
    protected final PrintStream outputStream;
    public PIRPrinter(PrintStream outputStream) {
        this.outputStream = outputStream;
    }
    public abstract void print(IPIR pir);
}

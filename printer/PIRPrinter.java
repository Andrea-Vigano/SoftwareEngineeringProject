package printer;

import models.PIR;

import java.io.PrintStream;

public abstract class PIRPrinter<T extends PIR> {
    protected final PrintStream outputStream;
    public PIRPrinter(PrintStream outputStream) {
        this.outputStream = outputStream;
    }
    public abstract void print(T pir);
}

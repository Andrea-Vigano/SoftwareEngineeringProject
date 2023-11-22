package printer;

import models.PIR;

import java.io.PrintStream;

public abstract class PIRPrinter<T extends PIR> {
    protected PrintStream outputStream;

    public PIRPrinter(PrintStream outputStream) {
        this.outputStream = outputStream;
    }

    public abstract void print(T pir);

    public PrintStream setPrintStream(PrintStream newStream) {
        PrintStream original_printStream = outputStream;
        this.outputStream = newStream;
        return original_printStream;
    }
}

package printer;

import models.PlainTextPIR;

import java.io.PrintStream;

public class PlainTextPIRPrinter extends PIRPrinter<PlainTextPIR> {
    public PlainTextPIRPrinter(PrintStream outputStream) {
        super(outputStream);
    }

    @Override
    public void print(PlainTextPIR pir) {
        this.outputStream.println(pir.getId() + ": " + pir.getText());
    }
}

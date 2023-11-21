package printer;

import models.PIR;
import models.PlainTextPIR;

import java.io.PrintStream;

public class PlainTextPIRPrinter extends PIRPrinter {
    public PlainTextPIRPrinter(PrintStream outputStream) {
        super(outputStream);
    }

    @Override
    public void print(PIR pir) {
        try {
            PlainTextPIR plainTextPIR = (PlainTextPIR) pir;
            this.outputStream.println(plainTextPIR.getId() + ": " + plainTextPIR.getText());
        } catch (Exception e) {
            this.outputStream.println("An internal error occurred");
        }
    }
}

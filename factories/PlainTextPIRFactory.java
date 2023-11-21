package factories;

import models.PlainTextPIR;

import java.io.PrintStream;
import java.util.Scanner;

public class PlainTextPIRFactory extends PIRFactory<PlainTextPIR> {
    public PlainTextPIRFactory(Scanner scanner, PrintStream printStream) {
        super(scanner, printStream);
    }

    @Override
    protected PlainTextPIR createPIR(Integer id) {
        String text = this.getText("Type the new content you wish to store: ");
        return new PlainTextPIR(id, text);
    }
}

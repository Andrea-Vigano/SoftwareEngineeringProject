package factories;

import models.PlainTextPIR;

public class PlainTextPIRFactory extends PIRFactory<PlainTextPIR> {
    @Override
    protected PlainTextPIR createPIR(Integer id) {
        this.printStream.println("Type the new content you wish to store: ");
        String text = this.scanner.nextLine();
        return new PlainTextPIR(id, text);
    }
}

package factories;

import models.PIR;
import models.PlainTextPIR;

public class PlainTextPIRFactory extends PIRFactory {

    @Override
    protected PIR createPIRForAdd(Integer id) {
        this.printStream.println("Type the content you wish to store: ");
        String text = this.scanner.nextLine();
        return new PlainTextPIR(id, text);
    }

    @Override
    protected PIR createPIRForEdit(Integer id) {
        this.printStream.println("Type the new content you wish to store: ");
        String text = this.scanner.nextLine();
        return new PlainTextPIR(id, text);
    }
}

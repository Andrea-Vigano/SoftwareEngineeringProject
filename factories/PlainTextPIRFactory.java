package factories;

import models.PlainTextPIR;

public class PlainTextPIRFactory extends PIRFactory<PlainTextPIR> {
    @Override
    protected PlainTextPIR createPIR(Integer id) {
        String text = this.getText("Type the new content you wish to store: ");
        return new PlainTextPIR(id, text);
    }
}

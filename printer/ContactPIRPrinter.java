package printer;

import models.ContactPIR;

import java.io.PrintStream;

public class ContactPIRPrinter extends PIRPrinter<ContactPIR> {
    public ContactPIRPrinter(PrintStream outputStream) {
        super(outputStream);
    }

    @Override
    public void print(ContactPIR pir) {
        this.outputStream.println(pir.getId() + ": " + pir.getName() + "\n\t" + pir.getAddress() + "\n\t" + pir.getMobilePhoneNumber());
    }
}

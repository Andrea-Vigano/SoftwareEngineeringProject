package factories;

import models.ContactPIR;

// TODO safe inputs on address and phone number
public class ContactPIRFactory extends PIRFactory<ContactPIR> {

    @Override
    protected ContactPIR createPIR(Integer id) {
        this.printStream.println("Type the name of the contact you wish to store: ");
        String name = this.scanner.nextLine();
        this.printStream.println("Type the address of the contact you wish to store: ");
        String address = this.scanner.nextLine();
        this.printStream.println("Type the phone number of the contact you wish to store: ");
        String phoneNumber = this.scanner.nextLine();
        return new ContactPIR(id, name, address, phoneNumber);
    }
}

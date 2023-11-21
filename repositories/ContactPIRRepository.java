package repositories;

import factories.ContactPIRFactory;
import models.ContactPIR;
import printer.ContactPIRPrinter;

public class ContactPIRRepository extends PIRRepository<ContactPIR> {
    public ContactPIRRepository() {
        super(new ContactPIRPrinter(System.out), new ContactPIRFactory());
    }
}

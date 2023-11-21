package repositories;

import factories.EventPIRFactory;
import models.EventPIR;
import printer.EventPIRPrinter;

public class EventPIRRepository extends PIRRepository<EventPIR> {
    public EventPIRRepository() {
        super(new EventPIRPrinter(System.out), new EventPIRFactory());
    }
}

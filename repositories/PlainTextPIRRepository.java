package repositories;

import factories.PlainTextPIRFactory;
import models.PlainTextPIR;
import printer.PlainTextPIRPrinter;

public class PlainTextPIRRepository extends PIRRepository<PlainTextPIR> {
    public PlainTextPIRRepository() {
        super(new PlainTextPIRPrinter(System.out), new PlainTextPIRFactory());
    }
};

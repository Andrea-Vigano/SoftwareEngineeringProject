package repositories;

import factories.PlainTextPIRFactory;
import models.PIR;
import models.PlainTextPIR;
import printer.PlainTextPIRPrinter;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class PlainTextPIRRepository extends PIRRepository {
    private final ArrayList<PlainTextPIR> data = new ArrayList<>();

    public PlainTextPIRRepository() {
        this.printer = new PlainTextPIRPrinter(System.out);
        this.pirFactory = new PlainTextPIRFactory();
    }

    @Override
    protected Boolean isUniqueId(PIR pir) {
        for (PIR storedPir : this.data) {
            if (Objects.equals(storedPir.getId(), pir.getId()))
                return false;
        }
        return true;
    }

    @Override
    public Boolean performAdd(PIR pir) {
        try {
            data.add((PlainTextPIR) pir);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public Boolean remove(Integer pirId) {
        for (PlainTextPIR pir : data) {
            if (Objects.equals(pir.getId(), pirId)) {
                data.remove(pir);
                return true;
            }
        }
        return false;
    }

    @Override
    public PIR find(Integer pirId) {
        for (PIR pir : this.data) {
            if (Objects.equals(pir.getId(), pirId))
                return pir;
        }
        return null;
    }
}

package repositories;

import factories.PIRFactory;
import models.PIR;
import printer.PIRPrinter;

import java.util.ArrayList;
import java.util.Objects;

public abstract class PIRRepository<T extends PIR> {
    protected PIRPrinter<T> printer;
    protected PIRFactory<T> pirFactory;
    protected final ArrayList<T> data = new ArrayList<>();

    public PIRRepository(PIRPrinter<T> printer, PIRFactory<T> pirFactory) {
        this.printer = printer;
        this.pirFactory = pirFactory;
    }
    protected Boolean add(T pir) {
        if (!isUniqueId(pir))
            return false;
        return this.performAdd(pir);
    }
    public Boolean createAndAdd() {
        T pir = this.pirFactory.createPIR();
        return this.add(pir);
    }
    protected Boolean isUniqueId(T pir) {
        for (T storedPir : data) {
            if (Objects.equals(storedPir.getId(), pir.getId()))
                return false;
        }
        return true;
    }
    protected Boolean performAdd(T pir) {
        try {
            data.add(pir);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public Boolean remove(Integer pirId) {
        for (T pir : data) {
            if (Objects.equals(pir.getId(), pirId)) {
                data.remove(pir);
                return true;
            }
        }
        return false;
    }
    protected T find(Integer pirId) {
        for (T pir : data) {
            if (Objects.equals(pir.getId(), pirId))
                return pir;
        }
        return null;
    }
    public Boolean findAndPrint(Integer pirId) {
        T pir = this.find(pirId);
        if (pir != null) {
            this.print(pir);
            return true;
        }
        return false;
    }
    protected Boolean edit(T pir) {
        Boolean result = this.remove(pir.getId());
        if (!result) return false;
        return this.add(pir);
    }
    public Boolean createAndEdit() {
        T pir = this.pirFactory.createPIR();
        return this.edit(pir);
    }
    public void print(T pir) {
        this.printer.print(pir);
    }
}

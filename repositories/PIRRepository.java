package repositories;

import factories.PIRFactory;
import models.IPIR;
import printer.PIRPrinter;

import java.util.ArrayList;
import java.util.Objects;

public abstract class PIRRepository<T extends IPIR> {
    protected PIRPrinter printer;
    protected PIRFactory<T> pirFactory;
    protected final ArrayList<T> data = new ArrayList<>();
    public PIRRepository(PIRPrinter printer, PIRFactory<T> pirFactory) {
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
    protected Boolean isUniqueId(IPIR pir) {
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
    public IPIR find(Integer pirId) {
        for (T pir : data) {
            if (Objects.equals(pir.getId(), pirId))
                return pir;
        }
        return null;
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
    public void print(IPIR pir) {
        this.printer.print(pir);
    }
}

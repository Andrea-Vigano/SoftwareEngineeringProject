package repositories;

import factories.PIRFactory;
import models.PIR;
import printer.PIRPrinter;
// TODO bring up implementation of remove, find and isUniqueId with data iterator abstract method
public abstract class PIRRepository {
    protected PIRPrinter printer;
    protected PIRFactory pirFactory;
    protected Boolean add(PIR pir) {
        if (!isUniqueId(pir))
            return false;
        return this.performAdd(pir);
    }
    public Boolean createAndAdd() {
        PIR pir = this.pirFactory.createPIRForAdd();
        return this.add(pir);
    }
    protected abstract Boolean isUniqueId(PIR pir);
    protected abstract Boolean performAdd(PIR pir);
    public abstract Boolean remove(Integer pirId);
    public abstract PIR find(Integer pirId);
    protected Boolean edit(PIR pir) {
        Boolean result = this.remove(pir.getId());
        if (!result) return false;
        return this.add(pir);
    }
    public Boolean createAndEdit() {
        PIR pir = this.pirFactory.createPIRForEdit();
        return this.edit(pir);
    }
    public void print(PIR pir) {
        this.printer.print(pir);
    }
}

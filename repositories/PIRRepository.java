package repositories;

import factories.PIRFactory;
import models.PIR;
import printer.PIRPrinter;
import search.SearchCondition;
import search.SearchStringParser;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public abstract class PIRRepository<T extends PIR> {
    protected PIRPrinter<T> printer;
    protected PIRFactory<T> pirFactory;
    protected final ArrayList<T> data = new ArrayList<>();

    protected final SearchStringParser<T> searchStringParser;

    public PIRRepository(PIRPrinter<T> printer, PIRFactory<T> pirFactory, SearchStringParser<T> searchStringParser) {
        this.printer = printer;
        this.pirFactory = pirFactory;
        this.searchStringParser = searchStringParser;
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

    private SearchCondition<T> parse(String condition) {
        return searchStringParser.parse(condition);
    }

    private ArrayList<T> applyCondition(SearchCondition<T> condition) {
        ArrayList<T> values = new ArrayList<>();
        for (T pir : this.data) {
            if (condition.evaluate(pir)) values.add(pir);
        }
        return values;
    }

    private void applyConditionAndPrint(SearchCondition<T> condition) {
        ArrayList<T> values = this.applyCondition(condition);
        for (T pir : values) this.print(pir);
    }

    public Boolean search(String condition) {
        SearchCondition<T> searchCondition = this.parse(condition);
        if (searchCondition == null) return false;
        applyConditionAndPrint(searchCondition);
        return true;
    }

    public void print(T pir) {
        this.printer.print(pir);
    }

    public void save(PrintStream filestream) {
        PrintStream buffer = this.printer.setPrintStream(filestream);
        for (T pir: this.data) {
            print(pir);
        }
        this.printer.setPrintStream(buffer);
    }

    protected void replaceOrAdd(T pir) {
        int index = indexOfId(pir.getId());
        if (index != -1) {
            data.set(index, pir);
        } else {
            data.add(pir);
        }
    }

    private int indexOfId(Integer id) {
        for (int i = 0; i < data.size(); i++) {
            if (Objects.equals(data.get(i).getId(), id)) {
                return i;
            }
        }
        return -1;
    }
    public abstract void load(Scanner scanner);
    public void load(Scanner scanner, String repositoryType) {
        if ("PlainText".equals(repositoryType)) {
            loadPlainText(scanner);
        } else if ("Task".equals(repositoryType)) {
            loadTask(scanner);
        } else if ("Event".equals(repositoryType)) {
            loadEvent(scanner);
        } else if ("Contact".equals(repositoryType)) {
            loadContact(scanner);
        }
    }

    protected abstract void loadPlainText(Scanner scanner);
    protected abstract void loadTask(Scanner scanner);
    protected abstract void loadEvent(Scanner scanner);
    protected abstract void loadContact(Scanner scanner);
}

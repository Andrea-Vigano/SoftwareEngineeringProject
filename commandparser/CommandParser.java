package commandparser;

import repositories.PIRRepository;
import search.SearchCondition;

import java.io.PrintStream;
import java.util.Scanner;

public class CommandParser {
    final private PIRRepository<?>[] repositories;
    final private Scanner scanner;
    final private PrintStream printStream;
    public CommandParser(PIRRepository<?>[] repositories, Scanner scanner, PrintStream printStream) {
        this.repositories = repositories;
        this.scanner = scanner;
        this.printStream = printStream;
    }

    public void parse(Command command) {
        if (command.isAdd()) {
            performAdd(command);
        } else if (command.isEdit()) {
            performEdit(command);
        } else if (command.isRm()) {
            performRm(command);
        } else if (command.isFind()) {
            performFind(command);
        } else if (command.isSearch()) {
            performSearch(command);
        }
    }

    private void performAdd(Command command) {
        int index = this.getRepositoryIndex(command);
        Boolean result = repositories[index].createAndAdd();
        if (result) this.printStream.println("Successfully added PIR");
        else this.printStream.println("Failed to add PIR");
    }

    private void performEdit(Command command) {
        int index = this.getRepositoryIndex(command);
        Boolean result = repositories[index].createAndEdit();
        if (result) this.printStream.println("Successfully updated PIR");
        else this.printStream.println("Failed to update PIR");
    }

    private void performRm(Command command) {
        this.printStream.println("Insert the id: ");
        Integer id = this.scanner.nextInt();
        this.scanner.nextLine();
        int index = this.getRepositoryIndex(command);
        Boolean result = repositories[index].remove(id);
        if (result) this.printStream.println("Successfully removed pir with id: " + id);
        else this.printStream.println("Unable to remove pir with id: " + id);
    }

    private void performFind(Command command) {
        this.printStream.println("Insert the id: ");
        Integer id = this.scanner.nextInt();
        this.scanner.nextLine();
        int index = this.getRepositoryIndex(command);
        Boolean result = repositories[index].findAndPrint(id);
        if (!result) this.printStream.println("Unable to find plain text PIR with id: " + id);
    }

    private void performSearch(Command command) {
        this.printStream.println("Type the search condition you wish to apply: ");
        String condition = this.scanner.nextLine();
        int index = this.getRepositoryIndex(command);
        Boolean result = repositories[index].search(condition);
        if (!result) this.printStream.println("Unable to parse condition: " + condition);
    }

    private int getRepositoryIndex(Command command) {
        if (command.isPlainText()) return 0;
        else if (command.isTask()) return 1;
        else if (command.isEvent()) return 2;
        else if (command.isContact()) return 3;
        return -1;
    }
}

package commandparser;

import models.PIR;
import repositories.PIRRepository;

import java.util.Scanner;

public class CommandParser {
    final private PIRRepository<PIR>[] repositories;
    final private Scanner scanner;
    public CommandParser(PIRRepository<PIR>[] repositories, Scanner scanner) {
        this.repositories = repositories;
        this.scanner = scanner;
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
        }
    }

    private void performAdd(Command command) {
        int index = this.getRepositoryIndex(command);
        Boolean result = repositories[index].createAndAdd();
        if (result) System.out.println("Successfully added PIR");
        else System.out.println("Failed to add PIR");
    }

    private void performEdit(Command command) {
        int index = this.getRepositoryIndex(command);
        Boolean result = repositories[index].createAndEdit();
        if (result) System.out.println("Successfully updated PIR");
        else System.out.println("Failed to update PIR");
    }

    private void performRm(Command command) {
        System.out.println("Insert the id: ");
        Integer id = this.scanner.nextInt();
        this.scanner.nextLine();
        int index = this.getRepositoryIndex(command);
        Boolean result = repositories[index].remove(id);
        if (result) System.out.println("Successfully removed pir with id: " + id);
        else System.out.println("Unable to remove pir with id: " + id);
    }

    private void performFind(Command command) {
        System.out.println("Insert the id: ");
        Integer id = this.scanner.nextInt();
        this.scanner.nextLine();
        int index = this.getRepositoryIndex(command);
        PIR pir = repositories[index].find(id);
        if (pir != null) repositories[index].print(pir);
        else System.out.println("Unable to find plain text PIR with id: " + id);
    }

    private int getRepositoryIndex(Command command) {
        if (command.isPlainText()) return 0;
        else if (command.isTask()) return 1;
        else if (command.isEvent()) return 2;
        else if (command.isContact()) return 3;
        return -1;
    }
}

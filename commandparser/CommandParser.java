package commandparser;

import repositories.PIRRepository;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class CommandParser {
    final private PIRRepository<?>[] repositories;
    final private Scanner scanner;
    final private PrintStream printStream;
    private boolean shouldExit = false;
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
        } else if (command.isSave()) {
            performSave(command);
        } else if (command.isLoad()) {
            performLoad(command);
        } else if (command.isExit()) {
            performExit();
        }else {        // Invalid input
            performInvalid();
        }

    }

    private void performExit() {
        this.shouldExit = true;  // Set the exit flag
    }

    public boolean shouldExit() {
        return shouldExit;
    }

    private void performAdd(Command command) {
        int index = this.getRepositoryIndex(command);
        if (index == -1) {
            this.printStream.println("Invalid PIR type");
            return;
        }
        Boolean result = repositories[index].createAndAdd();
        if (result) this.printStream.println("Successfully added PIR");
        else this.printStream.println("Failed to add PIR");
    }

    private void performEdit(Command command) {
        int index = this.getRepositoryIndex(command);
        if (index == -1) {
            this.printStream.println("Invalid PIR type");
            return;
        }
        Boolean result = repositories[index].createAndEdit();
        if (result) this.printStream.println("Successfully updated PIR");
        else this.printStream.println("Failed to update PIR");
    }

    private void performRm(Command command) {
        this.printStream.println("Insert the id: ");
        Integer id = this.scanner.nextInt();
        this.scanner.nextLine();
        int index = this.getRepositoryIndex(command);
        if (index == -1) {
            this.printStream.println("Invalid PIR type");
            return;
        }
        Boolean result = repositories[index].remove(id);
        if (result) this.printStream.println("Successfully removed pir with id: " + id);
        else this.printStream.println("Unable to remove pir with id: " + id);
    }

    private void performFind(Command command) {
        this.printStream.println("Insert the id: ");
        Integer id = this.scanner.nextInt();
        this.scanner.nextLine();
        int index = this.getRepositoryIndex(command);
        if (index == -1) {
            this.printStream.println("Invalid PIR type");
            return;
        }
        Boolean result = repositories[index].findAndPrint(id);
        if (!result) this.printStream.println("Unable to find PIR with id: " + id);
    }

    private void performSearch(Command command) {
        this.printStream.println("Type the search condition you wish to apply: ");
        String condition = this.scanner.nextLine();
        int index = this.getRepositoryIndex(command);
        if (index == -1) {
            this.printStream.println("Invalid PIR type");
            return;
        }
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

    private void performSave(Command command) {
        try {
            String[] repo_list = {"PlainText", "Task", "Event", "Contact"};
            PrintStream fileStream = new PrintStream(command.getdir() + ".pim");
            for (int index = 0; index < 4; ++index) {
                fileStream.println(repo_list[index]);
                repositories[index].save(fileStream);
                // Add a comment line as a marker between repositories
                fileStream.println("# --- End of " + repo_list[index] + " repository ---");
            }
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }

    private void performLoad(Command command) {
        try {
            if (!command.getdir().endsWith(".pim")) {
                System.out.println("Invalid file format. Please provide a .pim file.");
                return;
            }

            Scanner fileScanner = new Scanner(new File(command.getdir()));

            for (int index = 0; index < 4; ++index) {
                if (!fileScanner.hasNextLine()) {
                    System.out.println("Error: Incomplete data in the file. Aborting load.");
                    break;
                }

                String repositoryType = fileScanner.nextLine().trim();

                if (!isValidRepositoryType(repositoryType)) {
                    System.out.println("Error: Invalid repository type found in the file. Aborting load.");
                    break;
                }
                repositories[index].load(fileScanner, repositoryType);
            }

            fileScanner.close();
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }
    private boolean isValidRepositoryType(String repositoryType) {
        return repositoryType.equals("PlainText") || repositoryType.equals("Task")
                || repositoryType.equals("Event") || repositoryType.equals("Contact");
    }

    private void performInvalid() {
        this.printStream.println("Invalid Input!");
    }
}
import commandparser.Command;
import commandparser.CommandParser;
import io.IO;
import repositories.*;

import java.io.PrintStream;
import java.util.Scanner;

public class App {
    private final Scanner scanner;
    private final PrintStream printStream;
    private final CommandParser commandParser;

    public App(Scanner scanner, PrintStream printStream) {
        this.scanner = scanner;
        this.printStream = printStream;
        PIRRepository<?>[] repositories = {
                new PlainTextPIRRepository(scanner, printStream),
                new TaskPIRRepository(scanner, printStream),
                new EventPIRRepository(scanner, printStream),
                new ContactPIRRepository(scanner, printStream)
        };
        this.commandParser = new CommandParser(repositories, scanner, printStream);
    }

    static public void main(String[] args) {
        App app = new App(IO.scanner, IO.printStream);
        app.run();
    }

    public void run() {
        printWelcomeMessage();
        do {
            printStream.println("\nPlease enter your command:");
            String rawCommand = "";
            if (this.scanner.hasNext()) {    // this condition is for test
                rawCommand = this.scanner.nextLine().toLowerCase();
                runCommand(rawCommand);
            }
            else
                break;
        } while (!commandParser.shouldExit());
    }

    public void runCommand(String rawCommand) {
        Command currentCommand = new Command(rawCommand);
        commandParser.parse(currentCommand);
    }

    private void printWelcomeMessage() {
        printStream.println("\nWelcome to the Personal Information Manager!\n");
        printStream.println("Commands:");
        printStream.println("- To add a new item: add [type]");
        printStream.println("- To edit an item: edit [type]");
        printStream.println("- To remove an item: rm [type]");
        printStream.println("- To find an item: find [type]");
        printStream.println("- To search for items: search [type]");
        printStream.println("- To save the repositories: save [file]");
        printStream.println("- To load repositories from a file: load [file].pim");
        printStream.println("- To exit: exit\n");
        printStream.println("Example Usage:\n");
        printStream.println("1. Add a PlainText item:");
        printStream.println("   Command: add plain-text");
        printStream.println("   [User enters information for the PlainText item]\n");
        printStream.println("2. Edit a Task item:");
        printStream.println("   Command: edit task");
        printStream.println("   [User provides details to edit the Task item]\n");
        printStream.println("3. Remove an Event item:");
        printStream.println("   Command: rm event");
        printStream.println("   [User enters the ID of the Event item to be removed]\n");
        printStream.println("4. Find a PlainText item:");
        printStream.println("   Command: find plain-text");
        printStream.println("   [User enters information to find PlainText item]\n");
        printStream.println("5. Search a Task item:");
        printStream.println("   Command: find task");
        printStream.println("   [User enters condition for the Task item]");
        printStream.println("""
                   [Conditions:
                    AND(),
                    OR(),
                    NOT(),
                    CONTAINS(),
                    EQUALS(),
                    AFTER(),
                    BEFORE()]
                    [Example: BEFORE("2023/12/11 23:00")]
                """);
        printStream.println("6. Save your Personal Information Records as a file:");
        printStream.println("   Command: save [name_of_file]");
        printStream.println("   [User can save all created PIR information as a .pim file]\n");
        printStream.println("7. Load your Personal Information Records from a file:");
        printStream.println("   Command: load [name_of_file].pim");
        printStream.println("   [User can load the created PIM file to add,edit,remove,search and find PIR]\n");
        printStream.println("8. Exit from program:");
        printStream.println("   Command: exit");
    }
}

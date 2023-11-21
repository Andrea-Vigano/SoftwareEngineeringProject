import commandparser.Command;
import commandparser.CommandParser;
import io.IO;
import repositories.*;

import java.io.PrintStream;
import java.util.Scanner;

public class App {
    static private final Scanner scanner = IO.scanner;
    static private final PrintStream printStream = IO.printStream;
    private final CommandParser commandParser;

    public App(PIRRepository<?>[] repositories) {
        this.commandParser = new CommandParser(repositories, scanner, printStream);
    }

    static public void main(String[] args) {
        PIRRepository<?>[] repositories = {
                new PlainTextPIRRepository(App.scanner, App.printStream),
                new TaskPIRRepository(App.scanner, App.printStream),
                new EventPIRRepository(App.scanner, App.printStream),
                new ContactPIRRepository(App.scanner, App.printStream)
        };
        App app = new App(repositories);
        app.run();
    }

    public void run() {
        Command currentCommand;
        do {
            String rawCommand = App.scanner.nextLine();
            currentCommand = new Command(rawCommand);
            commandParser.parse(currentCommand);
        } while (!currentCommand.isExit());
    }
}

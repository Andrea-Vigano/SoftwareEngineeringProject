import commandparser.Command;
import commandparser.CommandParser;
import models.PIR;
import repositories.*;

import java.util.Scanner;

public class App {
    static private final Scanner scanner = new Scanner(System.in);
    private final CommandParser commandParser;

    public App(PIRRepository<PIR>[] repositories) {
        this.commandParser = new CommandParser(repositories, scanner);
    }

    static public void main(String[] args) {
        // TODO try fix parametrization
        PIRRepository[] repositories = {
                new PlainTextPIRRepository(),
                new TaskPIRRepository(),
                new EventPIRRepository(),
                new ContactPIRRepository()
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

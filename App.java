import commandparser.Command;
import commandparser.CommandParser;
import repositories.PIRRepository;
import repositories.PlainTextPIRRepository;

import java.util.Scanner;

public class App {
    static private final Scanner scanner = new Scanner(System.in);
    private final CommandParser commandParser;

    public App(PIRRepository[] repositories) {
        this.commandParser = new CommandParser(repositories, scanner);
    }

    static public void main(String[] args) {
        PIRRepository[] repositories = { new PlainTextPIRRepository() };
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

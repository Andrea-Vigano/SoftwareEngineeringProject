package repositories;

import factories.ContactPIRFactory;
import models.ContactPIR;
import printer.ContactPIRPrinter;
import search.ContactPIRSearchStringParser;

import java.io.PrintStream;
import java.util.Scanner;

public class ContactPIRRepository extends PIRRepository<ContactPIR> {
    public ContactPIRRepository(Scanner scanner, PrintStream printStream) {
        super(
                new ContactPIRPrinter(printStream),
                new ContactPIRFactory(scanner, printStream),
                new ContactPIRSearchStringParser());
    }

    @Override
    public void load(Scanner scanner) {}
    @Override
    protected void loadPlainText(Scanner scanner) {}
    @Override
    protected void loadTask(Scanner scanner) {}
    @Override
    protected void loadEvent(Scanner scanner) {}
    @Override
    public void loadContact(Scanner scanner) {
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.equals("# --- End of Contact repository ---")) {
                break;
            }

            String[] parts = line.split(": ", 2);
            if (parts.length == 2) {
                Integer id = Integer.parseInt(parts[0]);
                String name = parts[1];

                // Check for the next line (address)
                if (scanner.hasNextLine()) {
                    String addressLine = scanner.nextLine().trim();
                    // Check for the next line (phoneNumber)
                    if (scanner.hasNextLine()) {
                        String phoneNumber = scanner.nextLine().trim();

                        // Assuming the address is in the format: "City StreetName StreetNumber"
                        String[] addressDetails = addressLine.split(" ", 3);
                        if (addressDetails.length == 3) {
                            String city = addressDetails[0];
                            String streetName = addressDetails[1];
                            String streetNumber = addressDetails[2];

                            String address = city + " " + streetName + " " + streetNumber;

                            ContactPIR pir = new ContactPIR(id, name, address, phoneNumber);
                            replaceOrAdd(pir);
                        }
                    }
                }
            }
        }
    }
}


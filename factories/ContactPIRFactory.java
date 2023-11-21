package factories;

import models.ContactPIR;

public class ContactPIRFactory extends PIRFactory<ContactPIR> {

    @Override
    protected ContactPIR createPIR(Integer id) {
        String name = this.getText("Type the name of the contact you wish to store: ");
        String address = this.getAddress();
        String phoneNumber = this.getPhoneNumber();
        return new ContactPIR(id, name, address, phoneNumber);
    }

    private Boolean isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber.length() != 8) return false;
        try{
            int number = Integer.parseInt(phoneNumber);
            if (number >= 0)
                return true;
        } catch (NumberFormatException ignored) { }
        return false;
    }

    private String getPhoneNumber() {
        String phoneNumber = this.getText("Type the phone number of the contact you wish to store: ");
        if (!this.isValidPhoneNumber(phoneNumber)) {
            this.printStream.println("Unable to parse phone number: " + phoneNumber);
            return null;
        }
        return phoneNumber;
    }

    private String getAddress() {
        String city = this.getText("Type the city of the contact you wish to store: ");
        String street = this.getText("Type the street of the contact you wish to store: ");
        int streetNumber = this.getInteger("Type the street number of the contact you wish to store: ");
        return String.join(" ", new String[]{city, street, Integer.toString(streetNumber)});
    }
}

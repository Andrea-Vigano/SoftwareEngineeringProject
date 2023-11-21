package models;
// TODO better implement address and phone number (?)
public class ContactPIR extends PIR {
    private final String name;
    private final String address;
    private final String mobilePhoneNumber;
    public ContactPIR(Integer id, String name, String address, String mobilePhoneNumber) {
        super(id);
        this.name = name;
        this.address = address;
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }
}

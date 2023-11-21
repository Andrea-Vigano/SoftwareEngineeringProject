package models;

public class PlainTextPIR extends PIR {
    private final String text;

    public PlainTextPIR(Integer id, String text) {
        super(id);
        this.text = text;
    }

    public String getText() {
        return this.text;
    }
}

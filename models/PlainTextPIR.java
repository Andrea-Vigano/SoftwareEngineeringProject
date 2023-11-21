package models;

public class PlainTextPIR implements PIR {
    private String text;
    private Integer id;

    public PlainTextPIR(Integer id, String text) {
        this.text = text;
        this.id = id;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public Integer getId() {
        return this.id;
    }
}

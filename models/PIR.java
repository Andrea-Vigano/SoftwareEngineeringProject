package models;

public abstract class PIR implements IPIR {
    protected final Integer id;

    protected PIR(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

}

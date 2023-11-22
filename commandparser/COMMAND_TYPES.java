package commandparser;

import java.util.Objects;

public enum COMMAND_TYPES {
    ADD("add"),
    EDIT("edit"),
    RM("rm"),
    FIND("find"),
    SEARCH("search"),
    EXIT("exit"),
    SAVE("save"),
    LOAD("load");

    private final String value;

    COMMAND_TYPES(String value) {
        this.value = value;
    }

    public Boolean isEqual(String commandType) {
        return Objects.equals(this.value, commandType);
    }
}

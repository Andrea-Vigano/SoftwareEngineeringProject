package commandparser;

import models.PIR_TYPES;

public class Command {
    private String operation = "";
    private String type = "";
    public Command(String rawCommand) {
        if (!this.isExit()) {
            String[] components = rawCommand.split(" ",2);
            if (components.length >= 1) {
                operation = components[0];
            }

            if (components.length == 2) {
                type = components[1];
            }
        }
    }

    private Boolean is(COMMAND_TYPES commandType) {
        return commandType.isEqual(this.operation);
    }

    private Boolean is(PIR_TYPES pirType) {
        return pirType.isEqual(this.type);
    }

    public Boolean isAdd() {
        return this.is(COMMAND_TYPES.ADD) && !type.isEmpty();
    }

    public Boolean isEdit() {
        return this.is(COMMAND_TYPES.EDIT) && !type.isEmpty();
    }

    public Boolean isRm() {
        return this.is(COMMAND_TYPES.RM) && !type.isEmpty();
    }

    public Boolean isFind() {
        return this.is(COMMAND_TYPES.FIND) && !type.isEmpty();
    }

    public Boolean isSearch() {
        return this.is(COMMAND_TYPES.SEARCH) && !type.isEmpty();
    }

    public Boolean isExit() {
        return this.is(COMMAND_TYPES.EXIT);
    }

    public Boolean isSave() { return this.is(COMMAND_TYPES.SAVE);}

    public Boolean isLoad() { return this.is(COMMAND_TYPES.LOAD);}

    public Boolean isPlainText() {
        return this.is(PIR_TYPES.PLAIN_TEXT);
    }

    public Boolean isTask() {
        return this.is(PIR_TYPES.TASK);
    }

    public Boolean isEvent() {
        return this.is(PIR_TYPES.EVENT);
    }

    public Boolean isContact() {
        return this.is(PIR_TYPES.CONTACT);
    }

    public String getdir() {
        return (isSave() || isLoad()) ? type : "";
    }
}

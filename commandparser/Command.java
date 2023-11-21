package commandparser;

import models.PIR_TYPES;

public class Command {
    private String operation = "";
    private String type = "";
    public Command(String rawCommand) {
        if (!this.isExit()) {
            String[] components = rawCommand.split(" ");
            operation = components[0];
            type = components[1];
        }

    }

    private Boolean is(COMMAND_TYPES commandType) {
        return commandType.isEqual(this.operation);
    }

    private Boolean is(PIR_TYPES pirType) {
        return pirType.isEqual(this.type);
    }

    public Boolean isAdd() {
        return this.is(COMMAND_TYPES.ADD);
    }

    public Boolean isEdit() {
        return this.is(COMMAND_TYPES.EDIT);
    }

    public Boolean isRm() {
        return this.is(COMMAND_TYPES.RM);
    }

    public Boolean isFind() {
        return this.is(COMMAND_TYPES.FIND);
    }

    public Boolean isExit() {
        return this.is(COMMAND_TYPES.EXIT);
    }

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
}

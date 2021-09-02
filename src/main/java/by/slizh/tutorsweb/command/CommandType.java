package by.slizh.tutorsweb.command;

import by.slizh.tutorsweb.command.impl.DefaultCommand;
import by.slizh.tutorsweb.command.impl.LoginCommand;

public enum CommandType {
    DEFAULT(new DefaultCommand()),
    LOGIN(new LoginCommand());

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}

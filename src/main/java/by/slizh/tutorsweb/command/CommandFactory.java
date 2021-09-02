package by.slizh.tutorsweb.command;

import jakarta.servlet.http.HttpServletRequest;
import jdk.jfr.ContentType;

import java.util.Locale;

public class CommandFactory {

    private static CommandFactory instance;

    private CommandFactory() {
    }

    public static CommandFactory getInstance() {
        if (instance == null) {
            instance = new CommandFactory();
        }
        return instance;
    }

    public Command createCommand(HttpServletRequest request) {
        String commandName = request.getParameter(RequestParameter.COMMAND);
        Command command = CommandType.DEFAULT.getCommand();
        if (commandName == null) {
            return command;
        } else {
            try {
                CommandType commandType = CommandType.valueOf(commandName.toUpperCase());
                command = commandType.getCommand();
            } catch (IllegalArgumentException e) {
                command = CommandType.DEFAULT.getCommand();
            }
        }
        CommandType commandType = CommandType.valueOf(commandName.toUpperCase());
        command = commandType.getCommand();
        return command;
    }
}

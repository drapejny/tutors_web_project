package by.slizh.tutorsweb.command.impl;

import by.slizh.tutorsweb.command.Command;
import by.slizh.tutorsweb.command.Router;
import jakarta.servlet.http.HttpServletRequest;

public class DefaultCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        return null;
    }
}

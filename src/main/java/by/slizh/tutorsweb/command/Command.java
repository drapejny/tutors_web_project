package by.slizh.tutorsweb.command;

import jakarta.servlet.http.HttpServletRequest;

public interface Command {

    Router execute(HttpServletRequest request);
}

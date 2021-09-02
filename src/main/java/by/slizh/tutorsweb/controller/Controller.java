package by.slizh.tutorsweb.controller;

import java.io.*;

import by.slizh.tutorsweb.command.Command;
import by.slizh.tutorsweb.command.CommandFactory;
import by.slizh.tutorsweb.command.RequestParameter;
import by.slizh.tutorsweb.command.Router;
import by.slizh.tutorsweb.entity.Subject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "mainServlet", urlPatterns = "/controller")
public class Controller extends HttpServlet {

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Command command = CommandFactory.getInstance().createCommand(request);
        Router router = command.execute(request);
        switch (router.getRouteType()) {
            case FORWARD:
                System.out.println("!!!!!!!!!!!!");
                request.getRequestDispatcher(router.getPagePath()).forward(request, response);
                break;
            case REDIRECT:
                response.sendRedirect(request.getContextPath() + router.getPagePath());
                break;
        }
    }

    public void destroy() {
    }
}
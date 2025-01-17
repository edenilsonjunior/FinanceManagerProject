package br.edu.ifsp.arq.tsi.arqweb2.financeManager.servlets.handlers;

import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.contracts.services.IUsersService;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.service.UsersService;
import com.google.gson.JsonObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UsersHandler implements IHandler {

    private final IUsersService usersService;

    public UsersHandler(){
        this.usersService = new UsersService();
    }

    @Override
    public JsonObject handle(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        return switch (req.getParameter("action")) {
            case "login" -> usersService.handleLogin(req, resp);
            case "logout" -> usersService.handleLogout(req, resp);
            case "register" -> usersService.handleRegister(req, resp);
            default -> throw new IllegalArgumentException("Ação inválida: " + req.getParameter("action"));
        };
    }
}
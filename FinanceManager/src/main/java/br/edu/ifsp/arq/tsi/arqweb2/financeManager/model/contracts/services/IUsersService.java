package br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.contracts.services;

import com.google.gson.JsonObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface IUsersService {
    JsonObject handleRegister(HttpServletRequest request, HttpServletResponse response);
    JsonObject handleLogin(HttpServletRequest request, HttpServletResponse response);
    JsonObject handleLogout(HttpServletRequest request, HttpServletResponse response);
}
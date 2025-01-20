package br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.contracts.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface IUsersService {
    Object handleRegister(HttpServletRequest request, HttpServletResponse response);
    Object handleLogin(HttpServletRequest request, HttpServletResponse response);
    Object handleLogout(HttpServletRequest request, HttpServletResponse response);
}
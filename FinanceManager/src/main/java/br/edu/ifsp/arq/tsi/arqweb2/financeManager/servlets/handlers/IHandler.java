package br.edu.ifsp.arq.tsi.arqweb2.financeManager.servlets.handlers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface IHandler {
    Object handle(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
package br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.contracts.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface IIndexService {
    Object handleNotification(HttpServletRequest request, HttpServletResponse response);
}
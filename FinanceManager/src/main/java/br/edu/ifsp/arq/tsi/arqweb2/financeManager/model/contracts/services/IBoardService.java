package br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.contracts.services;

import com.google.gson.JsonObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface IBoardService {
    JsonObject handlePreview(HttpServletRequest request, HttpServletResponse response);
}
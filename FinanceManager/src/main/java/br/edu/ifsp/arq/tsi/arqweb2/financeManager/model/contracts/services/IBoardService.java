package br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.contracts.services;

import com.google.gson.JsonElement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface IBoardService {
    JsonElement handlePreview(HttpServletRequest request, HttpServletResponse response);
}
package br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.contracts.services;

import com.google.gson.JsonObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface ICategoriesService {
    JsonObject handleCreateCategory(HttpServletRequest request, HttpServletResponse response);
    JsonObject handleGetCategoriesByUser(HttpServletRequest request, HttpServletResponse response);
}
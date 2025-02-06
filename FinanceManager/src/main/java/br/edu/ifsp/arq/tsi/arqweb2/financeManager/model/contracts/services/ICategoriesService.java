package br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.contracts.services;

import com.google.gson.JsonElement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface ICategoriesService {
    Object handleCreateCategory(HttpServletRequest request, HttpServletResponse response);
    JsonElement handleGetCategoriesByUser(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
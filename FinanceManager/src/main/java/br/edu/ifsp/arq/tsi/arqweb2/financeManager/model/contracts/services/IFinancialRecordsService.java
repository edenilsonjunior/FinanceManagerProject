package br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.contracts.services;

import com.google.gson.JsonObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface IFinancialRecordsService {
    JsonObject handleCreate(HttpServletRequest request, HttpServletResponse response);
    JsonObject handleUpdate(HttpServletRequest request, HttpServletResponse response);
    JsonObject handleDelete(HttpServletRequest request, HttpServletResponse response);
    JsonObject handleGetByUser(HttpServletRequest request, HttpServletResponse response);
}
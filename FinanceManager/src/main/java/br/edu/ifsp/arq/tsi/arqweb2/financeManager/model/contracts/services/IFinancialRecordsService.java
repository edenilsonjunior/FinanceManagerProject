package br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.contracts.services;

import com.google.gson.JsonElement;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface IFinancialRecordsService {
    Object handleCreate(HttpServletRequest request, HttpServletResponse response);
    Object handleUpdate(HttpServletRequest request, HttpServletResponse response);
    Object handleDelete(HttpServletRequest request, HttpServletResponse response);
    Object handleCreateView(HttpServletRequest request, HttpServletResponse response) throws Exception;
    JsonElement handleUpdateView(HttpServletRequest request, HttpServletResponse response) throws Exception;
    JsonElement handleHistory(HttpServletRequest request, HttpServletResponse response);
}
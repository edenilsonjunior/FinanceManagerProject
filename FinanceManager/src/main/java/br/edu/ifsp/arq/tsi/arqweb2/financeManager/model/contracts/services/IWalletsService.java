package br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.contracts.services;

import com.google.gson.JsonElement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface IWalletsService {

    JsonElement handleHistory(HttpServletRequest request, HttpServletResponse response);

    Object handleCreate(HttpServletRequest request, HttpServletResponse response);

    Object handleCreateTransaction(HttpServletRequest request, HttpServletResponse response);

    JsonElement handleDetails(HttpServletRequest request, HttpServletResponse response);

    Object handleUpdate(HttpServletRequest request, HttpServletResponse response);

    Object handleUpdateView(HttpServletRequest request, HttpServletResponse response);

    Object handleDelete(HttpServletRequest request, HttpServletResponse response);
}
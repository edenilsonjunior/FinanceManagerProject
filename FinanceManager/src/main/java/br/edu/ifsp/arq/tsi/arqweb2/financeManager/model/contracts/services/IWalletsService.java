package br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.contracts.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface IWalletsService {
    Object handleCreate(HttpServletRequest request, HttpServletResponse response);

    Object handleCreateTransaction(HttpServletRequest request, HttpServletResponse response);

    Object handleUpdate(HttpServletRequest request, HttpServletResponse response);

    Object handleUpdateView(HttpServletRequest request, HttpServletResponse response);

    Object handleDelete(HttpServletRequest request, HttpServletResponse response);

    Object handleWalletsView(HttpServletRequest request, HttpServletResponse response);
}

package br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.service;

import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.contracts.dao.IWalletDao;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.contracts.services.IWalletsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class WalletsService implements IWalletsService {

    private final IWalletDao walletDao;

    public WalletsService(IWalletDao walletDao) {
        this.walletDao = walletDao;
    }

    @Override
    public Object handleCreate(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    @Override
    public Object handleCreateTransaction(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    @Override
    public Object handleUpdate(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    @Override
    public Object handleUpdateView(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    @Override
    public Object handleDelete(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    @Override
    public Object handleWalletsView(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}

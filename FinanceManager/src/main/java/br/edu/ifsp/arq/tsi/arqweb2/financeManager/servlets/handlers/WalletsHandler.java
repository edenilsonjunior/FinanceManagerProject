package br.edu.ifsp.arq.tsi.arqweb2.financeManager.servlets.handlers;

import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.contracts.services.IWalletsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class WalletsHandler implements IHandler{

    private final IWalletsService walletsService;

    public WalletsHandler(IWalletsService walletsService) {
        this.walletsService = walletsService;
    }

    @Override
    public Object handle(HttpServletRequest request, HttpServletResponse response) throws Exception {

        return switch (request.getParameter("action")) {
            case "wallets" -> walletsService.handleHistory(request, response);
            case "create" -> walletsService.handleCreate(request, response);
            case "create-transaction" -> walletsService.handleCreateTransaction(request, response);
            case "details" -> walletsService.handleDetails(request, response);
            case "update" -> walletsService.handleUpdate(request, response);
            case "update-view" -> walletsService.handleUpdateView(request, response);
            case "delete" -> walletsService.handleDelete(request, response);
            default -> throw new IllegalArgumentException("Ação inválida: " + request.getParameter("action"));
        };
    }
}
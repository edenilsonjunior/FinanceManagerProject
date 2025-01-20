package br.edu.ifsp.arq.tsi.arqweb2.financeManager.servlets.handlers;

import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.contracts.services.IFinancialRecordsService;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.service.FinancialRecordsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FinancialRecordsHandler implements IHandler {

    private final IFinancialRecordsService financialRecordsService;

    public FinancialRecordsHandler() {
        this.financialRecordsService = new FinancialRecordsService();
    }

    @Override
    public Object handle(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        return switch (req.getParameter("action")) {
            case "create" -> financialRecordsService.handleCreate(req, resp);
            case "create-view" -> financialRecordsService.handleCreateView(req, resp);
            case "update" -> financialRecordsService.handleUpdate(req, resp);
            case "update-view" -> financialRecordsService.handleUpdateView(req, resp);
            case "delete" -> financialRecordsService.handleDelete(req, resp);
            case "history" -> financialRecordsService.handleHistory(req, resp);
            default -> throw new IllegalArgumentException("Ação inválida: " + req.getParameter("action"));
        };
    }
}
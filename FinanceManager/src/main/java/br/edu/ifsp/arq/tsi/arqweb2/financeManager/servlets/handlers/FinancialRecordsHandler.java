package br.edu.ifsp.arq.tsi.arqweb2.financeManager.servlets.handlers;

import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.contracts.services.IFinancialRecordsService;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.service.FinancialRecordsService;
import com.google.gson.JsonObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FinancialRecordsHandler implements IHandler {

    private final IFinancialRecordsService financialRecordsService;

    public FinancialRecordsHandler() {
        this.financialRecordsService = new FinancialRecordsService();
    }

    @Override
    public JsonObject handle(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        return switch (req.getParameter("action")) {
            case "create" -> financialRecordsService.handleCreate(req, resp);
            case "update" -> financialRecordsService.handleUpdate(req, resp);
            case "delete" -> financialRecordsService.handleDelete(req, resp);
            case "getByUser" -> financialRecordsService.handleGetByUser(req, resp);
            default -> throw new IllegalArgumentException("Ação inválida: " + req.getParameter("action"));
        };
    }
}
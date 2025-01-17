package br.edu.ifsp.arq.tsi.arqweb2.financeManager.servlets.handlers;

import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.contracts.services.ICategoriesService;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.service.CategoriesService;
import com.google.gson.JsonObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CategoriesHandler implements IHandler {

    private final ICategoriesService categoriesService;

    public CategoriesHandler(){
        this.categoriesService = new CategoriesService();
    }

    @Override
    public JsonObject handle(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        return switch (req.getParameter("action")) {
            case "create" -> categoriesService.handleCreateCategory(req, resp);
            case "getByUser" -> categoriesService.handleGetCategoriesByUser(req, resp);
            default -> throw new IllegalArgumentException("Ação inválida: " + req.getParameter("action"));
        };
    }
}
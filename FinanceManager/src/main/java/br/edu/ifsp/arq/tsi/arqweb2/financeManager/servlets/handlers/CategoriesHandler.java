package br.edu.ifsp.arq.tsi.arqweb2.financeManager.servlets.handlers;

import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.contracts.services.ICategoriesService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CategoriesHandler implements IHandler {

    private final ICategoriesService categoriesService;

    public CategoriesHandler(ICategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }

    @Override
    public Object handle(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        return switch (req.getParameter("action")) {
            case "create" -> categoriesService.handleCreateCategory(req, resp);
            case "getByUser" -> categoriesService.handleGetCategoriesByUser(req, resp);
            default -> throw new IllegalArgumentException("Ação inválida: " + req.getParameter("action"));
        };
    }
}
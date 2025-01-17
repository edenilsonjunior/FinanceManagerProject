package br.edu.ifsp.arq.tsi.arqweb2.financeManager.servlets.handlers;

import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.contracts.services.IIndexService;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.service.IndexService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class IndexHandler implements IHandler {

    private final IIndexService indexService;

    public IndexHandler(){
        this.indexService = new IndexService();
    }

    @Override
    public Object handle(HttpServletRequest request, HttpServletResponse response) throws Exception {

        if(request.getParameter("action").equals("notification"))
            return indexService.handleNotification(request, response);

        throw new IllegalArgumentException("Ação inválida: " + request.getParameter("action"));
    }
}
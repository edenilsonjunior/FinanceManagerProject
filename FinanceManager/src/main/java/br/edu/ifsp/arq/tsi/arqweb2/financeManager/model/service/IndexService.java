package br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.service;

import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.contracts.services.IIndexService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class IndexService implements IIndexService {

    @Override
    public Object handleNotification(HttpServletRequest request, HttpServletResponse response) {

        var session = request.getSession(false);

        if (session != null && session.getAttribute("successMessage") != null) {
            request.setAttribute("successMessage", session.getAttribute("successMessage"));
            session.removeAttribute("successMessage");
        }

        return "/index.jsp";
    }
}
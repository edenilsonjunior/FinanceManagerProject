package br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.service;

import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.contracts.services.IIndexService;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.utils.Utils;
import com.google.gson.JsonElement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class IndexService implements IIndexService {

    @Override
    public JsonElement handleNotification(HttpServletRequest request, HttpServletResponse response) {

        var session = request.getSession(false);

        if (session != null && session.getAttribute("success") != null) {
            var message = session.getAttribute("success").toString();
            session.removeAttribute("success");

            return Utils.toJson(message);
        }

        if (session != null && session.getAttribute("error") != null) {
            var message = session.getAttribute("error").toString();
            session.removeAttribute("error");

            return Utils.toJson(message);
        }

        return Utils.toJson("");
    }
}
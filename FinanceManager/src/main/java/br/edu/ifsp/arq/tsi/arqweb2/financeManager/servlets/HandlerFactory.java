package br.edu.ifsp.arq.tsi.arqweb2.financeManager.servlets;

import br.edu.ifsp.arq.tsi.arqweb2.financeManager.servlets.handlers.IHandler;
import jakarta.servlet.http.HttpServletRequest;

public class HandlerFactory {

    private HandlerFactory() {}

    public static IHandler getHelper(HttpServletRequest request) {

        var context = request.getParameter("context");

        var className = request.getServletContext().getInitParameter(context);

        try {
            Class<?> clazz = Class.forName(className);
            return (IHandler)clazz.getDeclaredConstructor().newInstance();
        }
        catch(Exception erro) {
            throw new RuntimeException(erro);
        }
    }
}
package br.edu.ifsp.arq.tsi.arqweb2.financeManager.servlets;

import br.edu.ifsp.arq.tsi.arqweb2.financeManager.utils.Utils;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet("/controller")
public class FrontControllerServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public FrontControllerServlet() {
        super();
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        var user = Utils.getUser(request);

        if(request.getParameter("action") == null){
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }

        if(user == null && !request.getParameter("action").equals("login") && !request.getParameter("action").equals("sign-up")){
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        var handler = HandlerFactory.getHelper(request);

        try {
            var handlerResponse = handler.handle(request, response);

            if (handlerResponse instanceof JsonObject) {
                response.setContentType("application/json");
                response.getWriter().write(response.toString());
                return;
            }

            var dispatcher = request.getRequestDispatcher(response.toString());
            dispatcher.forward(request, response);

        } catch (Exception error) {
            throw new ServletException(error);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
package br.edu.ifsp.arq.tsi.arqweb2.financeManager.servlets;

import br.edu.ifsp.arq.tsi.arqweb2.financeManager.utils.Utils;
import com.google.gson.JsonElement;
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
            request.getRequestDispatcher("/login").forward(request, response);
            return;
        }

        if(user == null
            && !request.getParameter("action").equals("login")
            && !request.getParameter("action").equals("signup")
        ){
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        var handler = HandlerFactory.getHelper(request);

        try {
            var handlerResponse = handler.handle(request, response);

            if (handlerResponse instanceof JsonElement) {
                response.setContentType("application/json");
                response.getWriter().write(handlerResponse.toString());
                return;
            }

            var path = handlerResponse.toString();

            if(path.contains("dispatcher:")){
                
                path = path.replace("dispatcher:", "");
                request.getRequestDispatcher(path).forward(request, response);
                return;
            }

            response.sendRedirect(request.getContextPath() + path);

        } catch (Exception error) {
            throw new ServletException(error);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
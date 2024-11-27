package br.edu.ifsp.arq.tsi.arqweb2.financeManager.servlets;

import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.dao.UserDao;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.user.User;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.utils.DataSourceSearcher;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Optional;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
        dispatcher.forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        String url;

        var userDao = new UserDao(DataSourceSearcher.getInstance().getDataSource());

        var user = userDao.findUserByEmail(email);

        if(user.isPresent() && user.get().getPassword().equals(password)) {
            HttpSession session = request.getSession();
            session.setMaxInactiveInterval(600);
            session.setAttribute("user", user);

            url = "/index.jsp";
        } else {
            request.setAttribute("loginErrorMessage", "Email ou Senha inválidos");
            url = "/login.jsp";
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);

        dispatcher.forward(request, response);
    }
}

package br.edu.ifsp.arq.tsi.arqweb2.financeManager.servlets.user;

import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.dao.UserDao;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.utils.DataSourceSearcher;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.utils.PasswordEncoder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {

        var path = "/WEB-INF/views/user/login.jsp";
        var dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = PasswordEncoder.encode(request.getParameter("password"));
        
        var userDao = new UserDao(DataSourceSearcher.getInstance().getDataSource());

        var user = userDao.findUserByEmail(email);

        if(user.isPresent() && user.get().getPassword().equals(password)) {
            HttpSession session = request.getSession();
            session.setMaxInactiveInterval(600);
            session.setAttribute("user", user.get());

            response.sendRedirect(request.getContextPath() + "/index");

        } else {
            request.setAttribute("loginErrorMessage", "Email ou Senha inválidos");
            var dispatcher = request.getRequestDispatcher( "WEB-INF/views/user/login.jsp");
            dispatcher.forward(request, response);
        }
    }
}

package br.edu.ifsp.arq.tsi.arqweb2.financeManager.servlets.user;

import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.dao.UserDao;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.user.User;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.utils.DataSourceSearcher;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.utils.PasswordEncoder;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;


@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SignUpServlet() {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {

        var path = "/WEB-INF/views/user/signup.jsp";
        var dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String fullName = request.getParameter("full-name");
        String email = request.getParameter("email");
        String password = PasswordEncoder.encode(request.getParameter("password"));
        LocalDate birthDate = LocalDate.parse(request.getParameter("birth-date"));

        var path = "/WEB-INF/views/user/signup.jsp";

        var userDao = new UserDao(DataSourceSearcher.getInstance().getDataSource());

        if (!(userDao.findUserByEmail(email).isPresent())) {
            var user = new User();
            user.setFullName(fullName);
            user.setEmail(email);
            user.setPassword(password);
            user.setBirthDate(birthDate);

            userDao.create(user);

            request.setAttribute("signupSuccessMessage", "Usuário cadastrado com sucesso");
            path = "/WEB-INF/views/user/login.jsp";
        } else {
            request.setAttribute("signupErrorMessage", "Email já cadastrado");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response);
    }
}

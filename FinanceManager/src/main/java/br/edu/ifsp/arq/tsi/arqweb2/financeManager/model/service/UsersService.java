package br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.service;

import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.contracts.dao.IUserDao;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.contracts.services.IUsersService;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.dao.UserDao;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.user.User;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.utils.DataSourceSearcher;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.utils.PasswordEncoder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.time.LocalDate;

public class UsersService implements IUsersService {

    private final IUserDao userDao;

    public UsersService(){
        this.userDao = new UserDao(DataSourceSearcher.getInstance().getDataSource());
    }


    @Override
    public Object handleRegister(HttpServletRequest request, HttpServletResponse response) {

        var fullName = request.getParameter("full-name");
        var email = request.getParameter("email");
        var password = PasswordEncoder.encode(request.getParameter("password"));
        var birthDate = LocalDate.parse(request.getParameter("birth-date"));

        var path = "dispatcher:/signup";

        if (!(userDao.findUserByEmail(email).isPresent())) {
            var user = new User();
            user.setFullName(fullName);
            user.setEmail(email);
            user.setPassword(password);
            user.setBirthDate(birthDate);

            userDao.create(user);

            request.setAttribute("success", "Usuário cadastrado com sucesso");
            path = "dispatcher:/login";
        } else {
            request.setAttribute("error", "Email já cadastrado");
        }

        return path;
    }

    @Override
    public Object handleLogin(HttpServletRequest request, HttpServletResponse response) {

        String email = request.getParameter("email");
        String password = PasswordEncoder.encode(request.getParameter("password"));

        var user = userDao.findUserByEmail(email);

        if(user.isPresent() && user.get().getPassword().equals(password)) {
            var session = request.getSession();
            session.setMaxInactiveInterval(600);
            session.setAttribute("user", user.get());

            return "/index.jsp";
        }

        request.setAttribute("error", "Email ou Senha inválidos");
        return "dispatcher:/login";
    }

    @Override
    public Object handleLogout(HttpServletRequest request, HttpServletResponse response) {

        var session = request.getSession(false);
        session.invalidate();

        return "dispatcher:/login";
    }
}
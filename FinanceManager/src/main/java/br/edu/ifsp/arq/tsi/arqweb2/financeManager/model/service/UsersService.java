package br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.service;

import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.contracts.dao.IUserDao;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.contracts.services.IUsersService;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.dao.UserDao;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.user.User;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.utils.DataSourceSearcher;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.utils.PasswordEncoder;
import com.google.gson.JsonObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.time.LocalDate;

public class UsersService implements IUsersService {

    private final IUserDao userDao;

    public UsersService(){
        this.userDao = new UserDao(DataSourceSearcher.getInstance().getDataSource());
    }


    @Override
    public JsonObject handleRegister(HttpServletRequest request, HttpServletResponse response) {
        String fullName = request.getParameter("full-name");
        String email = request.getParameter("email");
        String password = PasswordEncoder.encode(request.getParameter("password"));
        LocalDate birthDate = LocalDate.parse(request.getParameter("birth-date"));

        var jsonResponse = new JsonObject();

        if (userDao.existsUserByEmail(email)) {
            jsonResponse.addProperty("error", "Email já cadastrado");
            return jsonResponse;
        }

        var user = new User();
        user.setFullName(fullName);
        user.setEmail(email);
        user.setPassword(password);
        user.setBirthDate(birthDate);

        userDao.create(user);

        jsonResponse.addProperty("success", "Usuário cadastrado com sucesso");
        return jsonResponse;
    }

    @Override
    public JsonObject handleLogin(HttpServletRequest request, HttpServletResponse response) {

        String email = request.getParameter("email");
        String password = PasswordEncoder.encode(request.getParameter("password"));

        var jsonResponse = new JsonObject();

        var user = userDao.findUserByEmail(email);

        if(user.isEmpty() || !user.get().getPassword().equals(password)) {
            jsonResponse.addProperty("error", "Email ou Senha inválidos");
            return jsonResponse;
        }

        var session = request.getSession();
        session.setMaxInactiveInterval(600);
        session.setAttribute("user", user.get());

        jsonResponse.addProperty("success", "Login efetuado com sucesso");
        return jsonResponse;
    }

    @Override
    public JsonObject handleLogout(HttpServletRequest request, HttpServletResponse response) {
        var session = request.getSession(false);
        session.invalidate();

        var jsonResponse = new JsonObject();
        jsonResponse.addProperty("success", "Logout efetuado com sucesso");

        return jsonResponse;
    }
}
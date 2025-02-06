package br.edu.ifsp.arq.tsi.arqweb2.financeManager.utils;

import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.user.User;
import jakarta.servlet.http.HttpServletRequest;

public class Utils {

    public static User getUser(HttpServletRequest request) {

        try{
            return (User) request.getSession(false).getAttribute("user");
        }catch (Exception e){
            return null;
        }
    }

    public static Boolean isLoggedIn(HttpServletRequest request) {
        var session = request.getSession(false);
        return (session != null && session.getAttribute("customer") != null);
    }
}

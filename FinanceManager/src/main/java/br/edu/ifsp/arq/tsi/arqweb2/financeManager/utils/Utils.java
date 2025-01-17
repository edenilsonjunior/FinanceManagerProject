package br.edu.ifsp.arq.tsi.arqweb2.financeManager.utils;

import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.user.User;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.http.HttpServletRequest;

public class Utils {

    private static Gson gson = new Gson();

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

    public static JsonObject createResponse(String title, Object value) {
        var response = new JsonObject();
        var jsonValue = gson.toJson(value);

        response.addProperty(title, jsonValue);
        return response;
    }
}

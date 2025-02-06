package br.edu.ifsp.arq.tsi.arqweb2.financeManager.utils;

import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.user.User;

import java.time.LocalDate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import jakarta.servlet.http.HttpServletRequest;

public class Utils {

    private static Gson gson;
    
    static {
        gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
    }

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

    public static JsonElement toJson(Object object) {
        return gson.toJsonTree(object);
    }
}

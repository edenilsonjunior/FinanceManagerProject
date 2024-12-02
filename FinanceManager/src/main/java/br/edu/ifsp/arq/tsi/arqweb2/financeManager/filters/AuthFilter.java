package br.edu.ifsp.arq.tsi.arqweb2.financeManager.filters;

import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.user.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = {
        "/index",
        "/index.jsp",
        "/WEB-INF/views/financial-record/*",
        "/create-category",
        "/create-financial-record",
        "/delete-financial-record",
        "/retrieve-categories",
        "/update-financial-record",
        "/board"
}, filterName = "Auth")
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
        } else {
            chain.doFilter(request, response);
        }

    }
}

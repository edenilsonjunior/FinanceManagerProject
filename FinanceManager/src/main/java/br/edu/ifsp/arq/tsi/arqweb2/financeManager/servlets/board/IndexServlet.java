package br.edu.ifsp.arq.tsi.arqweb2.financeManager.servlets.board;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public IndexServlet() {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
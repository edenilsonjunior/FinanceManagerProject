package br.edu.ifsp.arq.tsi.arqweb2.financeManager.servlets.board;

import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.dao.FinancialRecordDao;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.utils.DataSourceSearcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet("/board")
public class BoardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public BoardServlet() {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        var dao = new FinancialRecordDao(DataSourceSearcher.getInstance().getDataSource());

        var list = dao.findFinancialRecordsByUserId(1);

        request.setAttribute("financialRecords", list);

        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

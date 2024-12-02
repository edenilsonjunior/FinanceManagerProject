package br.edu.ifsp.arq.tsi.arqweb2.financeManager.servlets.financialRecord;

import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.dao.FinancialRecordDao;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.user.User;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.utils.DataSourceSearcher;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet("/history")
public class HistoryFinancialRecord extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public HistoryFinancialRecord() {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var financialRecordDao = new FinancialRecordDao(DataSourceSearcher.getInstance().getDataSource());


        var session = request.getSession(false);
        var user = (User) session.getAttribute("user");

        var financialRecordsDto = financialRecordDao.findFinancialRecordHistoryByUserId(user.getId());
        request.setAttribute("financialRecords", financialRecordsDto);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/financial-record/history-financial-record.jsp");
        dispatcher.forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
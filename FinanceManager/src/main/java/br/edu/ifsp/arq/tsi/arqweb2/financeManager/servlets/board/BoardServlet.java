package br.edu.ifsp.arq.tsi.arqweb2.financeManager.servlets.board;

import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.dao.FinancialRecordCategoryDao;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.dao.FinancialRecordDao;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.dto.BoardDto;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.user.User;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.utils.DataSourceSearcher;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


@WebServlet("/board")
public class BoardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public BoardServlet() {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        var financialRecordDao = new FinancialRecordDao(DataSourceSearcher.getInstance().getDataSource());
        var categoryDao = new FinancialRecordCategoryDao(DataSourceSearcher.getInstance().getDataSource());

        var content = new BoardDto(
                financialRecordDao.getOverviewByUserId(user.getId()),
                financialRecordDao.getMonthlyBalanceByUserId(user.getId()),
                categoryDao.getCategoryExpensesForCurrentMonthByUserId(user.getId())
        );

        String json = new Gson().toJson(content);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

package br.edu.ifsp.arq.tsi.arqweb2.financeManager.servlets.financialRecord;

import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.dao.FinancialRecordCategoryDao;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.financialRecord.FinancialRecordCategory;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.user.User;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.utils.DataSourceSearcher;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.sql.DataSource;
import java.io.IOException;


@WebServlet("/category")
public class CreateCategoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CreateCategoryServlet() {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("/category.jsp");
        dispatcher.forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");

        RequestDispatcher dispatcher = null;

        DataSource dataSource = DataSourceSearcher.getInstance().getDataSource();
        FinancialRecordCategoryDao financialRecordCategoryDao = new FinancialRecordCategoryDao(dataSource);

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        FinancialRecordCategory financialRecordCategory = new FinancialRecordCategory();
        financialRecordCategory.setName(name);
        financialRecordCategory.setUser(user);

        if(financialRecordCategoryDao.create(financialRecordCategory) != null) {
            dispatcher = request.getRequestDispatcher("/board");
        }else{
            dispatcher = request.getRequestDispatcher("/category.jsp");
        }

        dispatcher.forward(request, response);
    }
}
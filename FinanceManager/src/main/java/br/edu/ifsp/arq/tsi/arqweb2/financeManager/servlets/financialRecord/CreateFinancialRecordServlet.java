package br.edu.ifsp.arq.tsi.arqweb2.financeManager.servlets.financialRecord;

import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.dao.FinancialRecordCategoryDao;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.dao.FinancialRecordDao;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.financialRecord.FinancialRecord;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.financialRecord.FinancialRecordCategory;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.financialRecord.TransactionTypeEnum;
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


@WebServlet("/create-financial-record")
public class CreateFinancialRecordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CreateFinancialRecordServlet() {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        var financialRecordCategoryDao = new FinancialRecordCategoryDao(DataSourceSearcher.getInstance().getDataSource());

        if(request.getParameter("type").equals("expense")){

            var session = request.getSession(false);
            var user = (User) session.getAttribute("user");

            var categories = financialRecordCategoryDao.findFinancialRecordCategoriesByUserId(user.getId());
            request.setAttribute("userCategories", categories);
        }

        request.setAttribute("transactionType", TransactionTypeEnum.EXPENSE.toString());

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/financial-record/create-financial-record.jsp");
        dispatcher.forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //long categoryId = Long.parseLong(request.getParameter("categoryId"));
        String categoryId = request.getParameter("categoryId");
        double amount = Double.parseDouble(request.getParameter("amount"));
        TransactionTypeEnum transactionType = TransactionTypeEnum.valueOf(request.getParameter("transactionType"));
        String description = request.getParameter("description");

        RequestDispatcher dispatcher = null;

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        DataSource dataSource = DataSourceSearcher.getInstance().getDataSource();
        FinancialRecordCategoryDao financialRecordCategoryDao = new FinancialRecordCategoryDao(dataSource);

        FinancialRecord financialRecord = new FinancialRecord();
        financialRecord.setUser(user);
        financialRecord.setAmount(amount);
        financialRecord.setDescription(description);
        financialRecord.setTransactionType(transactionType);

        if(!categoryId.isEmpty()){
            FinancialRecordCategory financialRecordCategory = financialRecordCategoryDao.findFinancialRecordCategoryById(Long.parseLong(categoryId)).get();
            financialRecord.setCategory(financialRecordCategory);
        }

        FinancialRecordDao financialRecordDao = new FinancialRecordDao(dataSource);
        if(financialRecordDao.create(financialRecord) != null) {
            dispatcher = request.getRequestDispatcher("/index");
        }else{
            dispatcher = request.getRequestDispatcher("/financialRecord");
        }

        dispatcher.forward(request, response);
    }
}
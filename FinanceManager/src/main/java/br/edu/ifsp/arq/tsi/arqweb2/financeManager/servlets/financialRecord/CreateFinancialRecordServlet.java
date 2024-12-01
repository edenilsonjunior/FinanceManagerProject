package br.edu.ifsp.arq.tsi.arqweb2.financeManager.servlets.financialRecord;

import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.dao.FinancialRecordCategoryDao;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.dao.FinancialRecordDao;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.financialRecord.FinancialRecord;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.financialRecord.TransactionTypeEnum;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.utils.DataSourceSearcher;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.utils.Utils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet("/create-financial-record")
public class CreateFinancialRecordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CreateFinancialRecordServlet() {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        var categoryDao = new FinancialRecordCategoryDao(DataSourceSearcher.getInstance().getDataSource());
        var user = Utils.getUser(request);

        var path = "/WEB-INF/views/financial-record/create-financial-record.jsp";

        var categories = categoryDao.findFinancialRecordCategoriesByUserId(user.getId());
        request.setAttribute("userCategories", categories);

        request.getRequestDispatcher(path).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        var categoryId = request.getParameter("categorySelect");
        var amount = Double.parseDouble(request.getParameter("amount"));
        var description = request.getParameter("description");
        var transactionType = TransactionTypeEnum.valueOf(request.getParameter("transactionType"));

        var user = Utils.getUser(request);

        var dataSource = DataSourceSearcher.getInstance().getDataSource();
        var financialRecordCategoryDao = new FinancialRecordCategoryDao(dataSource);
        var financialRecordDao = new FinancialRecordDao(dataSource);

        var financialRecord = new FinancialRecord() {{
            setUser(user);
            setAmount(amount);
            setDescription(description);
            setTransactionType(transactionType);
        }};

        if(categoryId != null){
            var category = financialRecordCategoryDao
                    .findFinancialRecordCategoryById(Long.parseLong(categoryId))
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            financialRecord.setCategory(category);
        }

        if (financialRecordDao.create(financialRecord) == null) {
            request.setAttribute("financialRecordErrorMessage", "Houve um erro ao criar o registro financeiro");
            var dispatcher = request.getRequestDispatcher( "WEB-INF/views/user/login.jsp");
            dispatcher.forward(request, response);
        }else{
            request.getRequestDispatcher("/index").forward(request, response);
        }
    }
}
package br.edu.ifsp.arq.tsi.arqweb2.financeManager.servlets.financialRecord;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.dao.FinancialRecordDao;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.financialRecord.FinancialRecord;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.financialRecord.FinancialRecordCategory;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.utils.DataSourceSearcher;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/UpdateFinancialRecord")
public class UpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UpdateServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));

        FinancialRecordDao frd = new FinancialRecordDao(DataSourceSearcher.getInstance().getDataSource());
        FinancialRecord fr = frd.getById(id);
        List<FinancialRecordCategory> listCategories = frd.getAllCategoriesByName();

        request.setAttribute("financialRecord", fr);
        request.setAttribute("listCategories", listCategories);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/update.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        String categoryName = request.getParameter("categoryName");
        double amount = Double.parseDouble(request.getParameter("amount"));
        String transactionType = request.getParameter("transactionType");
        LocalDate transactionDate = LocalDate.parse(request.getParameter("transactionDate"));
        String description = request.getParameter("description");

        FinancialRecordDao frd = new FinancialRecordDao(DataSourceSearcher.getInstance().getDataSource());
        FinancialRecordCategory category = frd.getCategoryIdByName(categoryName);
        FinancialRecord fr = new FinancialRecord();

        fr.setId(id);
        fr.setCategory(category);
        fr.setAmount(amount);
        fr.setTransactionType(transactionType);
        fr.setTransactionDate(transactionDate);
        fr.setDescription(description);

        frd.update(fr);

        response.sendRedirect("financial-records");
    }
}
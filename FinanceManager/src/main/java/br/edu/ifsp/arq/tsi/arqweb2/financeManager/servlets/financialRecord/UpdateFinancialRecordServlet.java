package br.edu.ifsp.arq.tsi.arqweb2.financeManager.servlets.financialRecord;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.dao.FinancialRecordDao;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.financialRecord.FinancialRecord;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.financialRecord.FinancialRecordCategory;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.utils.DataSourceSearcher;
import com.google.gson.Gson;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/update-financial-record")
public class UpdateFinancialRecordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UpdateFinancialRecordServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));

        FinancialRecordDao frd = new FinancialRecordDao(DataSourceSearcher.getInstance().getDataSource());
        FinancialRecord fr = frd.getById(id);
        List<FinancialRecordCategory> listCategories = frd.getAllCategoriesByName();

        request.setAttribute("fr", fr);
        request.setAttribute("listCategories", listCategories);

        System.out.println(fr.getCategory().getName());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/financial-record/update-financial.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        String categoryName = request.getParameter("categorySelect");
        double amount = Double.parseDouble(request.getParameter("amount"));
        String description = request.getParameter("description");

        FinancialRecordDao frd = new FinancialRecordDao(DataSourceSearcher.getInstance().getDataSource());
        FinancialRecordCategory category = frd.getCategoryIdByName(categoryName);
        FinancialRecord fr = new FinancialRecord();

        fr.setId(id);
        fr.setCategory(category);
        fr.setAmount(amount);
        fr.setDescription(description);

        frd.update(fr);

        response.sendRedirect("history");
    }
}
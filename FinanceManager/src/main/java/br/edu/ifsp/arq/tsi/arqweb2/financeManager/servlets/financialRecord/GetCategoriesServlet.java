package br.edu.ifsp.arq.tsi.arqweb2.financeManager.servlets.financialRecord;

import java.io.IOException;
import java.util.List;
import com.google.gson.Gson;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.dao.FinancialRecordDao;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.financialRecord.FinancialRecordCategory;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.utils.DataSourceSearcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/GetCategories")
public class GetCategoriesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FinancialRecordDao frd = new FinancialRecordDao(DataSourceSearcher.getInstance().getDataSource());
        List<FinancialRecordCategory> categories = frd.getAllCategoriesByName();
        String json = new Gson().toJson(categories);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}
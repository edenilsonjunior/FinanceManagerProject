package br.edu.ifsp.arq.tsi.arqweb2.financeManager.servlets.financialRecord;

import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.dao.FinancialRecordCategoryDao;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.financialRecord.FinancialRecordCategory;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.utils.DataSourceSearcher;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.utils.Utils;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/create-category")
public class CreateCategoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CreateCategoryServlet() {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {

        var path = "/WEB-INF/views/financial-record/create-category.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");

        var categoryDao = new FinancialRecordCategoryDao(DataSourceSearcher.getInstance().getDataSource());

        var user = Utils.getUser(request);

        var financialRecordCategory = new FinancialRecordCategory();
        financialRecordCategory.setName(name);
        financialRecordCategory.setUser(user);

        String path = request.getContextPath() + "/index";

        if (categoryDao.create(financialRecordCategory) == null) {
            path = "/WEB-INF/views/financial-record/create-category.jsp";
            request.setAttribute("financialRecordErrorMessage", "Houve um erro ao criar a categoria.");
            request.getRequestDispatcher( path).forward(request, response);
        }else{
            // Set success message via session because sendRedirect doesn't keep request attributes
            // And we need to use sendRedirect to show the new url in the browser
            request.getSession().setAttribute("successMessage", "Categoria criada com sucesso!");
            response.sendRedirect(path);
        }
    }

}
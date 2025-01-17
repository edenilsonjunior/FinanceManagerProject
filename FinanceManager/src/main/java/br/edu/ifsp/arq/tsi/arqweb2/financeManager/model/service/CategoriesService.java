package br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.service;

import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.contracts.services.ICategoriesService;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.dao.FinancialRecordCategoryDao;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.financialRecord.FinancialRecordCategory;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.utils.DataSourceSearcher;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public class CategoriesService implements ICategoriesService {

    private final FinancialRecordCategoryDao categoryDao;
    private final Gson gson = new Gson();

    public CategoriesService(){
        this.categoryDao = new FinancialRecordCategoryDao(DataSourceSearcher.getInstance().getDataSource());
    }

    @Override
    public JsonObject handleCreateCategory(HttpServletRequest request, HttpServletResponse response) {

        var name = request.getParameter("name");
        var user = Utils.getUser(request);

        var financialRecordCategory = new FinancialRecordCategory();
        financialRecordCategory.setName(name);
        financialRecordCategory.setUser(user);

        var jsonResponse = new JsonObject();

        if (categoryDao.create(financialRecordCategory) == null)
            jsonResponse.addProperty("error", "Houve um erro ao criar a categoria.");
        else
            jsonResponse.addProperty("success", "Categoria criada com sucesso!");

        return jsonResponse;
    }

    @Override
    public JsonObject handleGetCategoriesByUser(HttpServletRequest request, HttpServletResponse response) {

        var user = Utils.getUser(request);
        var categories = categoryDao.findByUserId(user.getId());

        var jsonResponse = new JsonObject();
        jsonResponse.add("categories", gson.toJsonTree(categories));

        return jsonResponse;
    }
}
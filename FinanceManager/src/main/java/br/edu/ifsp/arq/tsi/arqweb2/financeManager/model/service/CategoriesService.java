package br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.service;

import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.contracts.dao.IFinancialRecordCategoryDao;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.contracts.services.ICategoriesService;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.financialRecord.FinancialRecordCategory;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.utils.Utils;

import com.google.gson.JsonElement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class CategoriesService implements ICategoriesService {

    private final IFinancialRecordCategoryDao categoryDao;

    public CategoriesService(IFinancialRecordCategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public Object handleCreateCategory(HttpServletRequest request, HttpServletResponse response) {

        var user = Utils.getUser(request);
        var name = request.getParameter("name");

        var financialRecordCategory = new FinancialRecordCategory();
        financialRecordCategory.setName(name);
        financialRecordCategory.setUser(user);

        var session = request.getSession(false);

        if(categoryDao.existsByNameAndUserId(name, user.getId())){
            session.setAttribute("error", "Categoria já existe.");
            return "/index";
        }

        if (categoryDao.create(financialRecordCategory) != null)
            session.setAttribute("success", "Categoria criada com sucesso!");
        else
            session.setAttribute("error", "Houve um erro ao criar a categoria.");

        return "/index";
    }

    @Override
    public JsonElement handleGetCategoriesByUser(HttpServletRequest request, HttpServletResponse response) throws Exception {

        var user = Utils.getUser(request);
        var categories = categoryDao.findByUserId(user.getId());
        
        return Utils.toJson(categories);
    }
}
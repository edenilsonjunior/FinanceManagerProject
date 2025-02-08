package br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.service;

import com.google.gson.JsonElement;

import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.contracts.dao.IFinancialRecordCategoryDao;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.contracts.dao.IFinancialRecordDao;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.contracts.services.IFinancialRecordsService;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.financialRecord.FinancialRecord;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.financialRecord.TransactionTypeEnum;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.user.User;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.HashMap;

public class FinancialRecordsService implements IFinancialRecordsService {

    private final IFinancialRecordDao financialRecordDao;
    private final IFinancialRecordCategoryDao categoryDao;

    public FinancialRecordsService(IFinancialRecordDao financialRecordDao, IFinancialRecordCategoryDao categoryDao) {
        this.financialRecordDao = financialRecordDao;
        this.categoryDao = categoryDao;
    }

    @Override
    public Object handleCreate(HttpServletRequest request, HttpServletResponse response) {

        var categoryId = request.getParameter("categoryId");
        var amount = Double.parseDouble(request.getParameter("amount"));
        var description = request.getParameter("description");
        var transactionType = TransactionTypeEnum.valueOf(request.getParameter("transactionType"));

        var user = Utils.getUser(request);

        var financialRecord = new FinancialRecord() {{
            setUser(user);
            setAmount(amount);
            setDescription(description);
            setTransactionType(transactionType);
        }};

        if(categoryId != null){
            var category = categoryDao
                    .findById(Long.parseLong(categoryId))
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            financialRecord.setCategory(category);
        }

        if (financialRecordDao.create(financialRecord) == null) {
            request.setAttribute("error", "Houve um erro ao criar o registro financeiro");
            return "dispatcher:/create-financial-record";
        }

        request.getSession(false).setAttribute("success", "Registro financeiro criado com sucesso");
        return "/index";
    }

    @Override
    public Object handleUpdate(HttpServletRequest request, HttpServletResponse response) {

        var id = Long.parseLong(request.getParameter("id"));
        var categoryId = Long.parseLong(request.getParameter("categoryId"));
        var amount = Double.parseDouble(request.getParameter("amount"));
        var description = request.getParameter("description");

        var category = categoryDao
            .findById(categoryId)
            .orElseThrow(() -> new RuntimeException("Category not found"));

        var financialRecord = new FinancialRecord();
        financialRecord.setId(id);
        financialRecord.setCategory(category);
        financialRecord.setAmount(amount);
        financialRecord.setDescription(description);

        financialRecordDao.update(financialRecord);

        return "/history";
    }

    @Override
    public Object handleDelete(HttpServletRequest request, HttpServletResponse response) {

        long id = Long.parseLong(request.getParameter("id"));

        var session = request.getSession(false);

        if (financialRecordDao.delete(id))
           session.setAttribute("success", "Registro deletado com sucesso!");
        else
           session.setAttribute("error", "Houve um erro ao excluir o registro!");

        return "/index";
    }

    @Override
    public Object handleCreateView(HttpServletRequest request, HttpServletResponse response) throws Exception {

        var user = Utils.getUser(request);

        var categories = categoryDao.findByUserId(user.getId());
        request.setAttribute("userCategories", categories);

        return "dispatcher:/create-financial-record";
    }

    @Override
    public JsonElement handleUpdateView(HttpServletRequest request, HttpServletResponse response) throws Exception {

        var user = Utils.getUser(request);

        long id = Long.parseLong(request.getParameter("id"));
        var financialRecord = financialRecordDao.findById(id);

        if(user == null || financialRecord.getUser().getId() != user.getId()){
            throw new Exception("Acesso negado");
        }

        var categories = categoryDao.findByUserId(user.getId());

        var responseContent = new HashMap<String, Object>();

        responseContent.put("financialRecord", financialRecord);
        responseContent.put("categories", categories);

        return Utils.toJson(responseContent);
    }

    @Override
    public JsonElement handleHistory(HttpServletRequest request, HttpServletResponse response) {

        var session = request.getSession(false);
        var user = (User) session.getAttribute("user");

        var financialRecordsDto = financialRecordDao.findFinancialRecordHistoryByUserId(user.getId());
        
        return Utils.toJson(financialRecordsDto);
    }
}
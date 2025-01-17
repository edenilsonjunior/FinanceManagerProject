package br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.service;

import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.contracts.dao.IFinancialRecordCategoryDao;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.contracts.dao.IFinancialRecordDao;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.contracts.services.IFinancialRecordsService;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.dao.FinancialRecordCategoryDao;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.dao.FinancialRecordDao;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.financialRecord.FinancialRecord;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.financialRecord.TransactionTypeEnum;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.utils.DataSourceSearcher;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.utils.Utils;
import com.google.gson.JsonObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FinancialRecordsService implements IFinancialRecordsService {

    private final IFinancialRecordDao financialRecordDao;
    private final IFinancialRecordCategoryDao categoryDao;

    public FinancialRecordsService(){
        var dataSource = DataSourceSearcher.getInstance().getDataSource();

        this.financialRecordDao = new FinancialRecordDao(dataSource);
        this.categoryDao = new FinancialRecordCategoryDao(dataSource);
    }

    @Override
    public JsonObject handleCreate(HttpServletRequest request, HttpServletResponse response) {

        var categoryId = Long.parseLong(request.getParameter("categoryId"));
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

        if(categoryId != 0){
            var category = categoryDao
                    .findById(categoryId)
                    .orElseThrow(() -> new RuntimeException("Category not found"));

            financialRecord.setCategory(category);
        }

        if (financialRecordDao.create(financialRecord) != null)
            return Utils.createResponse("success", "Registro financeiro criado com sucesso");

        return Utils.createResponse("error", "Houve um erro ao criar o registro financeiro");
    }

    @Override
    public JsonObject handleUpdate(HttpServletRequest request, HttpServletResponse response) {

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

        if (financialRecordDao.update(financialRecord))
            return Utils.createResponse("success", "Registro financeiro atualizado com sucesso");

        return Utils.createResponse("error", "Houve um erro ao atualizar o registro financeiro");
    }

    @Override
    public JsonObject handleDelete(HttpServletRequest request, HttpServletResponse response) {

        long id = Long.parseLong(request.getParameter("id"));

        if(financialRecordDao.delete(id))
            return Utils.createResponse("success", "Registro financeiro deletado com sucesso");

        return Utils.createResponse("error", "Houve um erro ao deletar o registro financeiro");
    }

    @Override
    public JsonObject handleGetByUser(HttpServletRequest request, HttpServletResponse response) {

        var user = Utils.getUser(request);

        var financialRecordsDto = financialRecordDao
                .findFinancialRecordHistoryByUserId(user.getId());

        return Utils.createResponse("financialRecords", financialRecordsDto);
    }
}
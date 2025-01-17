package br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.service;

import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.contracts.services.IBoardService;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.dao.FinancialRecordCategoryDao;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.dao.FinancialRecordDao;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.dto.BoardDto;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.utils.DataSourceSearcher;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.utils.Utils;
import com.google.gson.JsonObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BoardService implements IBoardService {

    private final FinancialRecordDao financialRecordDao;
    private final FinancialRecordCategoryDao categoryDao;

    public BoardService(){
        var dataSource = DataSourceSearcher.getInstance().getDataSource();

        this.financialRecordDao = new FinancialRecordDao(dataSource);
        this.categoryDao = new FinancialRecordCategoryDao(dataSource);
    }

    @Override
    public JsonObject handlePreview(HttpServletRequest request, HttpServletResponse response) {

        var user = Utils.getUser(request);

        var content = new BoardDto(
                financialRecordDao.getOverviewByUserId(user.getId()),
                financialRecordDao.getMonthlyBalanceByUserId(user.getId()),
                categoryDao.getCategoryExpensesForCurrentMonthByUserId(user.getId())
        );

        return Utils.createResponse("content", content);
    }
}
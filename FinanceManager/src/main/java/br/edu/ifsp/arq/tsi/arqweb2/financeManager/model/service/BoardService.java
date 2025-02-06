package br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.service;

import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.contracts.dao.IFinancialRecordCategoryDao;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.contracts.dao.IFinancialRecordDao;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.contracts.services.IBoardService;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.dto.BoardDto;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.user.User;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.utils.Utils;

import com.google.gson.JsonElement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class BoardService implements IBoardService {

    private final IFinancialRecordDao financialRecordDao;
    private final IFinancialRecordCategoryDao categoryDao;

    public BoardService(IFinancialRecordDao financialRecordDao, IFinancialRecordCategoryDao categoryDao) {
        this.financialRecordDao = financialRecordDao;
        this.categoryDao = categoryDao;
    }

    @Override
    public JsonElement handlePreview(HttpServletRequest request, HttpServletResponse response) {

       HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        var content = new BoardDto(
                financialRecordDao.getOverviewByUserId(user.getId()),
                financialRecordDao.getMonthlyBalanceByUserId(user.getId()),
                categoryDao.getCategoryExpensesForCurrentMonthByUserId(user.getId())
        );

        return Utils.toJson(content);
    }
}
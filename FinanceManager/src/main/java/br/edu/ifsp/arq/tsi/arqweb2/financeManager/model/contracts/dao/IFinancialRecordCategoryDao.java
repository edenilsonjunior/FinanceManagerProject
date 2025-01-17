package br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.contracts.dao;

import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.dto.CategoryDto;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.financialRecord.FinancialRecordCategory;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface IFinancialRecordCategoryDao {
    FinancialRecordCategory create(FinancialRecordCategory financialRecordCategory);
    Optional<FinancialRecordCategory> findById(long id);
    List<FinancialRecordCategory> findByUserId(long userId) throws Exception;
    List<CategoryDto> getCategoryExpensesForCurrentMonthByUserId(long userId);
}
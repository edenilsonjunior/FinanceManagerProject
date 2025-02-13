package br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.contracts.dao;

import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.dto.CategoryDto;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.dto.GetCategoryByUserDto;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.financialRecord.FinancialRecordCategory;

import java.util.List;
import java.util.Optional;

public interface IFinancialRecordCategoryDao {
    FinancialRecordCategory create(FinancialRecordCategory financialRecordCategory);
    Optional<FinancialRecordCategory> findById(long id);
    List<GetCategoryByUserDto> findByUserId(long userId) throws Exception;
    List<CategoryDto> getCategoryExpensesByUserId(long userId);
    boolean existsByNameAndUserId(String name, long userId);
}
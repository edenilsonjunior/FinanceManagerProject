package br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.contracts.dao;

import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.dto.FinancialRecordDto;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.dto.GetMonthBalanceDto;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.financialRecord.FinancialRecord;

import java.util.List;
import java.util.Map;

public interface IFinancialRecordDao {
    FinancialRecord create(FinancialRecord financialRecord);
    boolean update(FinancialRecord financialRecord);
    boolean delete (long financialRecordId);
    FinancialRecord findById(long id);
    Map<String, Double> getOverviewByUserId(long userId);
    List<GetMonthBalanceDto> getBalanceByUserGroupByMonth(long userId);
    List<FinancialRecordDto> findFinancialRecordHistoryByUserId(long userId);
}
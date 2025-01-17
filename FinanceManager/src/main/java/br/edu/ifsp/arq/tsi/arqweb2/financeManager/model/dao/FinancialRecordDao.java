package br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.dao;

import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.contracts.dao.IFinancialRecordDao;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.dao.queries.FinancialRecordQueries;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.dto.FinancialRecordDto;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.financialRecord.FinancialRecord;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FinancialRecordDao implements IFinancialRecordDao {

    private final DataSource dataSource;

    public FinancialRecordDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public FinancialRecord create(FinancialRecord financialRecord) {
        try (var con = dataSource.getConnection();
             var ps = con.prepareStatement(FinancialRecordQueries.CREATE, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setLong(1, financialRecord.getUser().getId());
            if(financialRecord.getCategory() != null)
                ps.setLong(2, financialRecord.getCategory().getId());
            else
                ps.setNull(2, java.sql.Types.INTEGER);
            ps.setDouble(3, financialRecord.getAmount());
            ps.setString(4, financialRecord.getTransactionType().toString());
            ps.setString(5, financialRecord.getDescription());
            ps.executeUpdate();
            var rs = ps.getGeneratedKeys();
            if (rs.next()) {
                financialRecord.setId(rs.getLong(1));
            }

            return financialRecord;
        } catch (SQLException sqlException) {
            throw new RuntimeException("Erro durante a criacao no BD", sqlException);
        }
    }

    @Override
    public boolean update(FinancialRecord financialRecord) {
        try (var con = dataSource.getConnection();
             var ps = con.prepareStatement(FinancialRecordQueries.UPDATE)) {

            ps.setLong(1, financialRecord.getCategory().getId());
            ps.setDouble(2, financialRecord.getAmount());
            ps.setString(3, financialRecord.getDescription());
            ps.setLong(4, financialRecord.getId());

            return ps.executeUpdate() > 0;
        } catch (SQLException sqlException) {
            throw new RuntimeException("Erro SQL: ", sqlException);
        }
    }

    @Override
    public boolean delete (long financialRecordId) {
        try (var con = dataSource.getConnection();
             var ps = con.prepareStatement(FinancialRecordQueries.DELETE)) {

            ps.setLong(1, financialRecordId);

            return ps.executeUpdate() > 0;
        } catch (SQLException sqlException) {
            throw new RuntimeException("Erro SQL: ", sqlException);
        }

    }

    @Override
    public Map<String, Double> getOverviewByUserId(long userId){

        try (var con = dataSource.getConnection();
             var ps = con.prepareStatement(FinancialRecordQueries.SELECT_OVERVIEW_BY_USER_ID)) {

            ps.setLong(1, userId);

            var rs = ps.executeQuery();
            double totalIncome = 0.0;
            double totalExpense = 0.0;
            double currentBalance = 0.0;

            if (rs.next()) {
                totalIncome = rs.getDouble("total_income");
                totalExpense = rs.getDouble("total_expense");
                currentBalance = rs.getDouble("current_balance");
            }

            return Map.of(
                    "totalIncome", totalIncome,
                    "totalExpense", totalExpense,
                    "currentBalance", currentBalance
            );

        } catch (SQLException sqlException) {
            throw new RuntimeException("Erro SQL: ", sqlException);
        }
    }

    @Override
    public Map<String, Double> getMonthlyBalanceByUserId(long userId){

        try (var con = dataSource.getConnection();
             var ps = con.prepareStatement(FinancialRecordQueries.SELECT_MONTHLY_BALANCE_BY_USER_ID)) {

            ps.setLong(1, userId);

            var rs = ps.executeQuery();
            double totalIncome = 0.0;
            double totalExpense = 0.0;
            double currentBalance = 0.0;

            if (rs.next()) {
                totalIncome = rs.getDouble("total_income");
                totalExpense = rs.getDouble("total_expense");
                currentBalance = rs.getDouble("current_balance");
            }

            return Map.of(
                    "totalIncome", totalIncome,
                    "totalExpense", totalExpense,
                    "currentBalance", currentBalance
            );

        } catch (SQLException sqlException) {
            throw new RuntimeException("Erro SQL: ", sqlException);
        }
    }

    @Override
    public List<FinancialRecordDto> findFinancialRecordHistoryByUserId(long userId){
        var list = new ArrayList<FinancialRecordDto>();

        try (var con = dataSource.getConnection();
             var ps = con.prepareStatement(FinancialRecordQueries.SELECT_HISTORY_BY_USER_ID)) {

            ps.setLong(1, userId);
            var rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new FinancialRecordDto(
                        rs.getLong("id"),
                        rs.getString("category_name"),
                        rs.getDouble("amount"),
                        rs.getString("transaction_type"),
                        rs.getDate("transaction_date").toLocalDate(),
                        rs.getString("description")
                ));
            }
            return list;
        } catch (SQLException sqlException) {
            throw new RuntimeException("Erro SQL: ", sqlException);
        }
    }
}
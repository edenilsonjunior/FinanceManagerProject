package br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.dao;

import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.dao.queries.FinancialRecordQueries;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.dto.FinancialRecordDto;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.financialRecord.FinancialRecord;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.financialRecord.FinancialRecordCategory;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.financialRecord.TransactionTypeEnum;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FinancialRecordDao {

    private final DataSource dataSource;

    public FinancialRecordDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

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

    public List<FinancialRecord> findFinancialRecordsByUserId(long userId){

        var list = new ArrayList<FinancialRecord>();

        try (var con = dataSource.getConnection();
             var ps = con.prepareStatement(FinancialRecordQueries.SELECT)) {

            ps.setLong(1, userId);

            var rs = ps.executeQuery();
            while (rs.next()) {

                var category = new FinancialRecordCategory();
                category.setId(rs.getLong("category_id"));
                category.setName(rs.getString("category_name"));

                var financialRecord = new FinancialRecord();
                financialRecord.setId(rs.getLong("id"));
                financialRecord.setCategory(category);
                financialRecord.setAmount(rs.getDouble("amount"));
                financialRecord.setTransactionType(TransactionTypeEnum.valueOf(rs.getString("transaction_type")));
                financialRecord.setTransactionDate(LocalDate.parse(rs.getDate("transaction_date").toString()));
                financialRecord.setDescription(rs.getString("description"));

                list.add(financialRecord);
            }
            return list;
        } catch (SQLException sqlException) {
            throw new RuntimeException("Erro durante a consulta no BD", sqlException);
        }

    }

    public boolean update(FinancialRecord financialRecord) {
        try (var con = dataSource.getConnection();
             var ps = con.prepareStatement(FinancialRecordQueries.UPDATE)) {

            ps.setLong(1, financialRecord.getCategory().getId());
            ps.setDouble(2, financialRecord.getAmount());
            ps.setString(3, financialRecord.getTransactionType().toString());
            ps.setDate(4, java.sql.Date.valueOf(financialRecord.getTransactionDate()));
            ps.setString(5, financialRecord.getDescription());
            ps.setLong(6, financialRecord.getId());

        return ps.executeUpdate() > 0;
    } catch (SQLException sqlException) {
        throw new RuntimeException("Erro SQL: ", sqlException);
    }
}

    public boolean delete (long financialRecordId) {
        try (var con = dataSource.getConnection();
             var ps = con.prepareStatement(FinancialRecordQueries.DELETE)) {

            ps.setLong(1, financialRecordId);

        return ps.executeUpdate() > 0;
        } catch (SQLException sqlException) {
            throw new RuntimeException("Erro SQL: ", sqlException);
        }

    }

    public FinancialRecord getById(long id) {
        try (var con = dataSource.getConnection();
             var ps = con.prepareStatement(FinancialRecordQueries.SELECT_BY_ID)) {

            ps.setLong(1, id);

            var rs = ps.executeQuery();
            if (rs.next()) {
                var financialRecord = new FinancialRecord();
                var category = new FinancialRecordCategory();

                category.setId(rs.getLong("category_id"));
                getCategoryById(category.getId());
                category.setName(category.getName());

                financialRecord.setId(rs.getLong("id"));
                financialRecord.setCategory(category);
                financialRecord.setAmount(rs.getDouble("amount"));
                financialRecord.setTransactionDate(LocalDate.parse(rs.getDate("transaction_date").toString()));
                financialRecord.setDescription(rs.getString("description"));
                financialRecord.setTransactionType(TransactionTypeEnum.valueOf(rs.getString("transaction_type")));

                return financialRecord;
            }
        } catch (SQLException sqlException) {
            throw new RuntimeException("Erro SQL: ", sqlException);
        }
        return null;
    }

    public List<FinancialRecordCategory> getAllCategoriesByName() {
        var list = new ArrayList<FinancialRecordCategory>();

        try (var con = dataSource.getConnection();
             var ps = con.prepareStatement(FinancialRecordQueries.SELECT_ALL_CATEGORY)) {

            var rs = ps.executeQuery();
            while (rs.next()) {
                var category = new FinancialRecordCategory();
                category.setId(rs.getLong("id"));
                category.setName(rs.getString("name"));
                list.add(category);
            }
            return list;
        } catch (SQLException sqlException) {
            throw new RuntimeException("Erro SQL: ", sqlException);
        }
    }

    public FinancialRecordCategory getCategoryIdByName(String category) {
        try (var con = dataSource.getConnection();
             var ps = con.prepareStatement(FinancialRecordQueries.SELECT_CATEGORY_ID_BY_NAME)) {

            ps.setString(1, category);

            var rs = ps.executeQuery();
            if (rs.next()) {
                var categoryObj = new FinancialRecordCategory();
                categoryObj.setId(rs.getLong("id"));
                categoryObj.setName(rs.getString("name"));
                return categoryObj;
            }
        } catch (SQLException sqlException) {
            throw new RuntimeException("Erro SQL: ", sqlException);
        }
        return null;
    }

    public FinancialRecordCategory getCategoryById(long categoryId) {
        try (var con = dataSource.getConnection();
             var ps = con.prepareStatement(FinancialRecordQueries.SELECT_CATEGORY_BY_ID)) {

            ps.setLong(1, categoryId);

            var rs = ps.executeQuery();
            if (rs.next()) {
                var category = new FinancialRecordCategory();
                category.setId(rs.getLong("id"));
                category.setName(rs.getString("name"));
                return category;
            }
        } catch (SQLException sqlException) {
            throw new RuntimeException("Erro SQL: ", sqlException);
        }
        return null;
    }


    public Map<String, Double> getOverviewByUserId(long userId){

        var map = new HashMap<String, Double>();

        try (var con = dataSource.getConnection();
             var ps = con.prepareStatement(FinancialRecordQueries.SELECT_OVERVIEW_BY_USER_ID)) {

            ps.setLong(1, userId);

            var rs = ps.executeQuery();
            if (rs.next()) {
                var totalIncome = rs.getDouble("total_income");
                var totalExpense = rs.getDouble("total_expense");
                var currentBalance = rs.getDouble("current_balance");

                map.put("totalIncome", totalIncome);
                map.put("totalExpense", totalExpense);
                map.put("currentBalance", currentBalance);
            }
            return map;

        } catch (SQLException sqlException) {
            throw new RuntimeException("Erro SQL: ", sqlException);
        }
    }

    public Map<String, Double> getMonthlyBalanceByUserId(long userId){

        var map = new HashMap<String, Double>();

        try (var con = dataSource.getConnection();
             var ps = con.prepareStatement(FinancialRecordQueries.SELECT_MONTHLY_BALANCE_BY_USER_ID)) {

            ps.setLong(1, userId);

            var rs = ps.executeQuery();
            if (rs.next()) {
                var totalIncome = rs.getDouble("total_income");
                var totalExpense = rs.getDouble("total_expense");
                var currentBalance = rs.getDouble("current_balance");

                map.put("totalIncome", totalIncome);
                map.put("totalExpense", totalExpense);
                map.put("currentBalance", currentBalance);
            }
            return map;

        } catch (SQLException sqlException) {
            throw new RuntimeException("Erro SQL: ", sqlException);
        }

    }

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

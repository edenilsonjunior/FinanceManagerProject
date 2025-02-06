package br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.dao;

import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.contracts.dao.IFinancialRecordCategoryDao;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.contracts.dao.IFinancialRecordDao;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.contracts.dao.IUserDao;
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
import java.util.List;
import java.util.Map;

public class FinancialRecordDao implements IFinancialRecordDao {

    private final DataSource dataSource;
    private final IFinancialRecordCategoryDao categoryDao;
    private final IUserDao userDao;

    public FinancialRecordDao(DataSource dataSource, IFinancialRecordCategoryDao categoryDao, IUserDao userDao) {
        this.dataSource = dataSource;
        this.categoryDao = categoryDao;
        this.userDao = userDao;
    }

    @Override
    public FinancialRecord create(FinancialRecord financialRecord) {
        try (var con = dataSource.getConnection();
             var ps = con.prepareStatement(FinancialRecordQueries.CREATE, new String[]{"id"})) {

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
               var fr = new FinancialRecordDto(
                        rs.getLong("id"),
                        rs.getString("category_name"),
                        rs.getDouble("amount"),
                        rs.getString("transaction_type"),
                        rs.getDate("transaction_date").toLocalDate(),
                        rs.getString("description")
                );

               if(fr.getCategoryName() == null)
                   fr.setCategoryName("");

                list.add(fr);
            }
            return list;
        } catch (SQLException sqlException) {
            throw new RuntimeException("Erro SQL: ", sqlException);
        }
    }

    @Override
    public FinancialRecord findById(long id) {
        try (var con = dataSource.getConnection();
             var ps = con.prepareStatement(FinancialRecordQueries.SELECT_BY_ID)) {

            ps.setLong(1, id);
            var rs = ps.executeQuery();

            if (rs.next()) {
                var financialRecord = new FinancialRecord();
                var category = new FinancialRecordCategory();

                financialRecord.setId(rs.getLong("id"));
                category.setId(rs.getLong("category_id"));
                category = categoryDao.findById(category.getId()).get();
                financialRecord.setCategory(category);
                financialRecord.setAmount(rs.getDouble("amount"));
                financialRecord.setTransactionType(TransactionTypeEnum.valueOf(rs.getString("transaction_type")));
                financialRecord.setTransactionDate(LocalDate.parse(rs.getDate("transaction_date").toString()));
                financialRecord.setDescription(rs.getString("description"));

                var user = userDao.findById(rs.getLong("user_id")).get();
                financialRecord.setUser(user);

                return financialRecord;
            }
        } catch (SQLException sqlException) {
            throw new RuntimeException("Erro SQL: ", sqlException);
        }
        return null;
    }
}

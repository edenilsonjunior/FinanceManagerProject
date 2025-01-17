package br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.dao;

import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.contracts.dao.IFinancialRecordCategoryDao;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.dao.queries.FinancialRecordCategoryQueries;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.dto.CategoryDto;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.financialRecord.FinancialRecordCategory;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FinancialRecordCategoryDao implements IFinancialRecordCategoryDao {

    private final DataSource dataSource;

    public FinancialRecordCategoryDao(DataSource dataSource) { this.dataSource = dataSource; }

    @Override
    public FinancialRecordCategory create(FinancialRecordCategory financialRecordCategory) {
        try (var con = dataSource.getConnection();
             var ps = con.prepareStatement(FinancialRecordCategoryQueries.CREATE, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setLong(1, financialRecordCategory.getUser().getId());
            ps.setString(2, financialRecordCategory.getName());
            ps.executeUpdate();
            var rs = ps.getGeneratedKeys();
            if (rs.next()) {
                financialRecordCategory.setId(rs.getLong(1));
            }

            return financialRecordCategory;
        } catch (SQLException sqlException) {
            throw new RuntimeException("Erro durante a criacao no BD", sqlException);
        }
    }

    @Override
    public Optional<FinancialRecordCategory> findById(long id){
        return Optional.empty();
    }

    @Override
    public List<FinancialRecordCategory> findByUserId(long userId) throws Exception {
        var list = new ArrayList<FinancialRecordCategory>();

        try (var con = dataSource.getConnection();
             var ps = con.prepareStatement(FinancialRecordCategoryQueries.SELECT_BY_USER_ID)) {

            ps.setLong(1, userId);

            var rs = ps.executeQuery();
            while (rs.next()) {

                var category = new FinancialRecordCategory();
                category.setId(rs.getLong("id"));
                category.setName(rs.getString("name"));

                list.add(category);
            }
            return list;
        } catch (SQLException sqlException) {
            throw new RuntimeException("Erro durante a consulta no BD", sqlException);
        }
    }

    @Override
    public List<CategoryDto> getCategoryExpensesForCurrentMonthByUserId(long userId){

        var list = new ArrayList<CategoryDto>();

        try (var con = dataSource.getConnection();
             var ps = con.prepareStatement(FinancialRecordCategoryQueries.GET_CATEGORY_EXPENSES_FOR_CURRENT_MONTH_BY_USER_ID)) {

            ps.setLong(1, userId);

            var rs = ps.executeQuery();
            while (rs.next()) {
                list.add( new CategoryDto(
                        rs.getString("category"),
                        rs.getDouble("amount")
                ));
            }
            return list;
        } catch (SQLException sqlException) {
            throw new RuntimeException("Erro durante a consulta no BD", sqlException);
        }
    }
}

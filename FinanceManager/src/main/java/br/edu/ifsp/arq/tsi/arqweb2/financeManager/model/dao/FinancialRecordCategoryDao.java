package br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.dao;

import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.financialRecord.FinancialRecord;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.financialRecord.FinancialRecordCategory;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FinancialRecordCategoryDao {

    private final DataSource dataSource;

    public FinancialRecordCategoryDao(DataSource dataSource) { this.dataSource = dataSource; }

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

    public Optional<FinancialRecordCategory> findFinancialRecordCategoryById(long id) {

        try (var con = dataSource.getConnection();
             var ps = con.prepareStatement(FinancialRecordCategoryQueries.SELECT)) {

            ps.setLong(1, id);

            var category = new FinancialRecordCategory();
            var rs = ps.executeQuery();
            if (rs.next()) {


                category.setId(id);
                category.setUser(new UserDao(dataSource).findUserByUserId(rs.getLong("user_id")).get().getFirst());
                category.setName(rs.getString("name"));
            }
            return Optional.of(category);
        } catch (SQLException sqlException) {
            throw new RuntimeException("Erro durante a consulta no BD", sqlException);
        }
    }

    public List<FinancialRecordCategory> findFinancialRecordCategoriesByUserId(long userId) {
        var list = new ArrayList<FinancialRecordCategory>();

        try (var con = dataSource.getConnection();
             var ps = con.prepareStatement(FinancialRecordCategoryQueries.SELECT_BY_USER_ID)) {

            ps.setLong(1, userId);

            var rs = ps.executeQuery();
            while (rs.next()) {

                var category = new FinancialRecordCategory();
                category.setId(rs.getLong("user_id"));
                category.setName(rs.getString("name"));

                list.add(category);
            }
            return list;
        } catch (SQLException sqlException) {
            throw new RuntimeException("Erro durante a consulta no BD", sqlException);
        }
    }
}

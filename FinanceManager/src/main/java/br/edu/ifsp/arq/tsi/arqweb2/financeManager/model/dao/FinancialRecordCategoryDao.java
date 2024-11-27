package br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.dao;

import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.financialRecord.FinancialRecordCategory;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}

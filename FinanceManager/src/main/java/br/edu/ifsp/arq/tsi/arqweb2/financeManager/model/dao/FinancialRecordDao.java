package br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.dao;

import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.financialRecord.FinancialRecord;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.financialRecord.FinancialRecordCategory;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.financialRecord.TransactionTypeEnum;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
}

package br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.dao;

import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.financialRecord.FinancialRecord;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.financialRecord.FinancialRecordCategory;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FinancialRecordDao {

    private final DataSource dataSource;

    public FinancialRecordDao(DataSource dataSource) {
        this.dataSource = dataSource;
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
                financialRecord.setTransactionType(rs.getString("transaction_type"));
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

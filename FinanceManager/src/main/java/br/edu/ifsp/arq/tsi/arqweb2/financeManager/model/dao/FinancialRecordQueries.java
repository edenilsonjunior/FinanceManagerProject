package br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.dao;

public interface FinancialRecordQueries {

    String SELECT = """
        SELECT fr.id,
               fr.user_id,
               fr.amount,
               fr.transaction_type,
               fr.transaction_date,
               fr.description,
               c.id AS category_id,
               c.name AS category_name
        FROM financial_record fr
        JOIN category c
                on c.id = fr.category_id
        WHERE fr.user_id = ?;
        """;

}

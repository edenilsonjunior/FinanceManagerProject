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

    String UPDATE = """
        UPDATE financial_record
        SET category_id = ?,
            amount = ?,
            transaction_type = ?,
            transaction_date = ?,
            description = ?
        WHERE id = ?;
        """;

    String DELETE = """
        DELETE FROM financial_record
        WHERE id = ?;
        """;

    String SELECT_BY_ID = """
        SELECT fr.id,
               fr.user_id,
               fr.amount,
               fr.transaction_type,
               fr.transaction_date,
               fr.description,
               fr.category_id,
        FROM financial_record fr
        WHERE fr.id = ?;
        """;

    String SELECT_ALL_CATEGORY = """
        SELECT id,
               name
        FROM category;
        """;

    String SELECT_CATEGORY_ID_BY_NAME = """
        SELECT id,
               name
        FROM category
        WHERE name = ?;
        """;

    String SELECT_CATEGORY_BY_ID = """
        SELECT id,
               name
        FROM category
        WHERE id = ?;
        """;

}

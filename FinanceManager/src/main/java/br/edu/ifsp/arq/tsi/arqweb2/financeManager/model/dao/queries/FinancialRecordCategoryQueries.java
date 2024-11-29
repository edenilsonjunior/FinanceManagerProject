package br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.dao.queries;

public interface FinancialRecordCategoryQueries {

    String CREATE = """
        INSERT INTO category
        (user_id, name)
        VALUES
        (?, ?);
        """;

    String SELECT = """
        SELECT c.id,
               c.user_id,
               c.name
        FROM category c
        WHERE c.id = ?;
        """;

    String SELECT_BY_USER_ID = """
        SELECT c.id,
               c.user_id,
               c.name
        FROM category c
        WHERE c.user_id = ?;
        """;
}

package br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.dao.queries;

public interface FinancialRecordCategoryQueries {

    String CREATE = """
        INSERT INTO category
        (user_id, name)
        VALUES
        (?, ?)
        """;

    String SELECT = """
        SELECT c.id,
               c.user_id,
               c.name
        FROM category c
        WHERE c.id = ?
        """;

    String SELECT_BY_USER_ID = """
        SELECT c.id,
               c.user_id,
               c.name
        FROM category c
        WHERE c.user_id = ?
        """;

    String GET_CATEGORY_EXPENSES_BY_USER_ID = """
        SELECT c.name AS category,
            COALESCE(SUM(fr.amount), 0) AS amount
        FROM category c
        LEFT JOIN financial_record fr
                ON c.id = fr.category_id
            AND fr.user_id = ?
        GROUP BY c.id, c.name
        """;

    String EXISTS_BY_NAME_AND_USER_ID = """
        SELECT CASE
                   WHEN EXISTS (
                       SELECT 1
                       FROM category c
                       WHERE LOWER(c.name) = LOWER(?)
                         AND c.user_id = ?
                   ) THEN 1
                   ELSE 0
               END AS exists_category
        FROM DUAL
        """;
}
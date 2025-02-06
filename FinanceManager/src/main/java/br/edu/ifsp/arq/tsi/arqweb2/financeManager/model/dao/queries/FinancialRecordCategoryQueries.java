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

    String GET_CATEGORY_EXPENSES_FOR_CURRENT_MONTH_BY_USER_ID = """
        SELECT c.name AS category,
               COALESCE(SUM(fr.amount), 0) AS amount
        FROM category c
        LEFT JOIN financial_record fr
                ON c.id = fr.category_id
        LEFT JOIN user u
                ON c.user_id = u.id
        WHERE u.id = ?
              AND MONTH(fr.transaction_date) = MONTH(CURRENT_DATE)
              AND YEAR(fr.transaction_date) = YEAR(CURRENT_DATE)
        GROUP BY c.name;
        """;
}

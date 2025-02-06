package br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.dao.queries;

public interface FinancialRecordQueries {

    String CREATE = """
        INSERT INTO financial_record
        (user_id, category_id, amount, transaction_type, description)
        VALUES
        (?, ?, ?, ?, ?)
        """;

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
        WHERE fr.user_id = ?
        """;

    String UPDATE = """
        UPDATE financial_record
        SET category_id = ?,
            amount = ?,
            description = ?
        WHERE id = ?
        """;

    String DELETE = """
        DELETE FROM financial_record
        WHERE id = ?
        """;

    String SELECT_BY_ID = """
        SELECT fr.id,
               fr.user_id,
               fr.category_id,
               fr.amount,
               fr.transaction_type,
               fr.transaction_date,
               fr.description
        FROM financial_record fr
        WHERE fr.id = ?
        """;


    String SELECT_ALL_CATEGORY = """
        SELECT id,
               name
        FROM category
        """;

    String SELECT_CATEGORY_ID_BY_NAME = """
        SELECT id,
               name
        FROM category
        WHERE name = ?
        """;

    String SELECT_CATEGORY_BY_ID = """
        SELECT id,
               name
        FROM category
        WHERE id = ?
        """;

    String SELECT_OVERVIEW_BY_USER_ID = """
        SELECT
            user_id,
            SUM(CASE WHEN transaction_type = 'INCOME' THEN amount ELSE 0 END) AS total_income,
            SUM(CASE WHEN transaction_type = 'EXPENSE' THEN amount ELSE 0 END) AS total_expense,
            SUM(CASE WHEN transaction_type = 'INCOME' THEN amount ELSE -amount END) AS current_balance
        FROM financial_record
        WHERE user_id = ?
        GROUP BY user_id
        """;

    String SELECT_MONTHLY_BALANCE_BY_USER_ID = """
        SELECT
            SUM(CASE WHEN transaction_type = 'INCOME' THEN amount ELSE 0 END) AS total_income,
            SUM(CASE WHEN transaction_type = 'EXPENSE' THEN amount ELSE 0 END) AS total_expense,
            SUM(CASE WHEN transaction_type = 'INCOME' THEN amount ELSE -amount END) AS current_balance
        FROM financial_record
        WHERE user_id = ?
              AND EXTRACT(MONTH FROM transaction_date) = EXTRACT(MONTH FROM CURRENT_DATE)
              AND EXTRACT(YEAR FROM transaction_date) = EXTRACT(YEAR FROM CURRENT_DATE)
        GROUP BY user_id
        """;

    String SELECT_HISTORY_BY_USER_ID = """
        SELECT
            f.id,
            c.name as category_name,
            f.amount,
            f.transaction_type,
            f.transaction_date,
            f.description
        FROM financial_record f
        LEFT JOIN category c
        ON f.category_id = c.id
        JOIN TB_USERS u
        ON f.user_id = u.id
        WHERE u.id = ?
        ORDER BY f.transaction_date DESC
        """;
}

package br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.dao.queries;

public interface WalletsQueries {

    String CREATE = """
        INSERT INTO wallet (user_id, name, goal_amount, current_balance, description, created_at)
        VALUES (?, ?, ?, ?, ?, NOW())
    """;

    String CREATE_TRANSACTION = """
        INSERT INTO WALLET_TRANSACTION(WALLET_ID, TRANSACTION_TYPE, AMOUNT, TRANSACTION_DATE)
        VALUES (?, ?, ?, NOW())
    """;

    String UPDATE = """
        UPDATE WALLET
        SET NAME = ?,
            GOAL_AMOUNT = ?,
            DESCRIPTION = ?
        WHERE ID = ?
    """;

    String DELETE = """
        DELETE FROM WALLET
        WHERE id = ?
        """;

    String SELECT_BY_USER_ID = """
        SELECT W.id
               ,W.user_id
               ,W.name
               ,W.goal_amount
               ,W.current_balance
               ,W.description
               ,W.created_at
        FROM WALLET W
        WHERE w.user_id = ?
    """;

    String SELECT_BY_ID_AND_USER_ID = """
            SELECT W.id
               ,W.user_id
               ,W.name
               ,W.goal_amount
               ,W.current_balance
               ,W.description
               ,W.created_at
        FROM WALLET W
        WHERE W.id = ? AND w.user_id = ?
    """;

    String SELECT_TRANSACTIONS_BY_WALLET_ID = """
        SELECT id
               ,wallet_id
               ,transaction_type
               ,amount
               ,transaction_date
               ,description
        FROM wallet_transaction
        WHERE wallet_id = ?
    """;

    String SELECT_OVERVIEW_BY_USER_ID = """
        SELECT
            COALESCE(SUM(w.current_balance), 0) AS total_balance,
            COALESCE(COUNT(wt.id), 0) AS transactions_this_month
        FROM wallet w
        LEFT JOIN wallet_transaction wt
            ON w.id = wt.wallet_id
            AND EXTRACT(YEAR FROM wt.transaction_date) = EXTRACT(YEAR FROM SYSDATE)
            AND EXTRACT(MONTH FROM wt.transaction_date) = EXTRACT(MONTH FROM SYSDATE)
        WHERE w.user_id = ?
    """;
}

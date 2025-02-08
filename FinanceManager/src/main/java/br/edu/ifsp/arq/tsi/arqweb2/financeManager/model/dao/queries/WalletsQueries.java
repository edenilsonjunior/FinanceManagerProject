package br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.dao.queries;

public interface WalletsQueries {

    String CREATE = """
        INSERT INTO wallet (id, user_id, name, goal_amount, current_balance, description, created_at)
        VALUES (wallet_seq.NEXTVAL, ?, ?, ?, 0, ?, CURRENT_TIMESTAMP)
    """;

    String CREATE_TRANSACTION = """
        INSERT INTO WALLET_TRANSACTION(WALLET_ID, TRANSACTION_TYPE, AMOUNT, TRANSACTION_DATE)
        VALUES (?, ?, ?, CURRENT_TIMESTAMP)
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

    String DELETE_TRANSACTIONS = """
        DELETE FROM WALLET_TRANSACTION
        WHERE WALLET_ID = ?
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
               ,W.created_at
               ,W.description
        FROM WALLET W
        WHERE W.id = ? AND w.user_id = ?
    """;

    String SELECT_TRANSACTIONS_BY_WALLET_ID = """
        SELECT id
               ,wallet_id
               ,transaction_type
               ,amount
               ,transaction_date
        FROM wallet_transaction
        WHERE wallet_id = ?
    """;

    String SELECT_OVERVIEW_BY_USER_ID = "{call ? := pkg_finance.calculate_wallet_overview(?)}";
}

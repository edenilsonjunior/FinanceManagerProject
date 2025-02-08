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

    String GET_FINANCIAL_OVERVIEW_BY_USER_PROCEDURE = "{call pkg_finance.get_financial_overview_by_user(?, ?, ?, ?)}";

    String GET_FINANCIAL_SUMMARY_AND_HISTORY_PROCEDURE = "{ call pkg_finance.get_financial_summary_and_history(?, ?) }";

    String GET_MONTHLY_BALANCE_BY_USER_ID_PROCEDURE = "{call pkg_finance.get_financial_overview_by_user_monthly(?, ?)}";

}

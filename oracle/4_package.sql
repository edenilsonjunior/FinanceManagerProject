-- Conectado como personal_finance_system

CREATE OR REPLACE PACKAGE pkg_finance AS

    TYPE financial_summary_rec IS RECORD (total_income NUMBER, total_expense NUMBER, current_balance NUMBER);

    FUNCTION calculate_financial_summary (p_user_id IN NUMBER, p_start_date IN DATE DEFAULT NULL, p_end_date IN DATE DEFAULT NULL) RETURN financial_summary_rec;

    FUNCTION calculate_wallet_overview(p_user_id IN NUMBER) RETURN SYS_REFCURSOR;

    PROCEDURE get_financial_overview_by_user (p_user_id IN NUMBER, p_total_income OUT NUMBER, p_total_expense OUT NUMBER, p_current_balance OUT NUMBER);

    PROCEDURE get_financial_overview_by_user_monthly ( p_user_id IN NUMBER, p_result OUT SYS_REFCURSOR);

    PROCEDURE get_financial_summary_and_history (p_user_id IN NUMBER, p_cursor OUT SYS_REFCURSOR);

END pkg_finance;
/

CREATE OR REPLACE PACKAGE BODY pkg_finance AS

    -- Recuperar o resumo financeiro do usuario
    FUNCTION calculate_financial_summary (
        p_user_id IN NUMBER,
        p_start_date IN DATE DEFAULT NULL,
        p_end_date IN DATE DEFAULT NULL
    ) RETURN financial_summary_rec IS
        v_summary financial_summary_rec;
    BEGIN
        SELECT
            COALESCE(SUM(CASE WHEN transaction_type = 'INCOME' THEN amount ELSE 0 END), 0),
            COALESCE(SUM(CASE WHEN transaction_type = 'EXPENSE' THEN amount ELSE 0 END), 0),
            COALESCE(SUM(CASE WHEN transaction_type = 'INCOME' THEN amount ELSE -amount END), 0)
        INTO v_summary
        FROM financial_record
        WHERE user_id = p_user_id
          AND (p_start_date IS NULL OR transaction_date >= p_start_date)
          AND (p_end_date IS NULL OR transaction_date <= p_end_date);

        RETURN v_summary;
    EXCEPTION
        WHEN OTHERS THEN
            RETURN financial_summary_rec(NULL, NULL, NULL);
    END calculate_financial_summary;


    FUNCTION calculate_wallet_overview(p_user_id IN NUMBER)
    RETURN SYS_REFCURSOR
    IS
        total_balance NUMBER;
        transactions_this_month NUMBER;
        cur SYS_REFCURSOR;
    BEGIN
        SELECT COALESCE(SUM(w.current_balance), 0)
        INTO total_balance
        FROM wallet w
        WHERE w.user_id = p_user_id;

        SELECT COALESCE(COUNT(wt.id), 0)
        INTO transactions_this_month
        FROM wallet w
        LEFT JOIN wallet_transaction wt
            ON w.id = wt.wallet_id
            AND EXTRACT(YEAR FROM wt.transaction_date) = EXTRACT(YEAR FROM SYSDATE)
            AND EXTRACT(MONTH FROM wt.transaction_date) = EXTRACT(MONTH FROM SYSDATE)
        WHERE w.user_id = p_user_id;

        OPEN cur FOR
            SELECT total_balance AS total_balance,
                transactions_this_month AS transactions_this_month
            FROM DUAL;

        RETURN cur;
    END calculate_wallet_overview;
    

    -- Recuperar o resumo financeiro do usuario
    PROCEDURE get_financial_overview_by_user (
        p_user_id IN NUMBER, 
        p_total_income OUT NUMBER, 
        p_total_expense OUT NUMBER, 
        p_current_balance OUT NUMBER
    ) IS
        v_summary financial_summary_rec;
    BEGIN
        v_summary := calculate_financial_summary(p_user_id);
        p_total_income := v_summary.total_income;
        p_total_expense := v_summary.total_expense;
        p_current_balance := v_summary.current_balance;
    EXCEPTION
        WHEN OTHERS THEN
            p_total_income := NULL;
            p_total_expense := NULL;
            p_current_balance := NULL;
    END get_financial_overview_by_user;


    -- Recuperar o resumo financeiro mensal do usuario
    PROCEDURE get_financial_overview_by_user_monthly (
        p_user_id IN NUMBER,
        p_result OUT SYS_REFCURSOR
    ) IS
    BEGIN
        OPEN p_result FOR
            SELECT 
                TO_CHAR(transaction_date, 'MM/YYYY') AS month_year,
                SUM(CASE WHEN transaction_type = 'INCOME' THEN amount ELSE 0 END) AS total_income,
                SUM(CASE WHEN transaction_type = 'EXPENSE' THEN amount ELSE 0 END) AS total_expense,
                SUM(CASE WHEN transaction_type = 'INCOME' THEN amount ELSE -amount END) AS current_balance
            FROM financial_record
            WHERE user_id = p_user_id
            GROUP BY TO_CHAR(transaction_date, 'MM/YYYY')
            ORDER BY TO_DATE(TO_CHAR(transaction_date, 'MM/YYYY'), 'MM/YYYY') DESC;
    END get_financial_overview_by_user_monthly;


    -- Recuperar o resumo para a tela de histórico
    PROCEDURE get_financial_summary_and_history (
        p_user_id IN NUMBER,
        p_cursor OUT SYS_REFCURSOR
    ) IS
    BEGIN
        OPEN p_cursor FOR
            SELECT
                f.id,
                c.name AS category_name,
                f.amount,
                f.transaction_type,
                f.transaction_date,
                f.description
            FROM financial_record f
            LEFT JOIN category c ON f.category_id = c.id
            WHERE f.user_id = p_user_id
            ORDER BY f.transaction_date DESC;
    END get_financial_summary_and_history;


END pkg_finance;
/

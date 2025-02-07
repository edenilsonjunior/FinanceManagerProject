CONN personal_finance_system/finance123;

CREATE OR REPLACE PACKAGE pkg_finance AS

    TYPE financial_summary_rec IS RECORD (total_income NUMBER, total_expense NUMBER, current_balance NUMBER);

    FUNCTION calculate_financial_summary (p_user_id IN NUMBER, p_start_date IN DATE DEFAULT NULL, p_end_date IN DATE DEFAULT NULL) RETURN financial_summary_rec;

    PROCEDURE get_financial_overview_by_user (p_user_id IN NUMBER, p_total_income OUT NUMBER, p_total_expense OUT NUMBER, p_current_balance OUT NUMBER);

    PROCEDURE get_financial_overview_by_user_monthly (p_user_id IN NUMBER, p_total_income OUT NUMBER, p_total_expense OUT NUMBER, p_current_balance OUT NUMBER);

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
        p_total_income OUT NUMBER,
        p_total_expense OUT NUMBER,
        p_current_balance OUT NUMBER
    ) IS
        v_summary financial_summary_rec;
        v_start_date DATE := TRUNC(CURRENT_DATE, 'MM');
        v_end_date DATE := LAST_DAY(CURRENT_DATE);
    BEGIN
        v_summary := calculate_financial_summary(p_user_id, v_start_date, v_end_date);
        p_total_income := v_summary.total_income;
        p_total_expense := v_summary.total_expense;
        p_current_balance := v_summary.current_balance;
    EXCEPTION
        WHEN OTHERS THEN
            p_total_income := NULL;
            p_total_expense := NULL;
            p_current_balance := NULL;
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

SET SERVEROUTPUT ON;

BEGIN
    EXECUTE IMMEDIATE 'DROP USER personal_finance_system CASCADE';
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erro ao excluir usuário: ' || SQLERRM);
END;
/

CREATE USER personal_finance_system IDENTIFIED BY finance123
DEFAULT TABLESPACE USERS
TEMPORARY TABLESPACE TEMP
QUOTA UNLIMITED ON USERS;

GRANT CONNECT, RESOURCE TO personal_finance_system;

GRANT CREATE SESSION,
    CREATE TABLE,
    CREATE SEQUENCE,
    CREATE VIEW,
    CREATE PROCEDURE
TO personal_finance_system;

-- Conectar-se ao esquema
CONN personal_finance_system/finance123


-- Tabela history
CREATE TABLE history (
    id NUMBER NOT NULL,
    insert_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    action VARCHAR2(255) NOT NULL,
    action_id NUMBER NOT NULL,
    modified_table VARCHAR2(30) NOT NULL,
    CONSTRAINT pk_history PRIMARY KEY (id)
);

-- Criação das tabelas
-- Tabela USER
-- Tabela tb_users
CREATE TABLE tb_users (
    id NUMBER NOT NULL,
    full_name VARCHAR2(100) NOT NULL,
    email VARCHAR2(100) NOT NULL UNIQUE,
    password VARCHAR2(255) NOT NULL,
    birth_date DATE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,

    CONSTRAINT pk_user PRIMARY KEY (id)
);

-- Tabela category
CREATE TABLE category (
    id NUMBER NOT NULL,
    user_id NUMBER NOT NULL,
    name VARCHAR2(100) NOT NULL,

    CONSTRAINT pk_category PRIMARY KEY (id),
    CONSTRAINT fk_category_user FOREIGN KEY (user_id) REFERENCES tb_users(id)
);

-- Tabela financial_record
CREATE TABLE financial_record (
    id NUMBER NOT NULL,
    user_id NUMBER NOT NULL,
    category_id NUMBER,
    amount NUMBER(10, 2) NOT NULL,
    transaction_type VARCHAR2(100) NOT NULL,
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    description CLOB,

    CONSTRAINT pk_financial_record PRIMARY KEY (id),
    CONSTRAINT financial_record_user FOREIGN KEY (user_id) REFERENCES tb_users(id),
    CONSTRAINT financial_record_category FOREIGN KEY (category_id) REFERENCES category(id)
);

-- Tabela wallet
CREATE TABLE wallet (
    id NUMBER NOT NULL,
    user_id NUMBER,
    name VARCHAR2(100) NOT NULL,
    goal_amount NUMBER(10, 2) NOT NULL,
    current_balance NUMBER(10, 2) DEFAULT 0.00,
    description CLOB,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT pk_wallet PRIMARY KEY (id),
    CONSTRAINT fk_wallet_user FOREIGN KEY (user_id) REFERENCES tb_users(id)
);

-- Tabela wallet_transaction
CREATE TABLE wallet_transaction (
    id NUMBER NOT NULL,
    wallet_id NUMBER,
    transaction_type VARCHAR2(100) NOT NULL,
    amount NUMBER(10, 2) NOT NULL,
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    description CLOB,

    CONSTRAINT pk_wallet_transaction PRIMARY KEY (id),
    CONSTRAINT fk_wallet_transaction_wallet FOREIGN KEY (wallet_id) REFERENCES wallet(id)
);

-- Tabela alert
CREATE TABLE alert (
    id NUMBER NOT NULL,
    alert_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    message CLOB NOT NULL,
    notified CHAR(1) DEFAULT 'N',
    user_id NUMBER,

    CONSTRAINT pk_alert PRIMARY KEY (id),
    CONSTRAINT fk_alert_user FOREIGN KEY (user_id) REFERENCES tb_users(id)
);

-- Criação da SEQUENCES e DAS TRIGGERS para insert na tabela history
-- Sequência para hisotry
CREATE SEQUENCE history_seq START WITH 1 INCREMENT BY 1;

-- Trigger para auto incremento no history
CREATE OR REPLACE TRIGGER history_bi
BEFORE INSERT ON history
FOR EACH ROW
BEGIN
    IF :NEW.id IS NULL THEN
        SELECT history_seq.NEXTVAL
        INTO :NEW.id
        FROM dual;
    END IF;
END;
/

-- Sequência para tb_users
CREATE SEQUENCE tb_users_seq START WITH 1 INCREMENT BY 1;

-- Trigger para auto incremento no tb_users
CREATE OR REPLACE TRIGGER tb_users_bi
BEFORE INSERT ON tb_users
FOR EACH ROW
BEGIN
    IF :NEW.id IS NULL THEN
        SELECT tb_users_seq.NEXTVAL
        INTO :NEW.id
        FROM dual;
    END IF;
    
    INSERT INTO history(action, action_id, modified_table)
    VALUES
        ('insert', :NEW.id, 'tb_users');
END;
/

-- Trigger para UPDATE na tabela tb_users
CREATE OR REPLACE TRIGGER tb_users_bu
AFTER UPDATE ON tb_users
FOR EACH ROW
BEGIN
    INSERT INTO history(action, action_id, modified_table)
    VALUES
        ('update', :OLD.id, 'tb_users');
END;
/

-- Trigger para DELETE na tabela tb_users
CREATE OR REPLACE TRIGGER tb_users_bd
AFTER DELETE ON tb_users
FOR EACH ROW
BEGIN
    INSERT INTO history(action, action_id, modified_table)
    VALUES
        ('delete', :OLD.id, 'tb_users');
END;
/

-- Sequência para category
CREATE SEQUENCE category_seq START WITH 1 INCREMENT BY 1;

-- Trigger para auto incremento no category
CREATE OR REPLACE TRIGGER category_bi
BEFORE INSERT ON category
FOR EACH ROW
BEGIN
    IF :NEW.id IS NULL THEN
        SELECT category_seq.NEXTVAL
        INTO :NEW.id
        FROM dual;
    END IF;
    
    INSERT INTO history(action, action_id, modified_table)
    VALUES
        ('insert', :NEW.id, 'category');
END;
/

-- Trigger para UPDATE na tabela category
CREATE OR REPLACE TRIGGER category_bu
AFTER UPDATE ON category
FOR EACH ROW
BEGIN
    INSERT INTO history(action, action_id, modified_table)
    VALUES
        ('update', :OLD.id, 'category');
END;
/

-- Trigger para DELETE na tabela category
CREATE OR REPLACE TRIGGER category_bd
AFTER DELETE ON category
FOR EACH ROW
BEGIN
    INSERT INTO history(action, action_id, modified_table)
    VALUES
        ('delete', :OLD.id, 'category');
END;
/

-- Sequência para financial_record
CREATE SEQUENCE financial_record_seq START WITH 1 INCREMENT BY 1;

-- Trigger para auto incremento no financial_record
CREATE OR REPLACE TRIGGER financial_record_bi
BEFORE INSERT ON financial_record
FOR EACH ROW
BEGIN
    IF :NEW.id IS NULL THEN
        SELECT financial_record_seq.NEXTVAL
        INTO :NEW.id
        FROM dual;
    END IF;
    
    INSERT INTO history(action, action_id, modified_table)
    VALUES
        ('insert', :NEW.id, 'financial_record');
END;
/

-- Trigger para UPDATE na tabela financial_record
CREATE OR REPLACE TRIGGER financial_record_bu
AFTER UPDATE ON financial_record
FOR EACH ROW
BEGIN
    INSERT INTO history(action, action_id, modified_table)
    VALUES
        ('update', :OLD.id, 'financial_record');
END;
/

-- Trigger para DELETE na tabela financial_record
CREATE OR REPLACE TRIGGER financial_record_bd
AFTER DELETE ON financial_record
FOR EACH ROW
BEGIN
    INSERT INTO history(action, action_id, modified_table)
    VALUES
        ('delete', :OLD.id, 'financial_record');
END;
/

-- Sequência para wallet
CREATE SEQUENCE wallet_seq START WITH 1 INCREMENT BY 1;

-- Trigger para auto incremento no wallet
CREATE OR REPLACE TRIGGER wallet_bi
BEFORE INSERT ON wallet
FOR EACH ROW
BEGIN
    IF :NEW.id IS NULL THEN
        SELECT wallet_seq.NEXTVAL
        INTO :NEW.id
        FROM dual;
    END IF;
    
    INSERT INTO history(action, action_id, modified_table)
    VALUES
        ('insert', :NEW.id, 'wallet');
END;
/

-- Trigger para UPDATE na tabela wallet
CREATE OR REPLACE TRIGGER wallet_bu
AFTER UPDATE ON wallet
FOR EACH ROW
BEGIN
    INSERT INTO history(action, action_id, modified_table)
    VALUES
        ('update', :OLD.id, 'wallet');
END;
/

-- Trigger para DELETE na tabela wallet
CREATE OR REPLACE TRIGGER wallet_bd
AFTER DELETE ON wallet
FOR EACH ROW
BEGIN
    INSERT INTO history(action, action_id, modified_table)
    VALUES
        ('delete', :OLD.id, 'wallet');
END;
/

-- Sequência para wallet_transaction
CREATE SEQUENCE wallet_transaction_seq START WITH 1 INCREMENT BY 1;

-- Trigger para auto incremento no wallet_transaction
CREATE OR REPLACE TRIGGER wallet_transaction_bi
BEFORE INSERT ON wallet_transaction
FOR EACH ROW
BEGIN
    IF :NEW.id IS NULL THEN
        SELECT wallet_transaction_seq.NEXTVAL
        INTO :NEW.id
        FROM dual;
    END IF;
    
    INSERT INTO history(action, action_id, modified_table)
    VALUES
        ('insert', :NEW.id, 'wallet_transaction');
END;
/

-- Trigger para UPDATE na tabela wallet_transaction
CREATE OR REPLACE TRIGGER wallet_transaction_bu
AFTER UPDATE ON wallet_transaction
FOR EACH ROW
BEGIN
    INSERT INTO history(action, action_id, modified_table)
    VALUES
        ('update', :OLD.id, 'wallet_transaction');
END;
/

-- Trigger para DELETE na tabela wallet_transaction
CREATE OR REPLACE TRIGGER wallet_transaction_bd
AFTER DELETE ON wallet_transaction
FOR EACH ROW
BEGIN
    INSERT INTO history(action, action_id, modified_table)
    VALUES
        ('delete', :OLD.id, 'wallet_transaction');
END;
/


-- Sequência para alert
CREATE SEQUENCE alert_seq START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER alert_bi
BEFORE INSERT ON alert
FOR EACH ROW
BEGIN
    -- Garantir que o id seja gerado pela sequência
    IF :NEW.id IS NULL THEN
        SELECT alert_seq.NEXTVAL
        INTO :NEW.id
        FROM dual;
    END IF;

    -- Inserir um histórico na tabela history com os dados inseridos
    INSERT INTO history(action, action_id, modified_table)
    VALUES
        ('insert', :NEW.id, 'alert');
END;
/

-- Trigger para UPDATE na tabela alert
CREATE OR REPLACE TRIGGER alert_bu
AFTER UPDATE ON alert
FOR EACH ROW
BEGIN
    INSERT INTO history(action, action_id, modified_table)
    VALUES
        ('update', :OLD.id, 'alert');
END;
/

-- Trigger para DELETE na tabela alert
CREATE OR REPLACE TRIGGER alert_bd
AFTER DELETE ON alert
FOR EACH ROW
BEGIN
    INSERT INTO history(action, action_id, modified_table)
    VALUES
        ('delete', :OLD.id, 'alert');
END;
/

-- Criação de TYPES
CREATE OR REPLACE TYPE financial_summary_rec AS OBJECT (
  total_income NUMBER,
  total_expense NUMBER,
  current_balance NUMBER
);
/

-- Criação da FUNCTIONS
CREATE OR REPLACE FUNCTION CALCULATE_FINANCIAL_SUMMARY (
    p_user_id IN NUMBER,
    p_start_date IN DATE DEFAULT NULL,  -- Data de início opcional para filtro
    p_end_date IN DATE DEFAULT NULL     -- Data de fim opcional para filtro
) RETURN financial_summary_rec IS
    v_summary financial_summary_rec;
BEGIN
    SELECT
        SUM(CASE WHEN transaction_type = 'INCOME' THEN amount ELSE 0 END),
        SUM(CASE WHEN transaction_type = 'EXPENSE' THEN amount ELSE 0 END),
        SUM(CASE WHEN transaction_type = 'INCOME' THEN amount ELSE -amount END)
    INTO
        v_summary.total_income, v_summary.total_expense, v_summary.current_balance
    FROM financial_record
    WHERE user_id = p_user_id
      AND (transaction_date BETWEEN p_start_date AND p_end_date OR (p_start_date IS NULL AND p_end_date IS NULL))  -- Filtro de data
    GROUP BY user_id;

    RETURN v_summary;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RETURN financial_summary_rec(0, 0, 0);  -- Retorna 0 se não houver dados
    WHEN OTHERS THEN
        RETURN financial_summary_rec(NULL, NULL, NULL); -- Retorna NULL em caso de erro
        -- Considerar logar o erro para depuração:
        -- DBMS_OUTPUT.PUT_LINE('Erro em CALCULATE_FINANCIAL_SUMMARY: ' || SQLERRM);
        RAISE; -- Re-lança a exceção após tratamento
END CALCULATE_FINANCIAL_SUMMARY;
/


-- Criação das PROCEDURES
CREATE OR REPLACE PROCEDURE get_financial_overview_by_user (
    p_user_id IN NUMBER, 
    p_total_income OUT NUMBER, 
    p_total_expense OUT NUMBER, 
    p_current_balance OUT NUMBER
) IS
    v_summary financial_summary_rec;
    CURSOR cur_financial_summary IS
        SELECT
            SUM(CASE WHEN transaction_type = 'INCOME' THEN amount ELSE 0 END) AS total_income,
            SUM(CASE WHEN transaction_type = 'EXPENSE' THEN amount ELSE 0 END) AS total_expense,
            SUM(CASE WHEN transaction_type = 'INCOME' THEN amount ELSE -amount END) AS current_balance
        FROM financial_record
        WHERE user_id = p_user_id
        GROUP BY user_id;
BEGIN
    OPEN cur_financial_summary;
    FETCH cur_financial_summary INTO p_total_income, p_total_expense, p_current_balance;
    CLOSE cur_financial_summary;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        p_total_income := 0;
        p_total_expense := 0;
        p_current_balance := 0;
    WHEN OTHERS THEN
        p_total_income := NULL;
        p_total_expense := NULL;
        p_current_balance := NULL;
        RAISE;
END get_financial_overview_by_user;
/

CREATE OR REPLACE PROCEDURE get_financial_overview_by_user_monthly(
    p_user_id IN NUMBER,
    p_total_income OUT NUMBER,
    p_total_expense OUT NUMBER,
    p_current_balance OUT NUMBER
) IS
    v_summary financial_summary_rec;
    v_start_date DATE;
    v_end_date DATE;
    CURSOR cur_financial_summary IS
        SELECT
            SUM(CASE WHEN transaction_type = 'INCOME' THEN amount ELSE 0 END) AS total_income,
            SUM(CASE WHEN transaction_type = 'EXPENSE' THEN amount ELSE 0 END) AS total_expense,
            SUM(CASE WHEN transaction_type = 'INCOME' THEN amount ELSE -amount END) AS current_balance
        FROM financial_record
        WHERE user_id = p_user_id
          AND EXTRACT(MONTH FROM transaction_date) = EXTRACT(MONTH FROM CURRENT_DATE)
          AND EXTRACT(YEAR FROM transaction_date) = EXTRACT(YEAR FROM CURRENT_DATE)
        GROUP BY user_id;
BEGIN
    v_start_date := TRUNC(ADD_MONTHS(SYSDATE, 0), 'MM');  -- Primeiro dia do mês atual
    v_end_date := LAST_DAY(SYSDATE);                      -- Último dia do mês atual

    OPEN cur_financial_summary;
    FETCH cur_financial_summary INTO p_total_income, p_total_expense, p_current_balance;
    CLOSE cur_financial_summary;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        p_total_income := 0;
        p_total_expense := 0;
        p_current_balance := 0;
    WHEN OTHERS THEN
        p_total_income := NULL;
        p_total_expense := NULL;
        p_current_balance := NULL;
        RAISE;
END get_financial_overview_by_user_monthly;
/

CREATE OR REPLACE PROCEDURE GET_FINANCIAL_SUMMARY_AND_HISTORY(
    p_user_id IN NUMBER,
    p_cursor OUT SYS_REFCURSOR
)
IS
BEGIN
    -- Retorna o histórico de transações do usuário em um cursor
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
        JOIN TB_USERS u ON f.user_id = u.id
        WHERE u.id = p_user_id
        ORDER BY f.transaction_date DESC;
END GET_FINANCIAL_SUMMARY_AND_HISTORY;
/


-- Inserção de dados
INSERT INTO tb_users (full_name, email, password, birth_date) 
VALUES ('John Doe', 'john.doe@example.com', '81DC9BDB52D04DC20036DBD8313ED055', TO_DATE('1985-07-12', 'YYYY-MM-DD'));

INSERT INTO tb_users (full_name, email, password, birth_date) 
VALUES ('Jane Smith', 'jane.smith@example.com', 'hashed_password_2', TO_DATE('1990-03-25', 'YYYY-MM-DD'));

-- Inserção de categorias
INSERT INTO category (user_id, name) VALUES (1, 'Gastos adversos');
INSERT INTO category (user_id, name) VALUES (1, 'Divida');
INSERT INTO category (user_id, name) VALUES (1, 'Alimentação');
INSERT INTO category (user_id, name) VALUES (1, 'Transporte');

INSERT INTO category (user_id, name) VALUES (2, 'Gastos adversos');
INSERT INTO category (user_id, name) VALUES (2, 'Divida');
INSERT INTO category (user_id, name) VALUES (2, 'Alimentação');
INSERT INTO category (user_id, name) VALUES (2, 'Transporte');

-- Inserção de registros financeiros para John Doe (id 1)
-- Agosto
INSERT INTO financial_record (user_id, category_id, amount, transaction_type, description, transaction_date) 
VALUES (1, NULL, 1500.00, 'INCOME', 'Salary payment for August', TO_TIMESTAMP('2024-08-01', 'YYYY-MM-DD'));
INSERT INTO financial_record (user_id, category_id, amount, transaction_type, description, transaction_date) 
VALUES (1, 3, 350.00, 'EXPENSE', 'Grocery shopping', TO_TIMESTAMP('2024-08-15', 'YYYY-MM-DD'));
INSERT INTO financial_record (user_id, category_id, amount, transaction_type, description, transaction_date) 
VALUES (1, 1, 200.00, 'EXPENSE', 'Emergency medical expenses', TO_TIMESTAMP('2024-08-31', 'YYYY-MM-DD'));

-- Setembro
INSERT INTO financial_record (user_id, category_id, amount, transaction_type, description, transaction_date) 
VALUES (1, NULL, 1700.00, 'INCOME', 'Salary payment for September', TO_TIMESTAMP('2024-09-01', 'YYYY-MM-DD'));
INSERT INTO financial_record (user_id, category_id, amount, transaction_type, description, transaction_date) 
VALUES (1, 2, 400.00, 'EXPENSE', 'Car maintenance', TO_TIMESTAMP('2024-09-15', 'YYYY-MM-DD'));
INSERT INTO financial_record (user_id, category_id, amount, transaction_type, description, transaction_date) 
VALUES (1, 4, 150.00, 'EXPENSE', 'Transport costs', TO_TIMESTAMP('2024-09-30', 'YYYY-MM-DD'));

-- Outubro
INSERT INTO financial_record (user_id, category_id, amount, transaction_type, description, transaction_date) 
VALUES (1, NULL, 1800.00, 'INCOME', 'Salary payment for October', TO_TIMESTAMP('2024-10-01', 'YYYY-MM-DD'));
INSERT INTO financial_record (user_id, category_id, amount, transaction_type, description, transaction_date) 
VALUES (1, 3, 300.00, 'EXPENSE', 'Food delivery service', TO_TIMESTAMP('2024-10-15', 'YYYY-MM-DD'));
INSERT INTO financial_record (user_id, category_id, amount, transaction_type, description, transaction_date) 
VALUES (1, 1, 450.00, 'EXPENSE', 'Unexpected health issue', TO_TIMESTAMP('2024-10-31', 'YYYY-MM-DD'));

-- Novembro
INSERT INTO financial_record (user_id, category_id, amount, transaction_type, description, transaction_date) 
VALUES (1, NULL, 2000.00, 'INCOME', 'Salary payment for November', TO_TIMESTAMP('2024-11-01', 'YYYY-MM-DD'));
INSERT INTO financial_record (user_id, category_id, amount, transaction_type, description, transaction_date) 
VALUES (1, 2, 500.00, 'EXPENSE', 'Savings deposit', TO_TIMESTAMP('2024-11-15', 'YYYY-MM-DD'));
INSERT INTO financial_record (user_id, category_id, amount, transaction_type, description, transaction_date) 
VALUES (1, 4, 250.00, 'EXPENSE', 'Public transportation fees', TO_TIMESTAMP('2024-11-30', 'YYYY-MM-DD'));

-- Inserção de registros financeiros para Jane Smith (id 2)
-- Agosto
INSERT INTO financial_record (user_id, category_id, amount, transaction_type, description, transaction_date) 
VALUES (2, NULL, 1600.00, 'INCOME', 'Investment dividend for August', TO_TIMESTAMP('2024-08-01', 'YYYY-MM-DD'));
INSERT INTO financial_record (user_id, category_id, amount, transaction_type, description, transaction_date) 
VALUES (2, 3, 400.00, 'EXPENSE', 'Shopping for the month', TO_TIMESTAMP('2024-08-15', 'YYYY-MM-DD'));
INSERT INTO financial_record (user_id, category_id, amount, transaction_type, description, transaction_date) 
VALUES (2, 4, 100.00, 'EXPENSE', 'Bus tickets', TO_TIMESTAMP('2024-08-31', 'YYYY-MM-DD'));

-- Setembro
INSERT INTO financial_record (user_id, category_id, amount, transaction_type, description, transaction_date) 
VALUES (2, NULL, 1700.00, 'INCOME', 'Investment dividend for September', TO_TIMESTAMP('2024-09-01', 'YYYY-MM-DD'));
INSERT INTO financial_record (user_id, category_id, amount, transaction_type, description, transaction_date) 
VALUES (2, 3, 350.00, 'EXPENSE', 'Buying supplies for home', TO_TIMESTAMP('2024-09-15', 'YYYY-MM-DD'));
INSERT INTO financial_record (user_id, category_id, amount, transaction_type, description, transaction_date) 
VALUES (2, 2, 150.00, 'EXPENSE', 'Debt repayment', TO_TIMESTAMP('2024-09-30', 'YYYY-MM-DD'));

-- Outubro
INSERT INTO financial_record (user_id, category_id, amount, transaction_type, description, transaction_date) 
VALUES (2, NULL, 1800.00, 'INCOME', 'Investment dividend for October', TO_TIMESTAMP('2024-10-01', 'YYYY-MM-DD'));
INSERT INTO financial_record (user_id, category_id, amount, transaction_type, description, transaction_date) 
VALUES (2, 1, 450.00, 'EXPENSE', 'Emergency home repairs', TO_TIMESTAMP('2024-10-15', 'YYYY-MM-DD'));
INSERT INTO financial_record (user_id, category_id, amount, transaction_type, description, transaction_date) 
VALUES (2, 4, 180.00, 'EXPENSE', 'Taxi fare', TO_TIMESTAMP('2024-10-31', 'YYYY-MM-DD'));

-- Novembro
INSERT INTO financial_record (user_id, category_id, amount, transaction_type, description, transaction_date) 
VALUES (2, NULL, 1900.00, 'INCOME', 'Investment dividend for November', TO_TIMESTAMP('2024-11-15', 'YYYY-MM-DD'));

-- Janeiro 2025
INSERT INTO financial_record (user_id, category_id, amount, transaction_type, description, transaction_date) 
VALUES (2, NULL, 1800.00, 'INCOME', 'Investment dividend for October', TO_TIMESTAMP('2025-01-10', 'YYYY-MM-DD'));
INSERT INTO financial_record (user_id, category_id, amount, transaction_type, description, transaction_date) 
VALUES (2, 1, 450.00, 'EXPENSE', 'Emergency home repairs', TO_TIMESTAMP('2025-01-15', 'YYYY-MM-DD'));
INSERT INTO financial_record (user_id, category_id, amount, transaction_type, description, transaction_date) 
VALUES (2, 4, 180.00, 'EXPENSE', 'Taxi fare', TO_TIMESTAMP('2025-01-31', 'YYYY-MM-DD'));

-- Fevereiro 2025
INSERT INTO financial_record (user_id, category_id, amount, transaction_type, description, transaction_date) 
VALUES (2, NULL, 1700.00, 'INCOME', 'Investment dividend for September', TO_TIMESTAMP('2025-02-10', 'YYYY-MM-DD'));
INSERT INTO financial_record (user_id, category_id, amount, transaction_type, description, transaction_date) 
VALUES (2, 3, 350.00, 'EXPENSE', 'Buying supplies for home', TO_TIMESTAMP('2025-02-10', 'YYYY-MM-DD'));
INSERT INTO financial_record (user_id, category_id, amount, transaction_type, description, transaction_date) 
VALUES (2, 2, 150.00, 'EXPENSE', 'Debt repayment', TO_TIMESTAMP('2025-02-10', 'YYYY-MM-DD'));

COMMIT;

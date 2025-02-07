-- Conectado como personal_finance_system

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



INSERT INTO wallet (id, user_id, name, goal_amount, current_balance, description, created_at)
VALUES (1, 1, 'Carteira Principal', 10000.00, 0.00, 'Carteira principal do usuário', CURRENT_TIMESTAMP);

INSERT INTO wallet (id, user_id, name, goal_amount, current_balance, description, created_at)
VALUES (2, 1, 'Carteira de Investimentos', 20000.00, 0.00, 'Carteira destinada a investimentos', CURRENT_TIMESTAMP);

INSERT INTO wallet_transaction (wallet_id, transaction_type, amount, transaction_date)
VALUES (1, 'INCOME', 1500.00, TO_TIMESTAMP('2025-02-01 10:00:00', 'YYYY-MM-DD HH24:MI:SS'));

INSERT INTO wallet_transaction (wallet_id, transaction_type, amount, transaction_date)
VALUES (1, 'INCOME', 2000.00, TO_TIMESTAMP('2025-02-05 15:30:00', 'YYYY-MM-DD HH24:MI:SS'));

INSERT INTO wallet_transaction (wallet_id, transaction_type, amount, transaction_date)
VALUES (1, 'EXPENSE', 500.00, TO_TIMESTAMP('2025-02-04 18:00:00', 'YYYY-MM-DD HH24:MI:SS'));

-- Inserindo transações para a Carteira de Investimentos (wallet_id = 2)
INSERT INTO wallet_transaction (wallet_id, transaction_type, amount, transaction_date)
VALUES (2, 'INCOME', 300.00, TO_TIMESTAMP('2025-02-03 08:45:00', 'YYYY-MM-DD HH24:MI:SS'));

INSERT INTO wallet_transaction (wallet_id, transaction_type, amount, transaction_date)
VALUES (2, 'EXPENSE', 100.00, TO_TIMESTAMP('2025-02-06 12:15:00', 'YYYY-MM-DD HH24:MI:SS'));

INSERT INTO wallet_transaction (wallet_id, transaction_type, amount, transaction_date)
VALUES (2, 'EXPENSE', 50.00, TO_TIMESTAMP('2025-02-02 09:00:00', 'YYYY-MM-DD HH24:MI:SS'));

COMMIT;

DROP DATABASE IF EXISTS personal_finance_system;
CREATE DATABASE personal_finance_system;
USE personal_finance_system;

CREATE TABLE user (
    id INT NOT NULL AUTO_INCREMENT,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    birth_date DATE NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT pk_user PRIMARY KEY (id)
);

CREATE TABLE category (
    id INT NOT NULL AUTO_INCREMENT,
    user_id INT NOT NULL,
    name VARCHAR(100) NOT NULL,

    CONSTRAINT pk_category PRIMARY KEY (id),
    CONSTRAINT fk_category_user FOREIGN KEY (user_id) REFERENCES user(id)
);

CREATE TABLE financial_record (
    id INT NOT NULL AUTO_INCREMENT,
    user_id INT NOT NULL,
    category_id INT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    transaction_type VARCHAR(100) NOT NULL,
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    description TEXT,

    CONSTRAINT pk_financial_record PRIMARY KEY (id),
    CONSTRAINT financial_record_user FOREIGN KEY (user_id) REFERENCES user(id),
    CONSTRAINT financial_record_category FOREIGN KEY (category_id) REFERENCES category(id)
);

CREATE TABLE wallet (
    id INT AUTO_INCREMENT,
    user_id INT,
    name VARCHAR(100) NOT NULL,
    goal_amount DECIMAL(10, 2) NOT NULL,
    current_balance DECIMAL(10, 2) DEFAULT 0.00,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    CONSTRAINT pk_wallet PRIMARY KEY (id),
    CONSTRAINT fk_wallet_user FOREIGN KEY (user_id) REFERENCES user(id)
);

CREATE TABLE wallet_transaction (
    id INT AUTO_INCREMENT,
    wallet_id INT,
    transaction_type VARCHAR(100) NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    description TEXT,
    CONSTRAINT pk_wallet_transaction PRIMARY KEY (id),
    CONSTRAINT fk_wallet_transaction_wallet FOREIGN KEY (wallet_id) REFERENCES wallet(id)
);

CREATE TABLE alert (
    id INT AUTO_INCREMENT,
    alert_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    message TEXT NOT NULL,
    notified BOOLEAN DEFAULT FALSE,
    user_id INT,
    
	CONSTRAINT pk_alert PRIMARY KEY (id),
    CONSTRAINT fk_alert_user FOREIGN KEY (user_id) REFERENCES user(id)
);

-- USAR 1234 no sistema para se conectar com o user 1
INSERT INTO user (full_name, email, password, birth_date) 
VALUES 
    ('John Doe', 'john.doe@example.com', '81DC9BDB52D04DC20036DBD8313ED055', '1985-07-12'),
    ('Jane Smith', 'jane.smith@example.com', 'hashed_password_2', '1990-03-25');

INSERT INTO category (user_id, name) 
VALUES 
    (1, 'Gastos adversos'),
    (1, 'Divida'),
    (1, 'Alimentação'),
    (1, 'Transporte');

-- Para o usuário John Doe (id 1)

-- Agosto
INSERT INTO financial_record (user_id, category_id, amount, transaction_type, description, transaction_date) 
VALUES 
    (1, NULL, 1500.00, 'INCOME', 'Salary payment for August', '2024-08-01'),
    (1, 3, 350.00, 'EXPENSE', 'Grocery shopping', '2024-08-15'),
    (1, 1, 200.00, 'EXPENSE', 'Emergency medical expenses', '2024-08-31');

-- Setembro
INSERT INTO financial_record (user_id, category_id, amount, transaction_type, description, transaction_date) 
VALUES 
    (1, NULL, 1700.00, 'INCOME', 'Salary payment for September', '2024-09-01'),
    (1, 2, 400.00, 'EXPENSE', 'Car maintenance', '2024-09-15'),
    (1, 4, 150.00, 'EXPENSE', 'Transport costs', '2024-09-30');

-- Outubro
INSERT INTO financial_record (user_id, category_id, amount, transaction_type, description, transaction_date) 
VALUES 
    (1, NULL, 1800.00, 'INCOME', 'Salary payment for October', '2024-10-01'),
    (1, 3, 300.00, 'EXPENSE', 'Food delivery service', '2024-10-15'),
    (1, 1, 450.00, 'EXPENSE', 'Unexpected health issue', '2024-10-31');

-- Novembro
INSERT INTO financial_record (user_id, category_id, amount, transaction_type, description, transaction_date) 
VALUES 
    (1, NULL, 2000.00, 'INCOME', 'Salary payment for November', '2024-11-01'),
    (1, 2, 500.00, 'EXPENSE', 'Savings deposit', '2024-11-15'),
    (1, 4, 250.00, 'EXPENSE', 'Public transportation fees', '2024-11-30');

-- Para o usuário Jane Smith (id 2)

-- Agosto
INSERT INTO financial_record (user_id, category_id, amount, transaction_type, description, transaction_date) 
VALUES 
    (2, NULL, 1600.00, 'INCOME', 'Investment dividend for August', '2024-08-01'),
    (2, 3, 400.00, 'EXPENSE', 'Shopping for the month', '2024-08-15'),
    (2, 4, 100.00, 'EXPENSE', 'Bus tickets', '2024-08-31');

-- Setembro
INSERT INTO financial_record (user_id, category_id, amount, transaction_type, description, transaction_date) 
VALUES 
    (2, NULL, 1700.00, 'INCOME', 'Investment dividend for September', '2024-09-01'),
    (2, 3, 350.00, 'EXPENSE', 'Buying supplies for home', '2024-09-15'),
    (2, 2, 150.00, 'EXPENSE', 'Debt repayment', '2024-09-30');

-- Outubro
INSERT INTO financial_record (user_id, category_id, amount, transaction_type, description, transaction_date) 
VALUES 
    (2, NULL, 1800.00, 'INCOME', 'Investment dividend for October', '2024-10-01'),
    (2, 1, 450.00, 'EXPENSE', 'Emergency home repairs', '2024-10-15'),
    (2, 4, 180.00, 'EXPENSE', 'Taxi fare', '2024-10-31');

-- Novembro
INSERT INTO financial_record (user_id, category_id, amount, transaction_type, description, transaction_date) 
VALUES 
    (2, NULL, 1900.00, 'INCOME', 'Investment dividend for November', '2024-11-01'),
    (2, 3, 500.00, 'EXPENSE', 'Supermarket shopping', '2024-11-15'),
    (2, 2, 200.00, 'EXPENSE', 'Debt repayment', '2024-11-30');


INSERT INTO wallet (user_id, name, goal_amount, current_balance, description)
VALUES 
    (1, 'Emergency Fund', 5000.00, 1500.00, 'Emergency fund for unexpected expenses'),
    (2, 'Travel Fund', 2000.00, 800.00, 'Savings for an upcoming vacation');

INSERT INTO wallet_transaction (wallet_id, transaction_type, amount, description)
VALUES 
    (1, 'Deposit', 1000.00, 'Deposited bonus from work'),
    (2, 'Withdrawal', 200.00, 'Paid for flight ticket');

INSERT INTO alert (user_id, message, notified)
VALUES 
    (1, 'Reminder: Check your savings balance', FALSE),
    (2, 'Reminder: Upcoming travel expenses for your vacation', FALSE);

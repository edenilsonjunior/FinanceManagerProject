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
    category_id INT NOT NULL,
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

INSERT INTO user (full_name, email, password, birth_date) 
VALUES 
    ('John Doe', 'john.doe@example.com', 'hashed_password_1', '1985-07-12'),
    ('Jane Smith', 'jane.smith@example.com', 'hashed_password_2', '1990-03-25');

INSERT INTO category (user_id, name) 
VALUES 
    (1, 'Salary'),
    (1, 'Savings'),
    (2, 'Investment'),
    (2, 'Expenses');

INSERT INTO financial_record (user_id, category_id, amount, transaction_type, description) 
VALUES 
    (1, 1, 3000.00, 'Credit', 'Salary payment for July'),
    (1, 2, 500.00, 'Debit', 'Transferred to savings'),
    (2, 3, 1500.00, 'Credit', 'Investment dividend'),
    (2, 4, 200.00, 'Debit', 'Grocery shopping');

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


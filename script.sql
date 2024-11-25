CREATE DATABASE personal_finance_system;

CREATE TABLE user (
    id INT NOT NULL AUTO_INCREMENT,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    birth_date DATE
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT pk_user PRIMARY KEY (id)
);

CREATE TABLE category (
    id INT NOT NULL AUTO_INCREMENT,
    user_id INT,
    name VARCHAR(100) NOT NULL,

    CONSTRAINT pk_category PRIMARY KEY (id),
    CONSTRAINT fk_category_user FOREIGN KEY (user_id) REFERENCES user(id)
);

CREATE TABLE financial_record (
    id INT NOT NULL AUTO_INCREMENT,
    user_id INT NOT NULL,
    category_id INT NOT NULL,
    balance DECIMAL(10, 2) NOT NULL,
    transaction_type VARCHAR(100) NOT NULL,
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    description TEXT,

    CONSTRAINT pk_financial_record PRIMARY KEY (id),
    CONSTRAINT financial_record_user FOREIGN KEY (user_id) REFERENCES user(id),
    CONSTRAINT financial_record_category FOREIGN KEY (category_id) REFERENCES category(id)
);

CREATE TABLE wallet (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    name VARCHAR(100) NOT NULL,
    goal_amount DECIMAL(10, 2) NOT NULL,
    current_balance DECIMAL(10, 2) DEFAULT 0.00,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user(id)
);

CREATE TABLE wallet_transaction (
    id INT AUTO_INCREMENT PRIMARY KEY,
    wallet_id INT,
    transaction_type VARCHAR(100) NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    description TEXT,
    FOREIGN KEY (wallet_id) REFERENCES wallet(id)
);

CREATE TABLE alert (
    id INT AUTO_INCREMENT PRIMARY KEY,
    alert_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    message TEXT NOT NULL,
    notified BOOLEAN DEFAULT FALSE,
    user_id INT,

    CONSTRAINT fk_alert_user FOREIGN KEY (user_id) REFERENCES user(id)
);
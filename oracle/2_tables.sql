CONN personal_finance_system/finance123;

CREATE TABLE history (
    id NUMBER NOT NULL,
    insert_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    action VARCHAR2(255) NOT NULL,
    action_id NUMBER NOT NULL,
    modified_table VARCHAR2(30) NOT NULL,
    CONSTRAINT pk_history PRIMARY KEY (id)
);

CREATE TABLE tb_users (
    id NUMBER NOT NULL,
    full_name VARCHAR2(100) NOT NULL,
    email VARCHAR2(100) NOT NULL UNIQUE,
    password VARCHAR2(255) NOT NULL,
    birth_date DATE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,

    CONSTRAINT pk_user PRIMARY KEY (id)
);

CREATE TABLE category (
    id NUMBER NOT NULL,
    user_id NUMBER NOT NULL,
    name VARCHAR2(100) NOT NULL,

    CONSTRAINT pk_category PRIMARY KEY (id),
    CONSTRAINT fk_category_user FOREIGN KEY (user_id) REFERENCES tb_users(id)
);

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

CREATE TABLE alert (
    id NUMBER NOT NULL,
    alert_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    message CLOB NOT NULL,
    notified CHAR(1) DEFAULT 'N',
    user_id NUMBER,

    CONSTRAINT pk_alert PRIMARY KEY (id),
    CONSTRAINT fk_alert_user FOREIGN KEY (user_id) REFERENCES tb_users(id)
);

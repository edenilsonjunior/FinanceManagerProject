-- Conectado como personal_finance_system

/*
    ===== SEQUENCES PARA AUTO INCREMENT =====
*/

CREATE SEQUENCE history_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE tb_users_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE category_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE financial_record_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE wallet_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE wallet_transaction_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE alert_seq START WITH 1 INCREMENT BY 1;


/*
    ===== TRIGGERS DE AUTO INCREMENT E AUDITORIA =====
*/

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


CREATE OR REPLACE TRIGGER tb_users_bu
AFTER UPDATE ON tb_users
FOR EACH ROW
BEGIN
    INSERT INTO history(action, action_id, modified_table)
    VALUES
        ('update', :OLD.id, 'tb_users');
END;
/


CREATE OR REPLACE TRIGGER category_bu
AFTER UPDATE ON category
FOR EACH ROW
BEGIN
    INSERT INTO history(action, action_id, modified_table)
    VALUES
        ('update', :OLD.id, 'category');
END;
/


CREATE OR REPLACE TRIGGER financial_record_bu
AFTER UPDATE ON financial_record
FOR EACH ROW
BEGIN
    INSERT INTO history(action, action_id, modified_table)
    VALUES
        ('update', :OLD.id, 'financial_record');
END;
/


CREATE OR REPLACE TRIGGER wallet_bu
AFTER UPDATE ON wallet
FOR EACH ROW
BEGIN
    INSERT INTO history(action, action_id, modified_table)
    VALUES
        ('update', :OLD.id, 'wallet');
END;
/


CREATE OR REPLACE TRIGGER tb_users_bd
AFTER DELETE ON tb_users
FOR EACH ROW
BEGIN
    INSERT INTO history(action, action_id, modified_table)
    VALUES
        ('delete', :OLD.id, 'tb_users');
END;
/


CREATE OR REPLACE TRIGGER category_bd
AFTER DELETE ON category
FOR EACH ROW
BEGIN
    INSERT INTO history(action, action_id, modified_table)
    VALUES
        ('delete', :OLD.id, 'category');
END;
/


CREATE OR REPLACE TRIGGER financial_record_bd
AFTER DELETE ON financial_record
FOR EACH ROW
BEGIN
    INSERT INTO history(action, action_id, modified_table)
    VALUES
        ('delete', :OLD.id, 'financial_record');
END;
/


CREATE OR REPLACE TRIGGER wallet_bd
AFTER DELETE ON wallet
FOR EACH ROW
BEGIN
    INSERT INTO history(action, action_id, modified_table)
    VALUES
        ('delete', :OLD.id, 'wallet');
END;
/


CREATE OR REPLACE TRIGGER wallet_transaction_bu
AFTER UPDATE ON wallet_transaction
FOR EACH ROW
BEGIN
    INSERT INTO history(action, action_id, modified_table)
    VALUES
        ('update', :OLD.id, 'wallet_transaction');
END;
/


CREATE OR REPLACE TRIGGER wallet_transaction_bd
AFTER DELETE ON wallet_transaction
FOR EACH ROW
BEGIN
    INSERT INTO history(action, action_id, modified_table)
    VALUES
        ('delete', :OLD.id, 'wallet_transaction');
END;
/


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



/*
    ===== TRIGGERS EXTRAS =====
*/


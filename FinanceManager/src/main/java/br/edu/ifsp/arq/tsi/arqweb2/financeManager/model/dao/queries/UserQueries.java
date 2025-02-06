package br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.dao.queries;

public interface UserQueries {

    String CREATE = """
        INSERT INTO TB_USERS
        (id, full_name, email, password, birth_date)
        VALUES
        (user_seq.NEXTVAL, ?, ?, ?, ?)
        """;

    String SELECT = """
        SELECT u.id,
               u.full_name,
               u.email,
               u.password,
               u.birth_date,
               u.created_at
        FROM TB_USERS u
        WHERE u.id = ?
        """;

    String SELECT_BY_EMAIL = """
        SELECT u.id,
               u.full_name,
               u.email,
               u.password,
               u.birth_date,
               u.created_at
        FROM TB_USERS u
        WHERE u.email = ?
        """;
}

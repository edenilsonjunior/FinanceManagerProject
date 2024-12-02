package br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.dao.queries;

public interface UserQueries {

    String CREATE = """
        INSERT INTO user
        (full_name, email, password, birth_date)
        VALUES
        (?, ?, ?, ?);
        """;

    String SELECT = """
        SELECT u.id,
               u.full_name,
               u.email,
               u.password,
               u.birth_date,
               u.created_at
        FROM user u
        WHERE u.id = ?;
        """;

    String SELECT_BY_EMAIL = """
        SELECT u.id,
               u.full_name,
               u.email,
               u.password,
               u.birth_date,
               u.created_at
        FROM user u
        WHERE u.email = ?;
        """;

}

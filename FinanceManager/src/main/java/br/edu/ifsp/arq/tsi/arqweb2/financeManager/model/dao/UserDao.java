package br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.dao;

import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.contracts.dao.IUserDao;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.dao.queries.UserQueries;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.user.User;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

public class UserDao implements IUserDao {

    private final DataSource dataSource;

    public UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void create(User user) {
        try (var con = dataSource.getConnection();
             var ps = con.prepareStatement(UserQueries.CREATE, new String[]{"id"})) {

            ps.setString(1, user.getFullName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setDate(4, Date.valueOf(user.getBirthDate()));
            ps.executeUpdate();

            // Recuperar a chave gerada no Oracle após o INSERT
            try (var rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    user.setId(rs.getLong(1));
                }
            }

        } catch (SQLException sqlException) {
            throw new RuntimeException("Erro durante a criacao no BD", sqlException);
        }
    }

    @Override
    public Optional<User> findUserByEmail(String email) {

        try (var con = dataSource.getConnection();
             var ps = con.prepareStatement(UserQueries.SELECT_BY_EMAIL)) {

            ps.setString(1, email);

            var rs = ps.executeQuery();
            if (rs.next()) {
                var user = new User();
                user.setId(rs.getLong("id"));
                user.setFullName(rs.getString("full_name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setBirthDate(rs.getDate("birth_date").toLocalDate());
                user.setCreatedAt(rs.getDate("created_at").toLocalDate());

                return Optional.of(user);
            }

            return Optional.empty();

        } catch (SQLException sqlException) {
            throw new RuntimeException("Erro durante a consulta no BD", sqlException);
        }
    }

    @Override
    public Optional<User> findById(long id) {

        try (var con = dataSource.getConnection();
             var ps = con.prepareStatement(UserQueries.SELECT)) {

            ps.setLong(1, id);

            var rs = ps.executeQuery();
            if (rs.next()) {
                var user = new User();
                user.setId(rs.getLong("id"));
                user.setFullName(rs.getString("full_name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setBirthDate(rs.getDate("birth_date").toLocalDate());
                user.setCreatedAt(rs.getDate("created_at").toLocalDate());

                return Optional.of(user);
            }
        } catch (SQLException sqlException) {
            throw new RuntimeException("Erro durante a consulta no BD", sqlException);
        }

        return Optional.empty();
    }

    @Override
    public boolean existsUserByEmail(String email) {
        try (var con = dataSource.getConnection();
             var ps = con.prepareStatement(UserQueries.SELECT_BY_EMAIL)) {

            ps.setString(1, email);

            try (var rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException sqlException) {
            throw new RuntimeException("Erro durante a consulta no BD", sqlException);
        }
    }
}

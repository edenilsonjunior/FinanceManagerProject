package br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.dao;

import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.user.User;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao {

    private final DataSource dataSource;

    public UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void create(User user) {

        try (var con = dataSource.getConnection();
             var ps = con.prepareStatement(UserQueries.CREATE, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, user.getFullName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setDate(4, Date.valueOf(user.getBirthDate()));
            ps.executeUpdate();
            var rs = ps.getGeneratedKeys();
            if (rs.next()) {
                user.setId(rs.getLong(1));
            }

        } catch (SQLException sqlException) {
            throw new RuntimeException("Erro durante a criacao no BD", sqlException);
        }

    }

    public Optional<List<User>> findUserByUserId(long userId) {

        var list = new ArrayList<User>();

        try (var con = dataSource.getConnection();
             var ps = con.prepareStatement(UserQueries.SELECT)) {

            ps.setLong(1, userId);

            var rs = ps.executeQuery();
            while (rs.next()) {

                var user = new User();
                user.setId(rs.getLong("id"));
                user.setFullName(rs.getString("full_name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setBirthDate(LocalDate.parse(rs.getDate("birth_date").toString()));
                user.setCreatedAt(LocalDate.parse(rs.getDate("created_at").toString()));

                list.add(user);
            }
            return Optional.of(list);
        } catch (SQLException sqlException) {
            throw new RuntimeException("Erro durante a consulta no BD", sqlException);
        }

    }

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
                user.setBirthDate(LocalDate.parse(rs.getDate("birth_date").toString()));
                user.setCreatedAt(LocalDate.parse(rs.getDate("created_at").toString()));

                return Optional.of(user);
            }
        } catch (SQLException sqlException) {
            throw new RuntimeException("Erro durante a consulta no BD", sqlException);
        }

        return Optional.empty();
    }
}

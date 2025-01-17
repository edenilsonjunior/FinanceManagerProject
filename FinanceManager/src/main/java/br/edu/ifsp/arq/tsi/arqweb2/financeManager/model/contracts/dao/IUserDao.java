package br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.contracts.dao;

import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.user.User;

import java.util.Optional;

public interface IUserDao {
    void create(User user);
    Optional<User> findUserByEmail(String email);
    boolean existsUserByEmail(String email);
}
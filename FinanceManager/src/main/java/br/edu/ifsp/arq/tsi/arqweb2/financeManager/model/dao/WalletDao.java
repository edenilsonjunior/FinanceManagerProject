package br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.dao;

import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.contracts.dao.IWalletDao;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.dao.queries.WalletsQueries;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.dto.WalletHistoryOverview;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.financialRecord.TransactionTypeEnum;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.wallet.Wallet;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.wallet.WalletTransaction;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WalletDao implements IWalletDao {

    private final DataSource dataSource;

    public WalletDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Wallet create(Wallet wallet) {

        try (var con = dataSource.getConnection();
             var ps = con.prepareStatement(WalletsQueries.CREATE, new String[]{"id"})) {

            ps.setLong(1, wallet.getUser().getId());
            ps.setString(2, wallet.getName());
            ps.setDouble(3, wallet.getGoalAmount());
            ps.setString(4, wallet.getDescription());

            ps.executeUpdate();

            try (var rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    wallet.setId(rs.getLong(1));
                }
            }

            return wallet;
        } catch (SQLException sqlException) {
            throw new RuntimeException("Erro durante a criacao no BD", sqlException);
        }
    }

    @Override
    public WalletTransaction createTransaction(long walletId, WalletTransaction transaction) {

        try (var con = dataSource.getConnection();
             var ps = con.prepareStatement(WalletsQueries.CREATE_TRANSACTION, new String[]{"id"})) {

            ps.setLong(1, walletId);
            ps.setString(2, transaction.getTransactionType().name());
            ps.setDouble(3, transaction.getAmount());

            ps.executeUpdate();

            try (var rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    transaction.setId(rs.getLong(1));
                }
            }

            return transaction;
        } catch (SQLException sqlException) {
            throw new RuntimeException("Erro durante a criacao no BD", sqlException);
        }
    }

    @Override
    public boolean update(Wallet wallet) {

        try (var con = dataSource.getConnection();
        var ps = con.prepareStatement(WalletsQueries.UPDATE)) {

            ps.setString(1, wallet.getName());
            ps.setDouble(2, wallet.getGoalAmount());
            ps.setString(3, wallet.getDescription());
            ps.setLong(4, wallet.getId());

            return ps.executeUpdate() > 0;
        } catch (SQLException sqlException) {
            throw new RuntimeException("Erro SQL: ", sqlException);
        }
    }

    @Override
    public boolean delete(long walletId) {
        try (var con = dataSource.getConnection()) {
            con.setAutoCommit(false);

            try (var deleteTransactionsPs = con.prepareStatement(WalletsQueries.DELETE_TRANSACTIONS);
                 var deleteWalletPs = con.prepareStatement(WalletsQueries.DELETE)) {

                deleteTransactionsPs.setLong(1, walletId);
                deleteTransactionsPs.executeUpdate();

                deleteWalletPs.setLong(1, walletId);
                boolean deleted = deleteWalletPs.executeUpdate() > 0;

                con.commit();

                return deleted;
            } catch (SQLException sqlException) {
                con.rollback();
                throw new RuntimeException("Erro ao excluir carteira e transações", sqlException);
            }
        } catch (SQLException sqlException) {
            throw new RuntimeException("Erro ao conectar ao banco", sqlException);
        }
    }


    @Override
    public List<Wallet> getWalletsByUserId(Long userId) {

        var wallets = new ArrayList<Wallet>();

        try (var con = dataSource.getConnection();
             var ps = con.prepareStatement(WalletsQueries.SELECT_BY_USER_ID)) {

            ps.setLong(1, userId);

            var rs = ps.executeQuery();

            while (rs.next()) {
                wallets.add(buildWallet(rs));
            }

        } catch (SQLException sqlException) {
            throw new RuntimeException("Erro SQL: ", sqlException);
        }
        return wallets;
    }

    @Override
    public List<WalletTransaction> getTransactionsByWalletId(Long walletId) {

        var results = new ArrayList<WalletTransaction>();

        try (var con = dataSource.getConnection();
             var ps = con.prepareStatement(WalletsQueries.SELECT_TRANSACTIONS_BY_WALLET_ID)) {

            ps.setLong(1, walletId);

            var rs = ps.executeQuery();

            while (rs.next()) {

                var transaction = new WalletTransaction();
                transaction.setId(rs.getLong("id"));
                transaction.setTransactionType(TransactionTypeEnum.valueOf(rs.getString("transaction_type")));
                transaction.setAmount(rs.getDouble("amount"));
                transaction.setTransactionDate(rs.getTimestamp("transaction_date").toLocalDateTime());

                results.add(transaction);
            }
        } catch (SQLException sqlException) {
            throw new RuntimeException("Erro SQL: ", sqlException);
        }

        return results;
    }

    @Override
    public Wallet getWalletByIdAndUserId(Long walletId, Long userId) {

        try (var con = dataSource.getConnection();
             var ps = con.prepareStatement(WalletsQueries.SELECT_BY_ID_AND_USER_ID)) {

            ps.setLong(1, walletId);
            ps.setLong(2, userId);

            var rs = ps.executeQuery();

            if (rs.next()) {
                return buildWallet(rs);
            }

        } catch (SQLException sqlException) {
            throw new RuntimeException("Erro SQL: ", sqlException);
        }
        return null;
    }

    @Override
    public WalletHistoryOverview getWalletOverviewByUserId(Long userId) {

        try (var con = dataSource.getConnection();
             var cs = con.prepareCall(WalletsQueries.SELECT_OVERVIEW_BY_USER_ID)) {

            cs.registerOutParameter(1, java.sql.Types.REF_CURSOR);

            cs.setLong(2, userId);

            cs.execute();

            try (var rs = (ResultSet) cs.getObject(1)) {
                if (rs.next()) {
                    var overview = new WalletHistoryOverview();
                    overview.setTotalBalance(rs.getDouble("total_balance"));
                    overview.setMonthlyTransactionsCount(rs.getInt("transactions_this_month"));
                    return overview;
                }
            }

        } catch (SQLException sqlException) {
            throw new RuntimeException("Erro ao obter resumo da carteira: ", sqlException);
        }

        return null;
    }


    private Wallet buildWallet(ResultSet rs) throws SQLException {

        var wallet = new Wallet();
        wallet.setId(rs.getLong("id"));
        wallet.setName(rs.getString("name"));
        wallet.setGoalAmount(rs.getDouble("goal_amount"));
        wallet.setCurrentBalance(rs.getDouble("current_balance"));
        wallet.setDescription(rs.getString("description"));
        wallet.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());

        return wallet;
    }
}
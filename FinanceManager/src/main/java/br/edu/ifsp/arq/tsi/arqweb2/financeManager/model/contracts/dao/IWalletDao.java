package br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.contracts.dao;

import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.dto.WalletHistoryOverview;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.wallet.Wallet;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.wallet.WalletTransaction;

import java.util.List;

public interface IWalletDao {

    Wallet create(Wallet wallet);

    WalletTransaction createTransaction(long walletId, WalletTransaction transaction);

    boolean update(Wallet wallet);

    boolean delete(long walletId);

    List<Wallet> getWalletsByUserId(Long userId);

    List<WalletTransaction> getTransactionsByWalletId(Long walletId);

    Wallet getWalletByIdAndUserId (Long walletId, Long userId);

    WalletHistoryOverview getWalletOverviewByUserId(Long userId);
}

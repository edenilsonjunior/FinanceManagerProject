package br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.dto;

import lombok.Data;

import java.util.Optional;

@Data
public class WalletHistoryOverview {
    private Double totalBalance;
    private Optional<WalletHistory> highestBalanceWallet;
    private Integer monthlyTransactionsCount;
}

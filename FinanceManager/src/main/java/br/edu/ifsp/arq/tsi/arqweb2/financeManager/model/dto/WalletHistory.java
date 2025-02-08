package br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.dto;

import java.util.Optional;

public record WalletHistory(
        Long id,
        String name,
        Double currentBalance,
        Double goalAmount,
        Optional<WalletHistoryLastTransaction> lastTransaction
) { }

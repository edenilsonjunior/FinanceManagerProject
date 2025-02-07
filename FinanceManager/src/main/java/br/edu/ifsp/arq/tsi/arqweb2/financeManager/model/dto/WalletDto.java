package br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.dto;

import java.util.List;

public record WalletDto(
        List<WalletHistory> wallets,
        WalletHistoryOverview overview
) { }

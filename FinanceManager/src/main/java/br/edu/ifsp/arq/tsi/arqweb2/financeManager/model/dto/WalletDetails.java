package br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.dto;

import java.util.List;

public record WalletDetails(
    String name,
    Double goalAmount,
    Double currentBalance,
    String description,
    List<WalletDetailsTransaction> transactions
) { }

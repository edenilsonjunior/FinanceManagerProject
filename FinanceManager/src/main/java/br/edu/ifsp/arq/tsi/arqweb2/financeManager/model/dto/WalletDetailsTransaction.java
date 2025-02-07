package br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.dto;

import java.time.LocalDate;

public record WalletDetailsTransaction(
        String transactionType,
        Double amount,
        LocalDate transactionDate
) { }

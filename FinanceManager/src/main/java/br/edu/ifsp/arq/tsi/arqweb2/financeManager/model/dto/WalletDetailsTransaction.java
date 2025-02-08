package br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record WalletDetailsTransaction(
        String transactionType,
        Double amount,
        LocalDateTime transactionDate
) { }

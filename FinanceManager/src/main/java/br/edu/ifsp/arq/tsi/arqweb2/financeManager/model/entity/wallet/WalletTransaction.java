package br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.wallet;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class WalletTransaction {

    private long id;
    private String transactionType;
    private LocalDate transactionDate;
    private String description;
    private double amount;

    public WalletTransaction(String transactionType, LocalDate transactionDate, String description, double amount) {
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
        this.description = description;
        this.amount = amount;
    }
}

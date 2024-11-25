package br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.wallet;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class Wallet {

    private long id;
    private String name;
    private String description;
    private LocalDate createdAt;
    private double goalAmount;
    private double currentBalance;
    private List<WalletTransaction> transactions;

    public Wallet(String name, String description, LocalDate createdAt, double goalAmount, double currentBalance, List<WalletTransaction> transactions) {
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.goalAmount = goalAmount;
        this.currentBalance = currentBalance;
        this.transactions = transactions;
    }
}

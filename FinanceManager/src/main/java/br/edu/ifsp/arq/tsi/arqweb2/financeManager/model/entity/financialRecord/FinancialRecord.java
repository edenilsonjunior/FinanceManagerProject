package br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.financialRecord;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class FinancialRecord {

    private FinancialRecordCategory category;
    private double balance;
    private String transactionType;
    private LocalDate transactionDate;
    private String description;

    public FinancialRecord(FinancialRecordCategory category, double balance, String transactionType, LocalDate transactionDate, String description) {
        this.category = category;
        this.balance = balance;
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
        this.description = description;
    }
}

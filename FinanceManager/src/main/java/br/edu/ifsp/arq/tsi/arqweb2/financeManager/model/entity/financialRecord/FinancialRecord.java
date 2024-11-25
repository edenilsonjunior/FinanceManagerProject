package br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.financialRecord;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class FinancialRecord {

    private long id;
    private FinancialRecordCategory category;
    private double amount;
    private String transactionType;
    private LocalDate transactionDate;
    private String description;

    public FinancialRecord(long id, FinancialRecordCategory category, double amount, String transactionType, LocalDate transactionDate, String description) {
        this.id = id;
        this.category = category;
        this.amount = amount;
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
        this.description = description;
    }
}

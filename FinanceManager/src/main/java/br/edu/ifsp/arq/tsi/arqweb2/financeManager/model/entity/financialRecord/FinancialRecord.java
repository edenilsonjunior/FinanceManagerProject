package br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.financialRecord;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FinancialRecord {

    private long id;
    private FinancialRecordCategory category;
    private double amount;
    private String transactionType;
    private LocalDate transactionDate;
    private String description;

}

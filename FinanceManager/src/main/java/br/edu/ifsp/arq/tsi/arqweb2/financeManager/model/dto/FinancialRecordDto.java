package br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class FinancialRecordDto{
    private Long id;
    private String categoryName;
    private Double amount;
    private String transactionType;
    private LocalDate transactionDate;
    private String description;
}

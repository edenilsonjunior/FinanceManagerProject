package br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.wallet;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WalletTransaction {

    private long id;
    private String transactionType;
    private LocalDate transactionDate;
    private String description;
    private double amount;

}

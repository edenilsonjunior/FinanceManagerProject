package br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.wallet;

import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.financialRecord.TransactionTypeEnum;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WalletTransaction {

    private long id;
    private LocalDateTime transactionDate;
    private double amount;
    private TransactionTypeEnum transactionType;
}
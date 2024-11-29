package br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.financialRecord;

import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.user.User;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FinancialRecord {

    private long id;
    private User user;
    private FinancialRecordCategory category;
    private double amount;
    private TransactionTypeEnum transactionType;
    private LocalDate transactionDate;
    private String description;

}

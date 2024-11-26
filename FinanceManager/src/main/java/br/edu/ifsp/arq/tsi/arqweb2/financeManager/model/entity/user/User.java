package br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.user;

import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.financialRecord.FinancialRecord;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.financialRecord.FinancialRecordCategory;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.wallet.Wallet;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    private long id;
    private String fullName;
    private String email;
    private String password;
    private LocalDate birthDate;
    private LocalDate createdAt;

    private List<FinancialRecordCategory> categories;
    private List<FinancialRecord> financialRecords;
    private List<Wallet> wallets;
    private List<Alert> alerts;

}

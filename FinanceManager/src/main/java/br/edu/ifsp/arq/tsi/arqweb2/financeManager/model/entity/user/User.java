package br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.user;

import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.financialRecord.FinancialRecord;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.financialRecord.FinancialRecordCategory;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.wallet.Wallet;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
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

    public User(String fullName, String email, String password, LocalDate birthDate, LocalDate createdAt, List<FinancialRecordCategory> categories, List<FinancialRecord> financialRecords, List<Wallet> wallets, List<Alert> alerts) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
        this.createdAt = createdAt;
        this.categories = categories;
        this.financialRecords = financialRecords;
        this.wallets = wallets;
        this.alerts = alerts;
    }
}

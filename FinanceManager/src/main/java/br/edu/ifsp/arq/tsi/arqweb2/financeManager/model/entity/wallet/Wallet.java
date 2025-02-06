package br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.wallet;

import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.user.User;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Wallet {

    private long id;
    private User user;
    private String name;
    private String description;
    private LocalDate createdAt;
    private double goalAmount;
    private double currentBalance;
    private List<WalletTransaction> transactions;

}
